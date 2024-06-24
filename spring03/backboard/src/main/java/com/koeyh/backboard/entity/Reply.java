package com.koeyh.backboard.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter
@Entity 
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reply {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) // Oracle로 이관 예정
    private Long rno;

    @Column(name = "content", length = 4000)
    private String content;

    @CreatedDate
    @Column(name = "createDate", updatable = false)
    private LocalDateTime createDate;

    @LastModifiedDate
    @Column(name = "modifyDate")
    private LocalDateTime modifyDate;   // '24. 6. 24. 수정일자 추가

    // 중요 !!!! ERD로 DB를 설계하지 않고 Entity 클래스로 관계 형성 시 반드시 필요함
    // Relationship 다 대 일
    @ManyToOne
    private Board board;

    @ManyToOne
    private Member writer;
}
