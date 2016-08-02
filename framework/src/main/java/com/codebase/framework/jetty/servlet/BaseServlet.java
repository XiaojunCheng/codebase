package com.codebase.framework.jetty.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

public class BaseServlet extends HttpServlet {

    private static final long serialVersionUID = 1025600256140415706L;

    protected Logger logger = LoggerFactory.getLogger(BaseServlet.class);

    protected void write(HttpServletResponse response, String msg) throws IOException {
        response.getWriter().write(msg);
    }

    protected void writeJson(HttpServletResponse response, Object object) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(object));
    }
}
