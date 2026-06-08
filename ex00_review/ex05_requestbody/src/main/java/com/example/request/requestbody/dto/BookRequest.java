package com.example.request.requestbody.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookRequest {
  private String title;
  private int page;
  private String author;
}
