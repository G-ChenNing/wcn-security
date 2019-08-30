package com.github.wangchenning.security.app;

import com.github.wangchenning.security.core.authentication.AbstractChannelSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.*;

import java.util.concurrent.TimeUnit;

@Configuration
//@EnableOAuth2Client
//@Order(-5001)
@EnableAuthorizationServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WcnAuthorizationServerConfig extends AbstractChannelSecurityConfig {



//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        applyPasswordAuthenticationConfig(http);
//    }
}
