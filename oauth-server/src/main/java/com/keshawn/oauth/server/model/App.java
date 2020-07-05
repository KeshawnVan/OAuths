package com.keshawn.oauth.server.model;

public class App {

    /**
     * ID
     */
    private String id;

    /**
     * 编码，唯一
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 密钥
     */
    private String secret;

    /**
     * 重定向地址
     */
    private String redirectUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
}
