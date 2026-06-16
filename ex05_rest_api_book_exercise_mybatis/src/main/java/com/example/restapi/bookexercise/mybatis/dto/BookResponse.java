package com.example.restapi.bookexercise.mybatis.dto;

import java.time.LocalDateTime;

public record BookResponse(
  Long isbn,
  String title,
  Integer price,
  LocalDateTime publishedAt,
  Borrower borrower
) {
  
  public record Borrower(
    Long id,
    String email,
    String nickname
  ) { }

  // Get borrower information
  public static void getBorrower(Borrower borrower) {
    
  }
}
