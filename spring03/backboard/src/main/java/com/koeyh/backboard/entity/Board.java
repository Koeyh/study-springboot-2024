package com.koeyh.backboard.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

// 게시판 보드 테이블 entity
@Entity // 클래스를 테이블화
@Getter @Setter
@Builder // 객체 생성을 간략화
@NoArgsConstructor // 파라미터 없는 기본 생성자 자동 생성
@AllArgsConstructor // 멤버 변수 모두를 파라미터로 가지는 생성자 자동 생성
public class Board {
    
    @Id // primary key 컬럼임을 명시
    @GeneratedValue(strategy = GenerationType.SEQUENCE) // Oracle로 이관 예정
    private Long bno;
    
    @Column(name = "title", length = 250)
    private String title;

    @Column(name = "content", length = 4000)
    private String content;

    @CreatedDate
    @Column(name= "createDate" ,updatable = false) // 업데이트 없음
    private LocalDateTime createDate;

    @LastModifiedDate
    @Column(name = "modifyDate")
    private LocalDateTime modifyDate;   // '24. 6. 24. 수정일자 추가

    // 한 명의 사용자가 여러개의 게시글을 작성할 수 있다. 다대일 설정
    @ManyToOne
    private Member writer;

    // 중요 !!
    // Relationship 일 대 다
    // 게시글이 지워지면, 해당 게시글 번호를 가진 댓글도 함께 삭제됨
    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<Reply> replyList;

    @ManyToOne
    private Category category;  // free, qna로 구분해서 글 생성 가능
}