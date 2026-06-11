package com.example.ex05.restapi.bookexercisewithexception.controller;

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

import com.example.ex05.restapi.bookexercisewithexception.dto.BookCreateRequest;
import com.example.ex05.restapi.bookexercisewithexception.dto.BookDto;
import com.example.ex05.restapi.bookexercisewithexception.dto.BookUpdateRequest;
import com.example.ex05.restapi.bookexercisewithexception.service.BookService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/v1/books")
@RestController
public class BookController {
  
  private final BookService bookService;

  // Save
  @PostMapping
  public ResponseEntity<String> saveBook(@Valid @RequestBody BookCreateRequest request) {
    bookService.save(request);
    return ResponseEntity.status(HttpStatus.CREATED).body("도서 등록 완료");
  }

  // Read All
  @GetMapping
  public ResponseEntity<List<BookDto>> findBookAll() {
    List<BookDto> books = bookService.findAll();
    return ResponseEntity.ok(books);
  }

  // Read One
  @GetMapping("/{id}")
  public ResponseEntity<String> findBookOne(@PathVariable Long isbn) {
    bookService.findById(isbn);
    return ResponseEntity.status(HttpStatus.FOUND).body("ISBN[" + isbn + "] 이 조회되었습니다.");
  }

  // Update
  @PutMapping("/{id}")
  public ResponseEntity<String> updateBook(
    @PathVariable Long isbn,
    @Valid @RequestBody BookUpdateRequest request
  ) {
    bookService.updateBook(isbn, request);
    return ResponseEntity.ok("도서 가격 수정 성공");
  }

  // Delete
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteBook(@PathVariable Long isbn) {
    bookService.deleteBook(isbn);
    return ResponseEntity.noContent().build();
  }
}
