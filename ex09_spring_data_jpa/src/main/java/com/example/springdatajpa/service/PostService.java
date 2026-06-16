package com.example.springdatajpa.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springdatajpa.domain.Comment;
import com.example.springdatajpa.domain.Post;
import com.example.springdatajpa.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

  private final PostRepository postRepository;

  // 생성
  @Transactional // 읽기 전용이 아님
  public Long createPost(String title, String content) {
    Post post = new Post(title, content);
    return postRepository.save(post).getId();
  }

  // 단 건 조회
  public Post getPost(Long id) {
    return postRepository.findPostWithComments(id);
  }

  // 목록 조회 (페이징, 제목 키워드 포함)
  public Page<Post> getPosts(String keyword, Pageable pageable) {
    if (keyword != null && !keyword.isBlank()) {
      return postRepository.findByTitleContaining(keyword, pageable);
    }
    return postRepository.findAll(pageable);
  }

  // 수정 (조회 후(조회 결과가 영속화 됨) 엔티티 수정(변경 감지로 인해 UPDATE 자동 생성))
  @Transactional
  public void updatePost(Long id, String title, String content) {
    Post findPost = postRepository
        .findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
    findPost.updatePost(title, content);
  }

  // 삭제
  @Transactional
  public void deletePost(Long id) {
    Post findPost = postRepository
        .findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
    postRepository.delete(findPost);
  }

  // 댓글 등록 (댓글 or 첨부파일 비즈니스 로직 처리)
  @Transactional
  public void addComment(Long postId, String content) {
    Post post = postRepository.findById(postId)
        .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
    Comment comment = new Comment(content);
    post.addComment(comment);
  }
}
