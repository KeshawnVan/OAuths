package com.keshawn.oauth.client.model;

public class TokenInfo {

    private String accessToken;

    private String openId;

    @Override
    public String toString() {
        return "TokenInfo{" +
                "accessToken='" + accessToken + '\'' +
                ", openId='" + openId + '\'' +
                '}';
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
