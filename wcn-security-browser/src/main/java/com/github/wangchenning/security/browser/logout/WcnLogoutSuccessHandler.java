package com.github.wangchenning.security.browser.logout;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.wangchenning.security.browser.support.SimpleResponse;
import com.github.wangchenning.security.core.properties.SecurityProperties;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WcnLogoutSuccessHandler implements LogoutSuccessHandler {
    Logger logger = LoggerFactory.getLogger(WcnLogoutSuccessHandler.class);
    private String signOutUrl;
    private ObjectMapper objectMapper = new ObjectMapper();

    public WcnLogoutSuccessHandler(String signOutUrl) {
        this.signOutUrl = signOutUrl;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.info("退出成功");
        if (StringUtils.isBlank(signOutUrl)) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse("退出成功")));
        } else {
            response.sendRedirect(signOutUrl);
        }
    }
}
