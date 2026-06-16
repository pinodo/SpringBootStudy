package com.example.restapi.bookexercise.mybatis.dto;

import jakarta.validation.constraints.NotNull;

public record BookUpdateRequest(
  Long isbn,
  String title,

  @NotNull(message = "가격 입력은 필수 항목입니다.")
  Integer price
) { }
