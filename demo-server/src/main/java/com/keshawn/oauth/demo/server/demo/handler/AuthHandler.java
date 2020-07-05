package com.keshawn.oauth.demo.server.demo.handler;

import com.keshawn.oauth.server.api.AuthService;
import com.keshawn.oauth.server.model.AuthResponse;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class AuthHandler implements AuthService {
    @Override
    public AuthResponse auth(HttpServletRequest servletRequest) {
        AuthResponse authResponse = new AuthResponse();
        authResponse.setOpenId("openId:1");
        return authResponse;
    }
}
