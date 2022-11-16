package com.prosoft.processingcenter.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("@annotation(com.prosoft.processingcenter.logging.LogThis)")
    public void logBefore(JoinPoint joinPoint) {

        logger.info("Вызов метода : " + joinPoint.getSignature().getName()
                + "Класс : " + joinPoint.getTarget().getClass().getName() + " "
                + "Прокси : " + joinPoint.getThis().getClass().getName());
        logger.info("Аргументы метода : " + Arrays.stream(joinPoint.getArgs()).map(Objects::toString)
                .collect(Collectors.joining(", ")) );
    }

    @After("@annotation(com.prosoft.processingcenter.logging.LogThis)")
    public void logAfter(JoinPoint joinPoint) {
        logger.info("Выполнение метода: " + joinPoint.getSignature().getName() + " завершено ");
    }

    @AfterReturning(value = "@annotation(com.prosoft.processingcenter.logging.LogThis)", returning = "result")
    public void logReturn(JoinPoint joinPoint, Object result) {
        logger.info("Результат: " + result.toString());
    }

}
