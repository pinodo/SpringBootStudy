package com.example.springdatajpa.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.example.springdatajpa.domain.Comment;
import com.example.springdatajpa.domain.Post;

public class PostDto {

  // Post 요청
  public record Request(
    String title,
    String content
  ) { }

  // Comment 요청
  public record CommentRequest(
    String content
  ) { }

  // 단건 조회 응답
  public record Response(
    Long id,
    String title,
    String content,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    List<Comment> comments
  ) {
    // 엔티티를 DTO로 변환
    public static Response from(Post post) {
      return new Response(
        post.getId(), 
        post.getTitle(), 
        post.getContent(), 
        post.getCreatedAt(), 
        post.getUpdatedAt(), 
        post.getComments());
    }
  }


}
