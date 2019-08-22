package com.github.wangchenning.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.wangchenning.security.core.validate.code.ValidateCode;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.RedisHttpSessionConfiguration;

import java.net.UnknownHostException;

@Configuration
public class RedisConfig {
    @Configuration
    public class MyRedisConfig {

        @Bean
        @ConditionalOnMissingBean(name = "redisTemplate")
        public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory)
                throws UnknownHostException {
            RedisTemplate<Object, Object> template = new RedisTemplate<>();
            template.setConnectionFactory(redisConnectionFactory);
            Jackson2JsonRedisSerializer<Object> ser = new Jackson2JsonRedisSerializer<Object>(Object.class);
            template.setDefaultSerializer(ser);
            return template;
        }
    }

    //RedisHttpSessionConfiguration session序列化
    @Bean("springSessionDefaultRedisSerializer")
    public RedisSerializer<Object> defaultRedisSerializer() {
        RedisSerializer<Object> objectRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
        return objectRedisSerializer;
    }

}
