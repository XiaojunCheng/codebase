package com.codebase.framework.jetty.server;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.nio.SelectChannelConnector;
import org.mortbay.jetty.servlet.ServletHandler;
import org.mortbay.thread.QueuedThreadPool;

import javax.servlet.http.HttpServlet;

public class JettyServer extends AbstractHttpServer {

    private final Server server;
    private final SelectChannelConnector connector;
    private final ServletHandler handler;

    public JettyServer() {
        this.server = new Server();
        this.handler = new ServletHandler();
        this.connector = new SelectChannelConnector();
    }

    @Override
    public void start(int port) throws Exception {
        connector.setPort(port);
        QueuedThreadPool pool = new QueuedThreadPool(100);
        pool.setName("Jetty-Worker");
        server.setThreadPool(pool);
        server.addConnector(connector);
        server.setHandler(handler);
        server.setGracefulShutdown(2000);
        server.setSendServerVersion(false);
        server.start();
    }

    @Override
    public void shutdown() throws Exception {
        connector.stop();
        handler.stop();
        server.stop();
    }

    @Override
    void doRegister(String uri, Class<? extends HttpServlet> servlet) {
        handler.addServletWithMapping(servlet, uri);
    }
}
