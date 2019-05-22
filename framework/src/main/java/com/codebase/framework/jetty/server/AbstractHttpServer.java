package com.codebase.framework.jetty.server;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractHttpServer implements HttpServer {

    private static final Logger logger = LoggerFactory.getLogger(AbstractHttpServer.class);

    private static final Pattern PATTERN = Pattern.compile("^/(.*)[a-z0-9]$");

    @Override
    public void register(Class<? extends HttpServlet> servlet) throws Exception {

        RequestMapping mapping = servlet.getAnnotation(RequestMapping.class);
        if (mapping == null) {
            logger.error("class {} should has @RequestMapping", servlet);
            return;
        }

        String uri = mapping.value();
        if (!PATTERN.matcher(uri).matches()) {
            throw new Exception("uri " + uri + " format error");
        }
        logger.warn("register servlet {} uri {}", servlet, uri);
        doRegister(uri, servlet);
    }

    abstract void doRegister(String uri, Class<? extends HttpServlet> servlet);

}
