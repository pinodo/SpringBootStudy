# 회원 API 명세서

---

## 공통

| 항목         | 내용               |
| ------------ | ------------------ |
| Base URL     | `/api/members`     |
| Content-Type | `application/json` |

---

## API 목록

| 기능           | 메서드   | 엔드포인트          | 상태 코드                  |
| -------------- | -------- | ------------------- | -------------------------- |
| 회원 등록      | `POST`   | `/api/members`      | `201 Created`              |
| 회원 전체 조회 | `GET`    | `/api/members`      | `200 OK`                   |
| 회원 단건 조회 | `GET`    | `/api/members/{id}` | `200 OK` / `404 Not Found` |
| 회원 수정      | `PUT`    | `/api/members/{id}` | `200 OK`                   |
| 회원 삭제      | `DELETE` | `/api/members/{id}` | `204 No Content`           |

---

## 상세

### 회원 등록

```
POST /api/members
```

**요청 본문**

```json
{
  "email": "user@example.com"
}
```

**응답 본문**

```json
{
  "id": 1,
  "email": "user@example.com"
}
```

**응답 코드**

| 코드          | 설명           |
| ------------- | -------------- |
| `201 Created` | 회원 등록 성공 |

---

### 회원 전체 조회

```
GET /api/members
```

**요청 본문** : 없음

**응답 본문**

```json
[
  {
    "id": 1,
    "email": "user@example.com"
  },
  {
    "id": 2,
    "email": "user2@example.com"
  }
]
```

**응답 코드**

| 코드     | 설명      |
| -------- | --------- |
| `200 OK` | 조회 성공 |

---

### 회원 단건 조회

```
GET /api/members/{id}
```

**Path Variable**

| 파라미터 | 타입   | 설명    |
| -------- | ------ | ------- |
| `id`     | `Long` | 회원 ID |

**요청 본문** : 없음

**응답 본문**

```json
{
  "id": 1,
  "email": "user@example.com"
}
```

**응답 코드**

| 코드            | 설명      |
| --------------- | --------- |
| `200 OK`        | 조회 성공 |
| `404 Not Found` | 회원 없음 |

---

### 회원 수정

```
PUT /api/members/{id}
```

**Path Variable**

| 파라미터 | 타입   | 설명    |
| -------- | ------ | ------- |
| `id`     | `Long` | 회원 ID |

**요청 본문**

```json
{
  "email": "new@example.com"
}
```

**응답 본문**

```json
{
  "id": 1,
  "email": "new@example.com"
}
```

**응답 코드**

| 코드     | 설명      |
| -------- | --------- |
| `200 OK` | 수정 성공 |

---

### 회원 삭제

```
DELETE /api/members/{id}
```

**Path Variable**

| 파라미터 | 타입   | 설명    |
| -------- | ------ | ------- |
| `id`     | `Long` | 회원 ID |

**요청 본문** : 없음

**응답 본문** : 없음

**응답 코드**

| 코드             | 설명      |
| ---------------- | --------- |
| `204 No Content` | 삭제 성공 |
