package com.keshawn.oauth.server.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ServletResponseUtil {

    public static void writeErrorMessage(HttpServletResponse response, String errorMessage) {
        response.setContentType("text/plain; charset=utf-8");
        try {
            PrintWriter writer = response.getWriter();
            writer.println(errorMessage);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
