package com.example.request.requestbody.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.request.requestbody.dto.BookRequest;

@Controller
public class RequestController {

  @PostMapping("/api/v1/json-post")
  public void springJsonPost(@RequestBody BookRequest bookRequest) {
    System.out.println("JSON 수신 객체: " + bookRequest.toString());
  }
}
