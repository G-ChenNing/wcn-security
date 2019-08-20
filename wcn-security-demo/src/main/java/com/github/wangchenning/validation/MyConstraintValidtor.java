package com.github.wangchenning.validation;

import com.github.wangchenning.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
public class MyConstraintValidtor implements ConstraintValidator<MyConstraint,Object> {
    @Autowired
    private HelloService helloService;
    
    @Override
    public boolean isValid(Object s, ConstraintValidatorContext constraintValidatorContext) {
        helloService.greeting("wcn");
        System.out.println(s);
        return false;
    }

    @Override
    public void initialize(MyConstraint constraintAnnotation) {
        log.info("my validtor initialize");
    }
}
