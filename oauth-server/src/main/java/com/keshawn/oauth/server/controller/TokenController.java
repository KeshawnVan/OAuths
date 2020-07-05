package com.keshawn.oauth.server.controller;

import com.keshawn.oauth.server.manager.AccessTokenManager;
import com.keshawn.oauth.server.manager.AppManager;
import com.keshawn.oauth.server.manager.VerifyCodeManager;
import com.keshawn.oauth.server.model.GetTokenRequest;
import com.keshawn.oauth.server.model.GetTokenResponse;
import com.keshawn.oauth.server.util.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/token")
public class TokenController {

    @Autowired
    private AppManager appManager;

    @Autowired
    private VerifyCodeManager verifyCodeManager;

    @Autowired
    private AccessTokenManager accessTokenManager;

    @ResponseBody
    @RequestMapping(path = "get-token", method = RequestMethod.POST)
    public GetTokenResponse getToken(@RequestBody GetTokenRequest getTokenRequest) {

        if (!verifyCodeManager.checkVerifyCode(getTokenRequest.getAppCode(), getTokenRequest.getVerifyCode())) {
            return new GetTokenResponse(null, null, "verify code invalid");
        }

        Tuple<Boolean, String> checkAppResult = appManager.checkAppWithSecret(getTokenRequest.getAppCode(), getTokenRequest.getSecret());
        if (!checkAppResult._1) {
            return new GetTokenResponse(null, null, checkAppResult._2);
        }

        String openId = verifyCodeManager.getOpenIdByVerifyCode(getTokenRequest.getVerifyCode());

        if (StringUtils.isEmpty(openId)) {
            return new GetTokenResponse(null, null, "verify code expired");
        }

        String accessToken = accessTokenManager.generateAccessToken(openId);

        return new GetTokenResponse(accessToken, openId, null);
    }
}
