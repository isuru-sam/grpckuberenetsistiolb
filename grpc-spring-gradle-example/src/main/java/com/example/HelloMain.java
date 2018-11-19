package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.TimeZone;

/**
 * Date 18/08/18
 * Time 12:07 AM
 *
 * @author yogin
 */

@ComponentScan(basePackages = "com.example")
public class HelloMain {

    private static final int DEFAULT_PORT = 10000;


    public static void main(String[] args) throws IOException, InterruptedException ,Exception{

        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

        //now that we have the configuration files, let the spring context read it and start the message listener container(s).
      /* ClassPathXmlApplicationContext context = new   ClassPathXmlApplicationContext("classpath*:/spring/spring*.xml");
        context.registerShutdownHook();*/
        
        ApplicationContext context
        = new AnnotationConfigApplicationContext(HelloMain.class);

        HelloMain p = context.getBean(HelloMain.class);
p.init(args);
/*
        int port = getPort(args);
        System.out.println("Starting");
        HelloServer server = context.getAutowireCapableBeanFactory().createBean(HelloServer.class);
        server.start(port);
        System.out.println("End starting")*/;
        
    }
    @Autowired
    HelloServer helloServer;
    public void init(String[] args) throws Exception{
    	 int port = getPort(args);
         System.out.println("Starting");
         HelloServer server = helloServer;
         server.start(port);
         System.out.println("End starting");
    }
    

    private static int getPort(String[] args) {
        int port = DEFAULT_PORT;
        if (args.length > 0) {
            try {
                String portNum = args[0];
                int parsedPort = Integer.parseInt(portNum);
                if (parsedPort > 0) {
                    port = parsedPort;
                }
            } catch (NumberFormatException ignored) {
            	System.out.println(ignored);
            }
        }
        return port;
    }
}
