package hr.com.unix.java.springcore.examples.security;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 *
 * @author Antun
 */
@EnableGlobalMethodSecurity(securedEnabled = true)
public class MethodSecurityConfig {

    public interface BankService {

        @Secured("IS_AUTHENTICATED_ANONYMOUSLY")
        public Object readAccount(Long id);

        @Secured("IS_AUTHENTICATED_ANONYMOUSLY")
        public Object[] findAccounts();

        @Secured("ROLE_TELLER")
        public Object post(Object account, double amount);
    }
}
