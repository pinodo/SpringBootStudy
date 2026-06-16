package com.example.springdatajpa.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springdatajpa.dto.PostDto;

@RestController
public class PostController {

  @PostMapping
  public ResponseEntity<> createPost(@RequestBody PostDto postDto) {

  }

  @GetMapping("/{id}")
  public ResponseEntity<> getPost(@PathVariable("id") Long id) {

  }

  @GetMapping
  public ResponseEntity<> getPosts() {

  }

  @PutMapping
  public ResponseEntity<> updatePost() {

  }

  @DeleteMapping
  public ResponseEntity<Void> deletePost(Long id) {
    
    return ResponseEntity.noContent().build();
  }
}
