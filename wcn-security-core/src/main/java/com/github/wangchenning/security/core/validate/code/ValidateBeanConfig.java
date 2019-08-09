package com.github.wangchenning.security.core.validate.code;

import com.github.wangchenning.security.core.properties.SecurityProperties;
import com.github.wangchenning.security.core.validate.code.image.ImageCodeGenerator;
import com.github.wangchenning.security.core.validate.code.sms.DefaultSmsSender;
import com.github.wangchenning.security.core.validate.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidateBeanConfig {
    @Autowired
    private SecurityProperties securityProperties;
    @Bean
    @ConditionalOnMissingBean(name = "imageValidateCodeGenerator")
    public ValidateCodeGenerator imageValidateCodeGenerator() {
        ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
        codeGenerator.setSecurityProperties(securityProperties);
        return codeGenerator;
    }
    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeGenerator() {
        return new DefaultSmsSender();
    }
}
