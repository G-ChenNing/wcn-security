package com.github.wangchenning.security.core.properties;

public abstract class AbstractSocialProperties {
    private String appId;
    private String appSecret;
    public AbstractSocialProperties() {
    }
    public String getAppId() {
        return this.appId;
    }
    public void setAppId(String appId) {
        this.appId = appId;
    }
    public String getAppSecret() {
        return this.appSecret;
    }
    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }
}
