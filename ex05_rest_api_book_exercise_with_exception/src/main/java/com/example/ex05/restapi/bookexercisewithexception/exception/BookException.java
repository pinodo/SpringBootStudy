package com.example.ex05.restapi.bookexercisewithexception.exception;

import lombok.Getter;

@Getter
public class BookException extends RuntimeException {

  private final ErrorCode errorCode;

  public BookException(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
  }
}
