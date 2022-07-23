package com.frankmoley.lil.fid.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
public class CountingAspect {
    private static final Logger LOGGABLE = LoggerFactory.getLogger("LIT Appliaction Counter");
    private static final Map<String, Integer> countingMap = new HashMap<>();

    @Pointcut("@annotation(Countable)")
    public void executeCounting() {}

    @Before("executeCounting()")
    public void incrementCount(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        countingMap.put(methodName, countingMap.getOrDefault(methodName, 0) + 1);

        StringBuilder message = new StringBuilder("Current count are | " );
        countingMap.forEach((k,v) -> message.append(k + " :: " + v + " | "));
        LOGGABLE.info(message.toString());
    }


}
