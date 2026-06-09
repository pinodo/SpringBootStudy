package com.example.valid.exception;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse(
  int status,
  String code,
  String message,
  List<FieldErrorDetail> errors,
  LocalDateTime timestamp
) {

  // 정적 팩토리 메서드 -> 객체를 만들어서 반환해주는 메서드 (naming: of, from, ...)
  public static ErrorResponse of(ErrorCode errorCode) {
    return new ErrorResponse(
      errorCode.getStatus().value(),
      errorCode.getCode(),
      errorCode.getMessage(),
      List.of(),
      LocalDateTime.now());
  }

  public static ErrorResponse of(ErrorCode errorCode, List<FieldErrorDetail> errors) {
    return new ErrorResponse(
      errorCode.getStatus().value(),
      errorCode.getCode(),
      errorCode.getMessage(),
      errors,
      LocalDateTime.now());
  }

  // Record in Record
  public record FieldErrorDetail(
    String field,
    String value,
    String reason
  ) { }
}
