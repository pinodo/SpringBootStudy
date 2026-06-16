package com.example.springdatajpa.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springdatajpa.domain.Post;
import com.example.springdatajpa.dto.PostDto;
import com.example.springdatajpa.service.PostService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

  private final PostService postService;

  @PostMapping
  public ResponseEntity<Long> createPost(@RequestBody PostDto.Request request) {
    Long postId = postService.createPost(request.title(), request.content());
    return ResponseEntity.status(HttpStatus.CREATED).body(postId);
  }

  @GetMapping("/{id}")
  public ResponseEntity<PostDto.Response> getPost(@PathVariable("id") Long id) {
    Post post = postService.getPost(id);
    PostDto.Response response = PostDto.Response.from(post);
    return ResponseEntity.ok(response);
  }

  @GetMapping
  public ResponseEntity<Page<PostDto.Response>> getPosts(
      @RequestParam(name = "keyword", required = false) String keyword,
      @PageableDefault(size = 2) Pageable pageable) {
    Page<Post> posts = postService.getPosts(keyword, pageable);
    Page<PostDto.Response> responsePage = posts.map(PostDto.Response::from);
    return ResponseEntity.ok(responsePage);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> updatePost(
      @PathVariable("id") Long id,
      @RequestBody PostDto.Request request) {
    postService.updatePost(id, request.title(), request.content());
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePost(Long id) {
    postService.deletePost(id);
    return ResponseEntity.noContent().build();
  }

  @PostMapping("/{postId}/comments")
  public ResponseEntity<Void> addComment(
      @PathVariable("postId") Long postId,
      @RequestBody PostDto.CommentRequest request) {
    postService.addComment(postId, request.content());
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}
