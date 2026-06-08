package com.example.response.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.response.dto.UserResponse;

@RestController
@RequestMapping("/api/users")
public class ResponseController {

  @GetMapping("/v1")
  public String responseString() {
    String jsonString = "{\name\":\"홍길동\",\"age\":30}";
    return jsonString;
  }

  @GetMapping("/v2")
  public UserResponse reponseObject() {
    return new UserResponse("Samantha", 40);
  }

  @GetMapping("/v3")
  public ResponseEntity<Map<String, String>> responseEntity() {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "권한 없음"));
  }
}
