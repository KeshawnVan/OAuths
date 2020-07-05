package com.keshawn.oauth.server.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LoginPageRender {

    void execute(HttpServletRequest servletRequest, HttpServletResponse servletResponse);

}
