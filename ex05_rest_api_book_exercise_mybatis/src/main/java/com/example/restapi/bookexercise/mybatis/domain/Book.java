package com.example.restapi.bookexercise.mybatis.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Book {
  private Long isbn;
  private Long userId; // FK
  private String title;
  private int price;
  private LocalDateTime publishedAt;
}
