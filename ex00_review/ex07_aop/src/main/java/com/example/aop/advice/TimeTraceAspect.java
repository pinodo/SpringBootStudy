package com.example.aop.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAspect {

  @Around("execution(* com.example.aop.service..*Service.*(..))")
  public Object executeTimeTrace(ProceedingJoinPoint joinPoint) throws Throwable {

    long start = System.currentTimeMillis();

    System.out.println("AOP 실행 메서드: " + joinPoint.toString());

    try {
      Object result = joinPoint.proceed();
      return result;
    } finally {
      long finish = System.currentTimeMillis();
      long timeMs = finish - start;
      System.out.println("AOP 가로채기 종료(실행 시간: " + timeMs + "ms)");
    }
  }
}
