package com.example.mybatis.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.mybatis.domain.Post;
import com.example.mybatis.dto.PageResponse;
import com.example.mybatis.dto.PostCreateRequest;
import com.example.mybatis.dto.PostResponse;
import com.example.mybatis.dto.PostUpdateRequest;
import com.example.mybatis.exception.CustomException;
import com.example.mybatis.exception.ErrorCode;
import com.example.mybatis.mapper.PostMapper;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true, rollbackFor = Exception.class)
@RequiredArgsConstructor
@Service
public class PostService {

  private final PostMapper postMapper;

  @Transactional
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
    // Return값: Optional<Post> -> 리턴받은 값을 .orElseThrow로 처리 가능
    // {returnedValue}.get(); // 100% 데이터가 존재할 때 사용
    // {returnedValue}.orElse(post); // 데이터가 NULL일 때 대신 사용할 객체 지정
    Post post = postMapper.findById(id)
      .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
    return PostResponse.from(post); // post로부터 PostResponse 얻기
  }

  public PageResponse<PostResponse> getPosts(int page, int size, String sort) {
    long offset = (page - 1) * size;
    long totalElements = postMapper.countAll();
    int totalPages = (int) Math.ceil((double)totalElements / size);

    List<Post> posts = postMapper.findAll(offset, size, sort);
    List<PostResponse> contents = posts.stream()
        .map(post -> PostResponse.from(post)) // .map(PostResponse::from) 메서드 참조
        .toList(); // .collect(Collectors.toList())
    
    return new PageResponse<>(contents, page, size, totalPages, totalElements, sort);
  }

  // Update
  @Transactional
  public PostResponse updatePost(Long id, PostUpdateRequest request) {
      Post post = postMapper.findById(id)
          .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
      post.setTitle(request.title());
      post.setContent(request.content());
      postMapper.update(post);
      return PostResponse.from(post);

    // PostResponse foundPost = findById(id);
    // Post updatedPost = Post.builder()
    //     .id(foundPost.id())
    //     .title(request.title())
    //     .content(request.content())
    //     .build();
    // postMapper.update(updatedPost);
    // return findById(updatedPost.getId());
    // return PostResponse.from(updatedPost);
  }
  
  // Delete
  @Transactional
  public void deletePost(Long id) {
    postMapper.findById(id)
        .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
    postMapper.deleteById(id);

    // postMapper.findById(id);
    // postMapper.deleteById(id);
  }
}
