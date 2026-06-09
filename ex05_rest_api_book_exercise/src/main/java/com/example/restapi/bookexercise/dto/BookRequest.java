package com.example.restapi.bookexercise.dto;

import lombok.Builder;

@Builder
public record BookRequest(
  Long isbn,
  String title,
  int price
) { }
