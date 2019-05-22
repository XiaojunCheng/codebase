package com.codebase.framework.jetty;

import com.codebase.framework.jetty.server.HttpServer;
import com.codebase.framework.jetty.server.JettyServer;
import com.codebase.framework.jetty.servlet.StatusServlet;

public class Main {

    public static void main(String[] args) throws Exception {

        final HttpServer server = new JettyServer();

        server.register(StatusServlet.class);
        System.out.println("start server, port " + 8899);

        server.start(8899);
        System.out.println("server start success");
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("start shutdown server");
                    server.shutdown();
                    System.out.println("server shutdown success");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }));
        Thread.currentThread().join();
    }

}
