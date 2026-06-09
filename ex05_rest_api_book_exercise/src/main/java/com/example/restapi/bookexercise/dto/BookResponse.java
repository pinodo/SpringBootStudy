package com.example.restapi.bookexercise.dto;

import lombok.Builder;

@Builder
public record BookResponse(
  Long isbn,
  String title,
  int price
) { }
