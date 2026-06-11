package com.example.ex05.restapi.bookexercisewithexception.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record BookCreateRequest(
  @NotNull(message = "isbn 입력은 필수 항목입니다.")
  @Positive(message = "isbn은 0보다 커야합니다.")
  Long isbn,
  
  @NotBlank(message = "제목 입력은 필수 항목입니다.")
  String title,

  @NotNull(message = "가격 입력은 필수 항목입니다.")
  @Positive(message = "isbn은 0보다 커야합니다.")
  Integer price
) {

}
