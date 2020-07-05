package com.keshawn.oauth.client.util;

import okhttp3.OkHttpClient;

public class HttpUtil {

    private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient();

    public static OkHttpClient getOkHttpClient() {
        return OK_HTTP_CLIENT;
    }
}
