package com.github.utils.net.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description:
 * @author: lijun
 * @Date: 2019-4-10 09:30
 */
public class PostFormServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("post");
        System.out.println(req.getParameter("a"));
        System.out.println(req.getParameter("b"));
        resp.getWriter().write("success");
    }
}

