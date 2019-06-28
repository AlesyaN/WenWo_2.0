package ru.itis.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import ru.itis.models.Info;
import ru.itis.services.InfoService;

import java.time.LocalDateTime;
import java.util.Date;

@Aspect
@Configuration
public class StatisticUpdateAspect {

    @Autowired
    InfoService infoService;

    @Around("execution(* *..InfoService.getInfo(..))")
    public Info updateStats(ProceedingJoinPoint jp) {
        try {
            Info info = (Info) jp.proceed(jp.getArgs());
            if (info != null && info.getUpdated().isAfter(LocalDateTime.now().minusDays(1))) {
                return info;
            } else {
                infoService.update();
                return (Info)jp.proceed(jp.getArgs());
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }
}
