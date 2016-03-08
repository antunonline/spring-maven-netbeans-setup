/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.com.unix.java.springcore.examples.jmx;

/**
 *
 * @author antun
 */
public class JmxTestBean {

    private String name;

    public String echo(String msg) {
        return msg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
