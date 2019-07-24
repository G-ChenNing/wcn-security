package com.github.wangchenning.security.browser;

import com.github.wangchenning.security.browser.authentication.WcnAuthenticationFailHandler;
import com.github.wangchenning.security.browser.authentication.WcnAuthenticationSuccessHandler;
import com.github.wangchenning.security.core.properties.SecurityProperties;
import com.github.wangchenning.security.core.validate.code.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private WcnAuthenticationSuccessHandler wcnAuthenticationSuccessHandler;
    @Autowired
    private WcnAuthenticationFailHandler wcnAuthenticationFailHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(wcnAuthenticationFailHandler);
        //        http.httpBasic()
        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .loginPage("/authentication/require")
                .loginProcessingUrl("/authentication/form")
                .successHandler(wcnAuthenticationSuccessHandler)
                .failureHandler(wcnAuthenticationFailHandler)
                .and()
                .authorizeRequests()
                .antMatchers("/authentication/require", "/code/image",
                        securityProperties.getBrowser().getLoginPage()).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**", "/css/**", "/images/**");
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
