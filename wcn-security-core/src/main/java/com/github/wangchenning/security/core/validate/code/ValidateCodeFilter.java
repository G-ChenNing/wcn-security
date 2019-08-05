package com.github.wangchenning.security.core.validate.code;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;

public class ValidateCodeFilter extends OncePerRequestFilter {

    private AuthenticationFailureHandler authenticationFailureHandler;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        if (StringUtils.equals("/authentication/form", httpServletRequest.getRequestURI())
                && StringUtils.equalsIgnoreCase(httpServletRequest.getMethod(), "post")) {
            try {
                validate(new ServletWebRequest(httpServletRequest));
            } catch (ValidateCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
                return;
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }

    private void validate(ServletWebRequest servletWebRequest) throws ServletRequestBindingException {
        ImageCode codeInSession = (ImageCode) sessionStrategy.getAttribute(servletWebRequest, ValidateCodeController.SESSION_KEY);
        String codeInRequest = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(), "imageCode");
//        try {
//            codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(),
//                    processorType.getParamNameOnValidate());
//        } catch (ServletRequestBindingException e) {
//            throw new ValidateCodeException("获取验证码的值失败");
//        }

        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException("验证码的值不能为空");
        }

        if (codeInSession == null) {
            throw new ValidateCodeException("验证码不存在");
        }

        if (codeInSession.isExpried()) {
            sessionStrategy.removeAttribute(servletWebRequest, ValidateCodeController.SESSION_KEY);
            throw new ValidateCodeException("验证码已过期");
        }

        if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
            throw new ValidateCodeException("验证码不匹配");
        }

        sessionStrategy.removeAttribute(servletWebRequest, ValidateCodeController.SESSION_KEY);
    }

    public AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return authenticationFailureHandler;
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    public SessionStrategy getSessionStrategy() {
        return sessionStrategy;
    }

    public void setSessionStrategy(SessionStrategy sessionStrategy) {
        this.sessionStrategy = sessionStrategy;
    }

//    public static void main(String[] args) {
//        LocalDateTime localDateTime = LocalDateTime.now().atZone(ZoneId.systemDefault()).toLocalDateTime();
//        System.out.println(LocalDateTime.parse("2019-10-25 00:01:02", DateTimeFormatter.ofPattern("y-MM-d HH:mm:ss"))
//                .with(ChronoField.DAY_OF_MONTH, LocalDateTime.parse("2019-10-25 00:01:02", DateTimeFormatter.ofPattern("y-MM-d HH:mm:ss")).range(ChronoField.DAY_OF_MONTH).getMinimum()));
//        System.out.println(localDateTime.format(DateTimeFormatter.ofPattern("y-MMM-d HH:mm:ss")));
//        System.out.println(LocalDateTime.of(2019,9,1,0,0,5).format(DateTimeFormatter.ofPattern("y-MM-dd HH:mm:ss")));
//
//        System.out.println(localDateTime.toLocalDate().withDayOfMonth(2).format(DateTimeFormatter.ofPattern("y-MM-dd")));
//        System.out.println(localDateTime.toLocalTime().withHour(17));
//
//        System.out.println(localDateTime.toLocalTime().withHour(17).withMinute((int)ChronoField.MINUTE_OF_HOUR.range().getMaximum()));
//
//        System.out.println(LocalDateTime.now().plusYears(-1));
//
//
//    }
}
