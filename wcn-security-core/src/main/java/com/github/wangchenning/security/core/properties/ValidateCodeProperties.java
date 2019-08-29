package com.github.wangchenning.security.core.properties;

import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author Musk
 */
public class ValidateCodeProperties {
    @NestedConfigurationProperty
    private ImageCodeProperties image = new ImageCodeProperties();
    @NestedConfigurationProperty
    private SmsCodeProperties sms = new SmsCodeProperties();

    public ImageCodeProperties getImage() {
        return image;
    }

    public void setImage(ImageCodeProperties image) {
        this.image = image;
    }

    public SmsCodeProperties getSms() {
        return sms;
    }

    public void setSms(SmsCodeProperties sms) {
        this.sms = sms;
    }
}
