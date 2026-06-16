package com.example.springdatajpa.domain;

import java.util.ArrayList;
import java.util.List;

import com.example.springdatajpa.common.BaseTimeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 부모 엔티티

@Entity
@Table(name = "posts")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Post extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 100)
  private String title;

  @Column(nullable = false, columnDefinition = "TEXT")
  private String content;

  @OneToMany(mappedBy = "post")
  private List<Comment> comments = new ArrayList<>();

  // 엔티티를 생성하는 방법은 생성자, 빌더 패턴, 정적 메서드 패턴 등 무엇을 활용하든 OK
  public Post(String title, String content) {
    this.title = title;
    this.content = content;
  }

  // 정적 메서드 패턴
  // public static Post createPost(String title, String content) {
  //   Post post = new Post();
  //   ...
  //   return post;
  // }

  // 비즈니스 메서드
  public void addComment(Comment comment) {
    this.comments.add(comment);
    comment.setPost(this);
  }

  // 변경 감지를 위한 비즈니스 메서드
  public void updatePost(String title, String content) {
    this.title = title;
    this.content = content;
  }
}
