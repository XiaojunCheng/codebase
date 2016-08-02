package com.codebase.framework.jetty.server;

import javax.servlet.http.HttpServlet;

public interface HttpServer {

    void start(int port) throws Exception;

    void shutdown() throws Exception;

    void register(Class<? extends HttpServlet> servlet) throws Exception;
}
