# 📋 Book-User 프로젝트 코드 작성 순서

> **핵심 원칙**: DB → 도메인 → DTO → 예외 → SQL → Mapper → Service → Controller
> 항상 **하위 계층부터** 작성합니다.

---

## 1. DB 테이블 생성 (SQL)

- `users` 테이블
- `book` 테이블 + FK 설정

---

## 2. Domain

- `User.java`
- `Book.java` — `user_id` 필드 포함

---

## 3. DTO

- `UserCreateRequest`
- `UserResponse`
- `BookCreateRequest` — `userId` 필드 포함
- `BookResponse` — `registeredBy` 필드 포함
- `BookUpdateRequest`

---

## 4. Exception

- `ErrorCode` — `USER_NOT_FOUND`, `BOOK_NOT_FOUND` 등
- `CustomException`
- `ErrorResponse`
- `GlobalExceptionHandler`

---

## 5. resources/mapper (SQL)

- `user.xml` — User CRUD SQL
- `book.xml` — Book CRUD + JOIN SQL

---

## 6. Mapper (Java 인터페이스)

- `UserMapper.java` — `user.xml` 메서드 선언
- `BookMapper.java` — `book.xml` 메서드 선언

---

## 7. Service

- `UserService.java` — UserMapper 호출
- `BookService.java` — BookMapper 호출, userId 유효성 검증

---

## 8. Controller

- `UserController.java`
- `BookController.java`

---

## 9. 테스트 (Postman)

- User CRUD 확인
- Book CRUD + JOIN 응답 확인