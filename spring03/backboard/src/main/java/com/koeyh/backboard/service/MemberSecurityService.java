package com.koeyh.backboard.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.koeyh.backboard.entity.Member;
import com.koeyh.backboard.repository.MemberRepository;
import com.koeyh.backboard.security.MemberRole;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberSecurityService implements UserDetailsService{

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Member> _member = this.memberRepository.findByUsername(username);
        if(_member.isEmpty()) {
            throw new UsernameNotFoundException("사용자가 없습니다.");
        }
        Member member = _member.get();

        List<GrantedAuthority> authorities = new ArrayList<>();
        if("admin".equals(username)) {  // admin이라는 문자열과 username이 일치하면 (username이 일치할 때)
            authorities.add(new SimpleGrantedAuthority(MemberRole.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(MemberRole.USER.getValue()));
        }

        return new User(member.getUsername(), member.getPassword(), authorities);
    }

}
