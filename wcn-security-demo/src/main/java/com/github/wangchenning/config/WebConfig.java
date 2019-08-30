package com.github.wangchenning.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.wangchenning.interceptor.TimeInterceptor;
import com.github.wangchenning.webfilter.TimeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
//import org.springframework.session.web.http.HeaderHttpSessionStrategy;
//import org.springframework.session.web.http.HttpSessionStrategy;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private TimeInterceptor timeInterceptor;

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(timeInterceptor);
//    }


//    @Override
//    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
//        configurer.
//    }

//    @Bean
//    public FilterRegistrationBean timeFilter() {
//        System.out.println(1);
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        TimeFilter timeFilter = new TimeFilter();
//        filterRegistrationBean.setFilter(timeFilter);
//        List<String> urls = new ArrayList<>();
//        urls.add("/*");
//        filterRegistrationBean.setUrlPatterns(urls);
//        filterRegistrationBean.setOrder(1);
//        return filterRegistrationBean;
//    }

//    @Bean
//    public HttpSessionStrategy httpSessionStrategy() {
//        return new HeaderHttpSessionStrategy();
//    }

}
