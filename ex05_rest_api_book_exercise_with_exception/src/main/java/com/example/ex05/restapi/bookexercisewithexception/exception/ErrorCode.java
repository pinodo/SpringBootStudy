package com.example.ex05.restapi.bookexercisewithexception.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ErrorCode {

  // 400 Bad Request
  INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "C001", "Invalid input value."),

  // 404 Not Found
  BOOK_NOT_FOUND(HttpStatus.NOT_FOUND, "B001", "Book not found."),

  // 409 Conflict
  DUPLICATE_ISBN(HttpStatus.CONFLICT, "B002", "ISBN is already taken."),

  // 500 Internal Server Error
  INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "S001", "Internal server error occured.");

  private HttpStatus status;
  private String code;
  private String message;
  
  ErrorCode(HttpStatus status, String code, String message) {
    this.status = status;
    this.code = code;
    this.message = message;
  }
}
