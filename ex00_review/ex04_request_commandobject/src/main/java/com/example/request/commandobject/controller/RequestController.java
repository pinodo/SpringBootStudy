package com.example.request.commandobject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.request.commandobject.dto.BookRequest;

@Controller
public class RequestController {

  @GetMapping("/api/v1/command-object")
  public void springCommandObject(BookRequest bookRequest) {
    System.out.println("수신 Command Object: " + bookRequest.toString());
  }
}
