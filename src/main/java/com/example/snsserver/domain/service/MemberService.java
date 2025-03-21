package com.example.snsserver.domain.service;

import com.example.snsserver.application.repository.MemberRepository;
import com.example.snsserver.domain.domain.Member;
import com.example.snsserver.domain.dto.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public MemberResponseDto getMyInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member member = memberRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new IllegalArgumentException("로그인 유저 정보가 없습니다."));
        return new MemberResponseDto(member);
    }

    @Transactional(readOnly = true)
    public MemberResponseDto getMemberInfo(String username) {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("유저 정보가 없습니다."));
        return new MemberResponseDto(member);
    }
}
