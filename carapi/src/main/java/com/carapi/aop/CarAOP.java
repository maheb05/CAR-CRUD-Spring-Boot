package com.carapi.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class CarAOP {
    
    private static final Logger log = LoggerFactory.getLogger(CarAOP.class);
    
    @Before(value = "execution(* com.carapi.controller.*.*(..)) || execution(* com.carapi.service.*.*(..))")
    public void beforeAdvice(JoinPoint joinPoint) {
        log.info("Before Advice: {}", joinPoint.getSignature().getName());
    }
    
    @AfterReturning(value = "execution(* com.carapi.controller.*.*(..)) || execution(* com.carapi.service.*.*(..))", returning = "result")
    public void afterReturningAdvice(JoinPoint joinPoint, Object result) {
        log.info("After Returning Advice: {}", joinPoint.getSignature().getName());
    }
}
