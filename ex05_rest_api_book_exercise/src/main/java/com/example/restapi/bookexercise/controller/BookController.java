package com.example.restapi.bookexercise.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restapi.bookexercise.dto.BookRequest;
import com.example.restapi.bookexercise.dto.BookResponse;
import com.example.restapi.bookexercise.service.BookService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
@RestController
public class BookController {

  private final BookService bookService;

  // 1. 도서 전체 조회
  @GetMapping
  public ResponseEntity<List<BookResponse>> findBookAll() {
    List<BookResponse> books = bookService.findBookAll();
    return ResponseEntity.ok(books);
  }
  
  // 2. 새 도서 추가
  @PostMapping
  public ResponseEntity<String> addBook(@RequestBody BookRequest request) {
    bookService.addBook(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(request.title() + "도서 등록 완료");
  }

  // 3. 도서 가격 수정
  @PutMapping("/{id}")
  public ResponseEntity<String> updateBook(
    @PathVariable("id") Long id, 
    @RequestBody BookRequest request) {
    try {
      bookService.updateBook(id, request);
      return ResponseEntity.ok("도서 가격 수정 성공");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("isbn[" + id + "] 도서가 없음");
    }
  }

  // 4. 도서 하나 조회
  @GetMapping("/{id}")
  public ResponseEntity<BookResponse> findBookById(@PathVariable("id") Long id) {
    try {
      BookResponse foundBook = bookService.findBookById(id);
      return ResponseEntity.status(HttpStatus.FOUND).body(foundBook);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

  // 5. 도서 제거
  @DeleteMapping("/{id}")
  public void deleteBookById(@PathVariable("id") Long id) {
    bookService.removeBook(id);
  }
}
