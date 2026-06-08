package com.example.request.httpservlet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class RequestController {

  @RequestMapping(value = "/api/v1/legacy-get", method = RequestMethod.GET)
  public void legacyGet(HttpServletRequest request) {
    String title = request.getParameter("title");
    String pageStr = request.getParameter("page");
    int page = (pageStr != null) ? Integer.parseInt(pageStr) : 0;

    System.out.println("title: " + title + ", page: " + page);
  }
}
