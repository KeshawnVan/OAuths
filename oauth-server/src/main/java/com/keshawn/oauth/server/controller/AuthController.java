package com.keshawn.oauth.server.controller;

import com.keshawn.oauth.server.api.AuthService;
import com.keshawn.oauth.server.api.LoginPageRender;
import com.keshawn.oauth.server.manager.VerifyCodeManager;
import com.keshawn.oauth.server.model.App;
import com.keshawn.oauth.server.model.AuthResponse;
import com.keshawn.oauth.server.repository.AppRepository;
import com.keshawn.oauth.server.util.ServletResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping(path = "/oauth")
public class AuthController {

    public static final String APP_CODE = "appCode";
    @Autowired
    private AppRepository appRepository;

    @Autowired
    private LoginPageRender loginPageRender;

    @Autowired
    private AuthService authService;

    @Autowired
    private VerifyCodeManager verifyCodeManager;

    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public void index(HttpServletRequest servletRequest, HttpServletResponse servletResponse, HttpSession session, String appCode) {
        // 校验appCode
        App app = appRepository.findByCode(appCode);

        if (app == null) {
            ServletResponseUtil.writeErrorMessage(servletResponse, "appCode invalid");
            return;
        }
        // 把appCode存到session中
        session.setAttribute(APP_CODE, appCode);

        // 业务方执行登录页面跳转/渲染逻辑
        loginPageRender.execute(servletRequest, servletResponse);
    }

    @RequestMapping(path = "/auth", method = RequestMethod.POST)
    public void auth(HttpServletRequest servletRequest, HttpServletResponse servletResponse, HttpSession session) throws IOException {
        // 校验session中是否包含appCode
        Object appCodeAttribute = session.getAttribute(APP_CODE);
        if (appCodeAttribute == null) {
            ServletResponseUtil.writeErrorMessage(servletResponse, "cannot find appCode");
            return;
        }
        // 再次校验appCode
        String appCode = appCodeAttribute.toString();
        App app = appRepository.findByCode(appCode);
        if (app == null) {
            ServletResponseUtil.writeErrorMessage(servletResponse, "appCode invalid");
            return;
        }

        // 执行授权接口
        AuthResponse authResponse = authService.auth(servletRequest);
        if (authResponse == null) {
            ServletResponseUtil.writeErrorMessage(servletResponse, "auth failure");
            return;
        }

        // 校验授权结果
        if (StringUtils.isEmpty(authResponse.getOpenId())) {
            ServletResponseUtil.writeErrorMessage(servletResponse, "auth failure, error message is " + authResponse.getErrorMessage());
            return;
        }

        // 生成verifyCode
        String verifyCode = verifyCodeManager.generateVerifyCode(app, authResponse.getOpenId());

        // 构建重定向url
        String redirectUrl = app.getRedirectUrl() + "?" + "verifyCode" + ":" + verifyCode;

        servletResponse.sendRedirect(redirectUrl);

    }


}
