package com.example.restapi.bookexercise.dto;

import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record BookDto(
  Long isbn,
  String title,
  int price,
  LocalDateTime addedToLibraryAt
) { }
