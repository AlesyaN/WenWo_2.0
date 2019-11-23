package ru.itis.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import ru.itis.models.Log;
import ru.itis.repositories.mongo.LogsRepository;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@Aspect
@Configuration
public class MyLogger {

    private Logger logger;

    @Autowired
    private LogsRepository logsRepository;

    public MyLogger() {
        this.logger = LoggerFactory.getLogger(MyLogger.class);
    }

    @Before("execution (* *..UserService.signUp(..))")
    public void logSignUp(JoinPoint jp) {
         logsRepository.save(Log.builder()
                 .date(new Date())
                 .info("New user " + jp.getArgs()[0] + "registered")
                 .build());
    }


}

