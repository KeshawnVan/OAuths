package com.keshawn.oauth.server.api;

import com.keshawn.oauth.server.model.AuthResponse;

import javax.servlet.http.HttpServletRequest;

public interface AuthService {

    AuthResponse auth(HttpServletRequest servletRequest);

}
