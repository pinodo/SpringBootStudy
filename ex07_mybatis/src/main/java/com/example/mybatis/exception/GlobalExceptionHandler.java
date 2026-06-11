package com.example.mybatis.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException e) {
    BindingResult bindingResult = e.getBindingResult();
    List<ErrorResponse.FieldErrorDetail> fieldErrorDetails = bindingResult.getFieldErrors().stream()
        .map(error -> new ErrorResponse.FieldErrorDetail(
          error.getField(),
          error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
          error.getDefaultMessage()))
        .collect(Collectors.toList());
    
    ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, fieldErrorDetails);
    return new ResponseEntity<>(errorResponse, ErrorCode.INVALID_INPUT_VALUE.getStatus());
  }

  @ExceptionHandler(CustomException.class)
  public ResponseEntity<ErrorResponse> handleBookEntity(CustomException e) {
    ErrorCode errorCode = e.getErrorCode();
    ErrorResponse errorResponse = ErrorResponse.of(errorCode);
    return new ResponseEntity<>(errorResponse, errorCode.getStatus());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleException(Exception e) {
    ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR);
    return new ResponseEntity<>(errorResponse, ErrorCode.INTERNAL_SERVER_ERROR.getStatus());
  }
}
