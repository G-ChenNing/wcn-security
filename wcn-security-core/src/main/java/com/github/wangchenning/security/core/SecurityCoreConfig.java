package com.github.wangchenning.security.core;

import com.github.wangchenning.security.core.properties.BrowserProperties;
import com.github.wangchenning.security.core.properties.ImageCodeProperties;
import com.github.wangchenning.security.core.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {
}
