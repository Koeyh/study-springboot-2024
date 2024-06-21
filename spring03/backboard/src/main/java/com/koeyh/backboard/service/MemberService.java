package com.koeyh.backboard.service;

import java.time.LocalDateTime;
import java.util.Optional;

// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.koeyh.backboard.common.NotFoundException;
import com.koeyh.backboard.entity.Member;
import com.koeyh.backboard.repository.MemberRepository;
import com.koeyh.backboard.security.MemberRole;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Member setMember(String username, String email, String password) {
        
        Member member = Member.builder().username(username).email(email).regDate(LocalDateTime.now()).build();

        // 처리되는 일이 많아서 1~2초의 시간이 소모될 때, 하단에 따로 적용
        // 패스워드 암호화
        // BCryptPasswordEncoder를 매번 새롭게 객체 생성을 하기보다
        // Bean을 등록 해 놓고 사용하는 방식이 더 권장됨.(유지보수 방면에서 더 좋음)        

        // BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder();
        member.setPassword(passwordEncoder.encode(password));    // 암호화 한 값을 BB에 보낼 준비
        // member.setRegDate(LocalDateTime.now());  // 이렇게 하단에 따로 적용해야 시간 차이가 식별되지 않는 정도로 저장됨.
        member.setRole(MemberRole.USER);    // 일반 사용자 권한 부여
        this.memberRepository.save(member);

        return member;
    }

    //
    public Member getMember(String username) {
        Optional<Member> member = this.memberRepository.findByUsername(username);
        if (member.isPresent()) {
            return member.get();
        } else {
            throw new NotFoundException("Member not Found !");
        }
    }
}