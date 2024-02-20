package com.sergio.curso.springboot.app.aop.springbootaop.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Component
@Aspect
public class GreetingFooAspect {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Before("GreetingServicePointcuts.greetingFooLoogerPointCut()")
    public void loggerBefore(JoinPoint joinPoint) {
        
        String metodo = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info("Antes Primero: " + metodo + " invocado con los parametros: " + args);
    }

    @After("GreetingServicePointcuts.greetingFooLoogerPointCut()")
    public void loggerAfter(JoinPoint joinPoint) {
        
        String metodo = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info("Despues Primero: " + metodo + " invocado con los parametros: " + args);
    }
}
