package com.koeyh.backboard.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// Spring Security 핵심 파일 !
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // http://localhost:8080/** 로그인 하지 않고도 접근 가능한 권한을 주겠다고 명시
        http
            .authorizeHttpRequests((atr) -> atr.requestMatchers(new AntPathRequestMatcher("/**"))
                                            .permitAll())
            // CSRF 위/변조 공격을 막는 부분 해제, 특정 URL은 csrf공격 리스트에서 제거
            .csrf((csrf) -> csrf.ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**")))
            // h2-console 페이지가 frameset, frame으로 구성, CORS와 유사한 옵션 추가
            .headers((headers) -> headers
                .addHeaderWriter(new XFrameOptionsHeaderWriter(
                    XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN // ignoringRequestMatchers 영역에 있는 frame이니 해제하라는 명시
                )))
        ; // http.authorizeHttpRequests 완료

        return http.build();
    }
}
