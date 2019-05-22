package com.codebase.framework.jetty.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.codebase.framework.jetty.server.RequestMapping;

@RequestMapping("/status")
public class StatusServlet extends BaseServlet {

    private static final long serialVersionUID = 46668273497335797L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        writeJson(resp, "OK");
    }

}
