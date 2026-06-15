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
public class User {
  private Long id; // PK
  private String email;
  private String nickname;
  private LocalDateTime createdAt;
}
