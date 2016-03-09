/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.com.unix.java.springcore.examples.security;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *
 * @author Antun
 */
@EnableWebSecurity
// @EnableGlobalMethodSecurity
// @EnableGlobalAuthentication
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user")
                .password("password")
                .roles("USER");
    }

    public void configureWithJdbcAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        DataSource dataSource = null;
        // org.postgresql.ds.PGSimpleDataSource
        // org.postgresql.xa.PGXADataSource<
        // org.mariadb.jdbc.MariaDbDataSource 
        // javax.sql.XADataSource
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .withDefaultSchema()
                .withUser("user").password("password").roles("USER").and()
                .withUser("admin").password("password").roles("USER", "ADMIN");
    }

    public void configureWithLdapAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .ldapAuthentication()
                .userDnPatterns("uid={0},ou=people")
                .groupSearchBase("ou=groups");

    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // If this method is removed, all requests are secured by default

        configureWithAllGenerated(http);
    }

    private void configureWithAllGenerated(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic();
    }

    public void configureWithCustomLogin(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login.html")
                .permitAll();
    }

}
