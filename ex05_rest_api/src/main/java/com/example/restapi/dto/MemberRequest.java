package com.example.restapi.dto;

import java.time.LocalDateTime;

import lombok.Builder;

// record class에서는 setter가 생성이 안됨 -> field값: private final
@Builder
public record MemberRequest(
  Long id,
  String email,
  LocalDateTime createdAt) {
}
