package com.example.mybatis.dto;

import jakarta.validation.constraints.NotBlank;

public record PostUpdateRequest(
  @NotBlank(message = "게시글 제목은 필수 항목입니다.")
  String title,
  String content
) { }
