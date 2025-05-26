package com.apress.users.advice;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
@Aspect
public class UsersAdvice {
//    @Around("execution(* com.apress.users.repository.UserRepository.save(..))")
//    public void checkFindRetroBoard(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        log.info("[ADVICE**************] {}", proceedingJoinPoint.getSignature().getName());
//    }

    @Before("execution(* com.apress.users.repository.UserRepository.findAll())")
    public void beforeServiceMethodExecution(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("Before executing method: " + methodName);
        log.info("[ADVICE**************] findAll()");
    }

}
