package com.github.wangchenning.ascept;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeAscept {
    @Around("execution(* com.github.wangchenning.controller.UserController.*(..))")
    public Object handlerControllerMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("time aspect start");
        Object[] args = proceedingJoinPoint.getArgs();
        for (Object arg : args) {
            System.out.println("arg is:" + arg);
        }


        long start = System.currentTimeMillis();
        Object proceed = proceedingJoinPoint.proceed();
        System.out.println("time aspect 耗时：" + (System.currentTimeMillis() - start));
        System.out.println("time aspect end");
        return proceed;
    }

}
