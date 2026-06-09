package com.example.restapi.bookexercise.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.example.restapi.bookexercise.dto.BookRequest;
import com.example.restapi.bookexercise.dto.BookResponse;

@Service
public class BookService {

  private final AtomicLong sequence = new AtomicLong(0);
  private final Map<Long, BookResponse> books = new ConcurrentHashMap<>();

  public BookService() {
    for (int i = 1; i <= 10; i++) {
      addBook(BookRequest.builder()
      .isbn((long) i)
      .title("book" + i)
      .price((int) Math.random() * 10000)
      .build());   
      // BookResponse book = BookResponse.builder()
      //     .isbn(sequence.incrementAndGet())
      //     .title("book" + i)
      //     .price((int) Math.random() * 10000)
      //     .build();
      // addBook(book);
    }
  }
  
  // 1. 도서 전체 조회
  public List<BookResponse> findBookAll() {
    return new ArrayList<>(books.values());
  }

  // 2. 새 도서 추가
  public BookResponse addBook(BookRequest request) {
    Long isbn = (long) sequence.incrementAndGet();
    String title = request.title();
    int price = request.price();
    BookResponse addedBook = new BookResponse(isbn, title, price);
    books.put(isbn, addedBook);
    return addedBook;
    // BookResponse addedBook = BookResponse.builder()
    //     .isbn(request.isbn())
    //     .title(request.title())
    //     .price(request.price())
    //     .build();
    // books.put(sequence.incrementAndGet(), addedBook);
    // return addedBook;
  }

  // 3. 도서 가격 수정
  public BookResponse updateBook(Long isbn, BookRequest request) {
    findBookById(isbn);
    BookResponse updatedBook = BookResponse.builder()
      .price(request.price())
      .build();
    return updatedBook;
  }

  // 4. 도서 하나 조회
  public BookResponse findBookById(Long isbn) {
    BookResponse foundBook = books.get(isbn);
    if (foundBook == null) {
      throw new RuntimeException("찾는 책이 없습니다.");
    }
    return foundBook;
  }

  // 5. 도서 제거
  public void removeBook(Long isbn) {
    books.remove(isbn);
  }
}
