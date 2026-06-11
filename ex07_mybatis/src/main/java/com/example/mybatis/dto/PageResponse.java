package com.example.mybatis.dto;

import java.util.List;

public record PageResponse<T>(
  List<T> contents,
  int page,
  int size,
  int totalPages,
  Long totalElements,
  String sort
  // column 정해서 정렬하기
) { }