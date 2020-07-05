package com.keshawn.oauth.client;

import com.keshawn.oauth.client.constant.OAuthConstant;
import com.keshawn.oauth.client.properties.AppProperties;

public final class OAuthUtil {

    public static String buildRedirectUrl(AppProperties appProperties) {
        // check appProperties
        String redirectUrl = appProperties.getAuthUrl() + "?" + OAuthConstant.APP_CODE + "=" + appProperties.getCode();
        return "redirect:" + redirectUrl;
    }
}
