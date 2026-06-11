package com.example.ex05.restapi.bookexercisewithexception.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.example.ex05.restapi.bookexercisewithexception.dto.BookCreateRequest;
import com.example.ex05.restapi.bookexercisewithexception.dto.BookDto;
import com.example.ex05.restapi.bookexercisewithexception.dto.BookUpdateRequest;
import com.example.ex05.restapi.bookexercisewithexception.exception.BookException;
import com.example.ex05.restapi.bookexercisewithexception.exception.ErrorCode;

@Service
public class BookService {
  
  private final Map<Long, BookDto> library = new ConcurrentHashMap<>();
  private final AtomicLong sequence = new AtomicLong(0);


  // Save
  public BookDto save(BookCreateRequest request) {
    // ISBN 중복 검증
    boolean isExistEmail = library.values().stream()
        .anyMatch(book -> book.isbn().equals(request.isbn()));
    if (isExistEmail) {
      throw new BookException(ErrorCode.DUPLICATE_ISBN);
    }

    Long id = sequence.incrementAndGet();
    BookDto book = BookDto.builder()
        .isbn(request.isbn())
        .title(request.title())
        .price(request.price())
        .registeredAt(LocalDateTime.now())
        .build();
    library.put(id, book);
    return book;
  }

  // Read All
  public List<BookDto> findAll() {
    return new ArrayList<>(library.values());
  }

  // Read One
  public BookDto findById(Long isbn) {
    BookDto foundBook = library.get(isbn);
    // 없는 책 예외 처리
    if (foundBook == null) {
      throw new BookException(ErrorCode.BOOK_NOT_FOUND);
    }
    return foundBook;
  }

  // Update
  public BookDto updateBook(Long isbn, BookUpdateRequest request) {
    BookDto foundBook = findById(isbn);
    BookDto updatedBook = BookDto.builder()
        .isbn(foundBook.isbn())
        .title(foundBook.title())
        .price(request.price())
        .build();
    library.put(isbn, updatedBook);
    return updatedBook;
  }

  // Remove
  public void deleteBook(Long isbn) {
    findById(isbn);
    library.remove(isbn);
  }
}
