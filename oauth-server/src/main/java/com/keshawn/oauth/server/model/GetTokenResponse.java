package com.keshawn.oauth.server.model;

public class GetTokenResponse {

    private String accessToken;

    private String openId;

    private String errorMessage;

    public GetTokenResponse() {
    }

    public GetTokenResponse(String accessToken, String openId, String errorMessage) {
        this.accessToken = accessToken;
        this.openId = openId;
        this.errorMessage = errorMessage;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
