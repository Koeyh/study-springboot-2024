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

    // 새로운 사용자 생성
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
    
    // 기존 사용자 비밀번호 초기화
    public void setMember(Member member) {
        member.setPassword(passwordEncoder.encode(member.getPassword()));   // BCrypt 암호화를 안하고 실행하니 에러 발생 !
        this.memberRepository.save(member); // 파라미터로 받은 멤버 저장
    }
    
    // 사용자명으로 사용자 정보 가져오는 메서드
    public Member getMember(String username) {
        Optional<Member> member = this.memberRepository.findByUsername(username);
        if (member.isPresent()) {
            return member.get();
        } else {
            throw new NotFoundException("Member not Found !");
        }
    }

    // '24. 6. 28. 추가
    // Email로 사용자 정보 검색, 가져오는 메서드
    public Member getMemberByEmail(String email) {
        Optional<Member> member = this.memberRepository.findByEmail(email);
        if(member.isPresent()) {
            return member.get();
        } else {
            throw new NotFoundException("Member Not Found");
        }
    }

}
