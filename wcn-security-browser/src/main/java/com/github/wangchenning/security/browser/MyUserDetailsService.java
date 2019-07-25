package com.github.wangchenning.security.browser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private BCryptPasswordEncoder encoder;

    private Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        logger.info("登录用户名：" + s);
        //根据用户名查找用户信息
        String password = encoder.encode("123456");
        logger.info("数据库密码{}",password);
        //判断是否被冻结
//        return new User(s,encoder.encode("123456"), AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        return new User(s, password, true, true, true
                , true, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
