package com.github.wangchenning.security.core.properties;

import org.springframework.boot.context.properties.NestedConfigurationProperty;

public class SocialProperties {
    
    private String filterProcessUrl = "/auth";

    @NestedConfigurationProperty
    private QQProperties qq = new QQProperties();

    @NestedConfigurationProperty
    private WeixinProperties weixin = new WeixinProperties();

    public QQProperties getQq() {
        return qq;
    }

    public void setQq(QQProperties qq) {
        this.qq = qq;
    }

    public String getFilterProcessUrl() {
        return filterProcessUrl;
    }

    public void setFilterProcessUrl(String filterProcessUrl) {
        this.filterProcessUrl = filterProcessUrl;
    }

    public WeixinProperties getWeixin() {
        return weixin;
    }

    public void setWeixin(WeixinProperties weixin) {
        this.weixin = weixin;
    }
}
