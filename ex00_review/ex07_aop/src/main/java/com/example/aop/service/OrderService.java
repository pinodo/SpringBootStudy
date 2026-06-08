package com.example.aop.service;

import org.springframework.stereotype.Service;

@Service
public class OrderService {

  public String createOrder(String itemId) {
    System.out.println("[주문 생성 메서드 시작], 주문 아이템: " + itemId);
    try {
      Thread.sleep(1000);
    } catch (Exception e) {
      Thread.currentThread().interrupt();
    }
    System.out.println("[주문 생성 메서드 종료]");
    return "Order-" + itemId;
  }
}
