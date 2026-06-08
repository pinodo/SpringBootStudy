package com.example.aop_config.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.aop_config.service.OrderService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // 생성자 주입을 통한 DI (final 필드 전용)
@RestController
public class OrderController {

  private final OrderService orderService;

  @GetMapping("/aop-test")
  public String aopTest() {
    System.out.println("OrderService 클래스: " + orderService.getClass());
    System.out.println("=====");
    String result = orderService.createOrder("item-001");
    System.out.println("=====");
    return result; // Order-item-001
  }
}
