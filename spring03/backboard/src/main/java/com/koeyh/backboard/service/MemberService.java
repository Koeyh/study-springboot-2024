package com.koeyh.backboard.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.koeyh.backboard.entity.Member;
import com.koeyh.backboard.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member setMember(String username, String email, String password) {
        
        Member member = Member.builder().username(username).email(email).build();
        // 패스워드 암호화
        BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder();
        member.setPassword(pwdEncoder.encode(password));    // 암호화 한 값을 BB에 보낼 준비
        this.memberRepository.save(member);

        return member;
    }
}
