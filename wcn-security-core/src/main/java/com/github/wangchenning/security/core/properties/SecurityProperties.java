package com.github.wangchenning.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@ConfigurationProperties(prefix = "wcn.security", ignoreUnknownFields = true)
public class SecurityProperties {
    @NestedConfigurationProperty
    private BrowserProperties browser = new BrowserProperties();
    @NestedConfigurationProperty
    private ValidateCodeProperties code = new ValidateCodeProperties();
    @NestedConfigurationProperty
    private SocialProperties social = new SocialProperties();

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }

    public ValidateCodeProperties getCode() {
        return code;
    }

    public void setCode(ValidateCodeProperties code) {
        this.code = code;
    }

    public SocialProperties getSocial() {
        return social;
    }

    public void setSocial(SocialProperties social) {
        this.social = social;
    }
}
