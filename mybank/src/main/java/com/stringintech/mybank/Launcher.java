package com.stringintech.mybank;

import com.stringintech.mybank.web.BankServlet;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;

public class Launcher {
    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(getPort());
        tomcat.getConnector();

        Context ctx = tomcat.addContext("", null);
        Wrapper servlet = Tomcat.addServlet(ctx, "myBankServlet", new BankServlet());
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/*");

        tomcat.start();
    }

    public static int getPort() {
        int port = 8080;
        String property = System.getProperty("server.port");
        if (property != null) {
            port = Integer.parseInt(property);
        }
        return port;
    }
}
