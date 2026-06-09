package com.example.restapi.bookexercise.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restapi.bookexercise.dto.BookResponse;
import com.example.restapi.bookexercise.service.BookService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
@RestController
public class BookController {

  private final BookService bookService;

  // 1. 도서 전체 조회
  // @GetMapping
  // public ResponseEntity<List<BookResponse>> findBookAll() {
  //   List books = new ArrayList<>(bookService.findBookAll());
  //   return books;
  // }
  
  // 2. 새 도서 추가


  // 3. 도서 가격 수정


  // 4. 도서 하나 조회


  // 5. 도서 제거

}
