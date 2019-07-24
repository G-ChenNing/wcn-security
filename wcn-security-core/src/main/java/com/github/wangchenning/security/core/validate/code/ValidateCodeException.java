package com.github.wangchenning.security.core.validate.code;

import org.springframework.security.core.AuthenticationException;

public class ValidateCodeException extends AuthenticationException {

    private static final long serialVersionUID = 6337036482414361375L;

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
