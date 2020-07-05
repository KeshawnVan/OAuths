package com.keshawn.oauth.client;

import com.alibaba.fastjson.JSON;
import com.keshawn.oauth.client.model.GetTokenRequest;
import com.keshawn.oauth.client.model.TokenInfo;
import com.keshawn.oauth.client.util.HttpUtil;
import okhttp3.*;
import org.springframework.util.StringUtils;

public class TokenUtil {

    public static TokenInfo getToken(GetTokenRequest getTokenRequest, String url) throws Exception {
        MediaType mediaType = MediaType.parse("application/json");
        Request request = new Request.Builder().url(url).post(RequestBody.create(mediaType, JSON.toJSONString(getTokenRequest))).build();
        Response response = HttpUtil.getOkHttpClient().newCall(request).execute();
        ResponseBody responseBody = response.body();
        if (responseBody == null) {
            return null;
        }
        String message = responseBody.string();
        if ( StringUtils.isEmpty(message)) {
            return null;
        }
        return JSON.parseObject(message, TokenInfo.class);
    }
}
