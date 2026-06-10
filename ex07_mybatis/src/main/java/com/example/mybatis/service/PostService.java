package com.example.mybatis.service;

import org.springframework.stereotype.Service;

import com.example.mybatis.domain.Post;
import com.example.mybatis.dto.PostCreateRequest;
import com.example.mybatis.dto.PostResponse;
import com.example.mybatis.mapper.PostMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostService {

  private final PostMapper postMapper;

  public PostResponse createPost(PostCreateRequest request) {
    Post post = Post.builder()
        .userId(request.userId()) // FK
        .title(request.title())
        .content(request.content())
        .build();

    // 제약 조건 위배를 대비한 코드 필요
    postMapper.save(post); // INSERT -> useGeneratedKeys="true" -> PK (AUTO_INCREMENT) 생성됨

    // post에 저장되는 정보: userId, title, content, id
    // 1대다 관계일 경우(한 게시글 여러 파일 첨부 시)

    // post 리턴 시 createAt 제회한 모든 값 리턴 가능
    // createdAt을 꼭 채워서 리턴하고 싶다면, post의 id를 이용해 select한 뒤 그 결과를 반환함
    return findById(post.getId());
  }

  public PostResponse findById(Long id) {
    return null;
  }
}
