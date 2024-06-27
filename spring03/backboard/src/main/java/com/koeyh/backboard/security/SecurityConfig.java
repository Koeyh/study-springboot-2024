package com.koeyh.backboard.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// Spring Security 핵심 파일 !
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true) // @PreAuthorize 사용 설정
public class SecurityConfig {
    
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // http://localhost:8080/** 로그인 하지 않고도 접근 가능한 권한을 주겠다고 명시
        http
            // 모두 접근 가능하지만 글 쓰기는 로그인 후에만 사용 가능
            .authorizeHttpRequests((atr) -> atr.requestMatchers(new AntPathRequestMatcher("/**"))
                                            .permitAll())
            // 로그인, 회원가입 페이지만 로그인 하지 않고도 접근 가능
            // .authorizeHttpRequests((atr) -> atr.requestMatchers(new AntPathRequestMatcher("/member/register"), 
            //                                                     new AntPathRequestMatcher("/member/login"))
            // .permitAll())
            // CSRF 위/변조 공격을 막는 부분 해제, 특정 URL은 csrf공격 리스트에서 제거
            // .csrf((csrf) -> csrf.ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**")))
            // '24. 6. 27. REST API 전달 시 403에러 발생
            .csrf((csrf) -> csrf.disable())
            // h2-console 페이지가 frameset, frame으로 구성, CORS와 유사한 옵션 추가
            .headers((headers) -> headers
                .addHeaderWriter(new XFrameOptionsHeaderWriter(
                    XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN // ignoringRequestMatchers 영역에 있는 frame이니 해제하라는 명시
                )))
            // 로그인, 로그아웃은 Spring Security에서 자동 생성 후 실행, 별도로 controller 태울 필요 없음
            // 로그인 url을 지정 ~/member/login, 로그인 성공 시 루트("/")로 패이지 변경
            .formLogin((fl) -> fl.loginPage("/member/login").defaultSuccessUrl("/"))
            // 로그아웃 처리
            .logout((logout) -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true))
        ; // http.authorizeHttpRequests 완료

        return http.build();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();     // 암호화를 빈으로 생성
    }
}
