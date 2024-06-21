package com.koeyh.backboard.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import com.koeyh.backboard.security.MemberRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // ToString, NoArg.., Getter, Setter 포함
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long mid;

    @Column(unique = true, length = 100)
    private String username;

    @Column(unique = true, length = 150)
    private String email;

    private String password;

    @CreatedDate
    @Column(name= "regDate" ,updatable = false) // 업데이트 없음
    private LocalDateTime regDate;               // 회원가입일

    @Enumerated(EnumType.STRING)    // Enum 타입이 STRING인 이유 : "ROLE_ADMIN", "ROLE_USER"이기 떄문
    @Column(length = 12)
    private MemberRole role;
}