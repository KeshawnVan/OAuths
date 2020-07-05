package com.keshawn.oauth.demo.client.democlient.controller;

import com.keshawn.oauth.client.TokenUtil;
import com.keshawn.oauth.client.model.GetTokenRequest;
import com.keshawn.oauth.client.model.TokenInfo;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;
import java.util.Optional;

@Controller
public class ThirdLoginController {

    private static final String AUTH_URL = "http://localhost:8080/oauth/index";

    private static final String TOKEN_URL = "http://localhost:8080/token/get-token";

    private static final String APP_CODE = "mock";

    private static final String SECRET = "123456";

    @RequestMapping(path = "/third", method = RequestMethod.GET)
    public String thirdLogin() {
        String redirectUrl = AUTH_URL + "?" + "appCode=" + APP_CODE;
        return "redirect:" + redirectUrl;
    }

    @ResponseBody
    @RequestMapping(path = "/callback", method = RequestMethod.GET)
    public String callback(@RequestParam("verifyCode") String verifyCode) throws Exception {
        if (StringUtils.isEmpty(verifyCode)) {
            return "verify code is empty";
        }
        GetTokenRequest getTokenRequest = new GetTokenRequest();
        getTokenRequest.setAppCode(APP_CODE);
        getTokenRequest.setSecret(SECRET);
        getTokenRequest.setVerifyCode(verifyCode);
        TokenInfo tokenInfo = TokenUtil.getToken(getTokenRequest, TOKEN_URL);
        return Optional.ofNullable(tokenInfo).map(Objects::toString).orElseThrow(() -> new RuntimeException("token info is null"));
    }
}
