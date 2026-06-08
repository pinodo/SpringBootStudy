package com.example.aop_config.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.aop_config.advice.TimeTraceAspect;

// 권장 방식 아님
@Configuration
public class AopConfig {

  @Bean
  public com.example.aop_config.advice.TimeTraceAspect TimeTraceAspect() {
    return new TimeTraceAspect();
  }
}
