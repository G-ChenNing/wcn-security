package com.github.wangchenning.validation;

import com.github.wangchenning.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

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
        System.out.println("my validtor initialize");
    }
}
