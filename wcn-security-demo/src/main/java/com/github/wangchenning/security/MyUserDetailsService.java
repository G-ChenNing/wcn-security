package com.github.wangchenning.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailsService implements UserDetailsService, SocialUserDetailsService {
    @Autowired
    private BCryptPasswordEncoder encoder;

    private Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        logger.info("登录用户名：" + s);
        return buildUser(s);
    }

    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        logger.info("社交登录用户IdMyUserDetailsService：" + userId);
        return buildUser(userId);
    }

    private SocialUserDetails buildUser(String userId) {
        //根据用户名查找用户信息
        String password = encoder.encode("123456");
        logger.info("数据库密码{}",password);
        //判断是否被冻结
//        return new User(s,encoder.encode("123456"), AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        return new SocialUser(userId, password, true, true, true
                , true, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
