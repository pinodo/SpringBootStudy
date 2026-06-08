package com.example.request.requestparam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RequestController {

  @GetMapping("/api/v1/request-param")
  public void springRequestParam(
    @RequestParam(name = "title") String boardTitle,
    @RequestParam(required = false, defaultValue = "1") int page
  ) {
    System.out.println("title: " + boardTitle + ", page: " + page);
  }
}
