package com.example.restapi.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.example.restapi.dto.MemberRequest;
import com.example.restapi.dto.MemberResponse;

@Service
public class MemberServiceImpl implements MemberService {

  // 인 메모리 데이터베이스
  private final Map<Long, MemberResponse> members = new ConcurrentHashMap<>();
  private final AtomicLong sequence = new AtomicLong();

  // Mock Data
  public MemberServiceImpl() {
    for (int i = 1; i <= 10; i++) {
      save(MemberRequest.builder()
          .email("member" + i + "test.com")
          .build());
    } // new MemberRequest(null, "member@test.com", null)
  }
  
  @Override
  public MemberResponse save(MemberRequest request) {
    Long id = sequence.incrementAndGet();
    String email = request.email();
    LocalDateTime createdAt = LocalDateTime.now();
    MemberResponse response = new MemberResponse(id, email, createdAt);
    members.put(id, response);
    return response;
  }

  @Override
  public List<MemberResponse> findAll() {
    return new ArrayList<>(members.values());
  }

  @Override
  public MemberResponse findById(Long id) {
    MemberResponse response = members.get(id);
    if (response == null) {
      throw new RuntimeException("존재하지 않는 회원 ID: " + id);
    }
    return response;
  }
  
  @Override
  public MemberResponse update(MemberRequest request, Long id) {
    MemberResponse foundMember = findById(id);
    // 수정 정보를 가진 MemberResponse 새로 생성 후 Map에 저장
    MemberResponse updatedMember = MemberResponse.builder()
        .id(id)
        .email(request.email())
        .createdAt(foundMember.createdAt())
        .build();
    return updatedMember;
  }

  @Override
  public void deleteById(Long id) {
    findById(id);
    members.remove(id);
  }
}
