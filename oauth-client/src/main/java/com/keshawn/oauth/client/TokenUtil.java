package com.keshawn.oauth.client;

import com.alibaba.fastjson.JSON;
import com.keshawn.oauth.client.model.GetTokenRequest;
import com.keshawn.oauth.client.model.TokenInfo;
import com.keshawn.oauth.client.util.HttpUtil;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.util.StringUtils;

public class TokenUtil {

    public static TokenInfo getToken(GetTokenRequest getTokenRequest) throws Exception {
        MediaType mediaType = MediaType.parse("application/json");
        Request request = new Request.Builder().post(RequestBody.create(mediaType, JSON.toJSONString(getTokenRequest))).build();
        Response response = HttpUtil.getOkHttpClient().newCall(request).execute();
        String message = response.message();
        if (StringUtils.isEmpty(message)) {
            return null;
        }
        return JSON.parseObject(message, TokenInfo.class);
    }
}
