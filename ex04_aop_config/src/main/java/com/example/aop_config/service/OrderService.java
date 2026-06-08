package com.example.aop_config.service;

import org.springframework.stereotype.Service;

@Service
public class OrderService {

  // 실제 업무 처리(비즈니스 메서드: 포인트멋 대상이 되는 타겟 메서드)
  public String createOrder(String itemId) {
    System.out.println("[주문 생성 메서드 시작], 주문 아이템: " + itemId);
    try {
      Thread.sleep(1000); // 1초 지연
    } catch (Exception e) {
      Thread.currentThread().interrupt();
    }
    System.out.println("[주문 생성 메서드 종료]");
    return "Order-" + itemId;
  } 
}
