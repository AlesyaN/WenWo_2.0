package ru.itis.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@Aspect
@Configuration
public class MyLogger {

    private Logger logger;

    public MyLogger() {
        this.logger = LoggerFactory.getLogger(MyLogger.class);
    }

    @Before("execution (* *..UserService.signUp(..))")
    public void logSignUp(JoinPoint jp) {
        SimpleDateFormat format = new SimpleDateFormat("hh:mm dd/MM/yy");
        logger.info("New user " + Arrays.toString(jp.getArgs())  +
                "registered at " + format.format(new Date()));
    }


}

