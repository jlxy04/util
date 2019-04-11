package com.github.utils.net;

import com.github.jlxy04.utils.net.HttpUtils;
import com.github.utils.net.servlet.PostFormServlet;
import com.github.utils.net.servlet.PostJsonServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @Description:
 * @author: lijun
 * @Date: 2019-4-10 09:05
 */
public class HttpUtilsTest {

    private int port = 8090;

    private String address = "http://localhost:" + port;

    @Before
    public void initServer() throws Exception {
        Server server = new Server(port);
        ServletHandler servletHandler = new ServletHandler();
        servletHandler.addServletWithMapping(PostFormServlet.class, "/postForm");
        servletHandler.addServletWithMapping(PostJsonServlet.class, "/postJson");
        server.setHandler(servletHandler);
        server.start();
    }

    @Test
    public void sendPostForm() {
        String result = HttpUtils.sendPostForm(address + "/postForm", "a=1&b=2");
        Assert.assertEquals("success", result);
    }

    @Test
    public void sendPostJson() {
        String result = HttpUtils.sendPostForm(address + "/postJson", "{\"a\":\"testa\", \"b\":\"testb\"}");
        Assert.assertEquals("success", result);
    }
}
