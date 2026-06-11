package com.example.ex05.restapi.bookexercisewithexception.dto;

import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record BookDto(
  Long isbn,
  String title,
  Integer price,
  LocalDateTime registeredAt
) { }
