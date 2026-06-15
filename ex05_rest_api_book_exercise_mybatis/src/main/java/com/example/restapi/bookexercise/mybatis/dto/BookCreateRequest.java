package com.example.restapi.bookexercise.mybatis.dto;

public record BookCreateRequest(
  Long isbn,
  String title,
  Integer price
) {

}
