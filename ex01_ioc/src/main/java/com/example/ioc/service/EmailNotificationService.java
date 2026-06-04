package com.example.ioc.service;

import org.springframework.stereotype.Component;

@Component // new EmailNotificationService() 대체
public class EmailNotificationService implements NotificationService{
  @Override
  public void sendNotification(String message) {
    System.out.println("[이메일 발송] " + message);
  }
}
