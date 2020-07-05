package com.keshawn.oauth.demo.server.demo.handler;

import com.keshawn.oauth.server.api.LoginPageRender;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class LoginPageRenderImpl implements LoginPageRender {

    @Override
    public void execute(HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        try {
            servletResponse.setContentType("html");
            PrintWriter writer = servletResponse.getWriter();
            writer.println("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>login</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<form method=\"post\" action=\"/oauth/auth\">\n" +
                    "    <input type=\"submit\" name=\"login\"/>\n" +
                    "</form>\n" +
                    "</body>\n" +
                    "</html>");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
