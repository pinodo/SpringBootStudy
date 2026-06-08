package com.example.aop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.aop.service.OrderService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class OrderController {

  private final OrderService orderService;

  @GetMapping("/aop-test")
  public String aopTest() {
    System.out.println("OrderService Class: " + orderService.getClass());
    System.out.println("=====");
    String res = orderService.createOrder("item-001");
    System.out.println("=====");
    return res;
  }
}
