package com.keshawn.oauth.client.properties;

public class AppProperties {

    /**
     * 编码，唯一
     */
    private String code;

    /**
     * 密钥
     */
    private String secret;

    /**
     * 授权地址
     */
    private String authUrl;

    /**
     * token获取地址
     */
    private String tokenUrl;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getAuthUrl() {
        return authUrl;
    }

    public void setAuthUrl(String authUrl) {
        this.authUrl = authUrl;
    }

    public String getTokenUrl() {
        return tokenUrl;
    }

    public void setTokenUrl(String tokenUrl) {
        this.tokenUrl = tokenUrl;
    }
}
