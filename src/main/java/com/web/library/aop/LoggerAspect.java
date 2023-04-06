package com.web.library.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

@Slf4j
@Aspect
@Component
public class LoggerAspect {

    @Around("execution(* com.web.library.domain..*Controller.*(..)) || execution(* com.web.library.domain..*Service.*(..)) || execution(* com.web.library.domain..*Mapper.*(..))")
    public Object printLog(ProceedingJoinPoint joinPoint) throws Throwable {

        String name = joinPoint.getSignature().getDeclaringTypeName();
        String type =
                StringUtils.contains(name, "Controller") ? "Controller ===> " :
                        StringUtils.contains(name, "Service") ? "Service ===> " :
                                StringUtils.contains(name, "Mapper") ? "Mapper ===> " : "";

        log.debug(type + name + "." + joinPoint.getSignature().getName() + "()");
        return joinPoint.proceed();
    }

}