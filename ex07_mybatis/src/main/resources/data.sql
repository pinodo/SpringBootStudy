-- data.sql (데이터 정의)

-- 사용자 초기 Mock Data
INSERT INTO users (email, nickname) VALUES
('user1@example.com', 'user1'),
('user2@example.com', 'user2'),
('user3@example.com', 'user3');

-- 게시글 초기 Mock Data
INSERT INTO posts (user_id, title, content) VALUES
(1, '스프링 스터디 모집', '함께 공부하실 분 모집합니다!'),
(1, 'MyBatis 알려 주실 분', 'MyBatis 알려 주시면 점심 사 드려요!'),
(2, 'REST API 고도화 전략', 'HTTP 상태 코드와 에러 응답의 표준을 정합니다!');