package com.example.restapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restapi.dto.MemberRequest;
import com.example.restapi.dto.MemberResponse;
import com.example.restapi.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/members")
@RequiredArgsConstructor
@RestController
public class MemberApiController {

  private final MemberService memberService;

  // 1. 등록
  // @RequestBody가 없으면 form으로 전송된 데이터를 찾음 (후 새로고침) -> csr에서는 쓰면 안됨
  @PostMapping
  public ResponseEntity<MemberResponse> createMember(@RequestBody MemberRequest request) {
    MemberResponse savedMember = memberService.save(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedMember);
  }
}
