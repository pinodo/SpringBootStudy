package com.example.ioc.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.ioc.dto.UserDTO;
import com.example.ioc.service.NotificationService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class UserController {
  private final NotificationService notificationService;
  private final ObjectMapper objectMapper;

  public UserController(@Qualifier("emailNotificationService") NotificationService notificationService, ObjectMapper objectMapper) {
    this.notificationService = notificationService;
    this.objectMapper = objectMapper;
  }

  @RequestMapping(value = "/join", method = RequestMethod.GET)
  public void createUser() {
    notificationService.sendNotification("반갑습니다!");
  }

  @RequestMapping(value = "/modify", method = RequestMethod.POST)
  public void modifyUser() {
    notificationService.sendNotification("수정되었습니다!");
  }

  @RequestMapping(value = "/json-test")
  public void jsonTest() {
    try {
      
      UserDTO dto = new UserDTO("김철수", 40);
      String jsonString = objectMapper.writeValueAsString(dto);
      System.out.println("생성된 JSON: " + jsonString);

      String inputJson = "{\"name\":\"김철수\",\"age\":40}";
      UserDTO resultDto = objectMapper.readValue(inputJson, UserDTO.class);
      System.out.println("생성된 DTO: " + resultDto);

    } catch (Exception e) {
      e.printStackTrace();
      System.err.println("[예외 발생 사유]: " + e.getMessage());
    }
  }
}
