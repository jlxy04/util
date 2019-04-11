package com.github.utils.net.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @Description:
 * @author: lijun
 * @Date: 2019-4-10 09:30
 */
public class PostJsonServlet extends HttpServlet {

    private Logger log = LoggerFactory.getLogger(PostJsonServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        InputStream inputStream = req.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line, result = "";
        while ((line = bufferedReader.readLine()) != null) {
            result += line;
        }
        log.info("接收到的body为 -> {}", result);

        bufferedReader.close();

        resp.getWriter().write("success");
    }
}

