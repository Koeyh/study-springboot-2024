package com.koeyh.backboard.repository;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.koeyh.backboard.entity.Board;


@SpringBootTest
public class BoardRepositoryTests {
    
    // JUnit 테스트
    @Autowired
    private BoardRepository boardRepository;

    @Test
    void testInsertBoard() {

        // 전통적인 객체 생성 방식
        Board board1 = new Board(); 
        board1.setTitle("첫 번째 테스트입니다.");
        board1.setContent("첫 번째 컨텐츠입니다.");
        board1.setCreateDate(LocalDateTime.now());
        this.boardRepository.save(board1);

        // Builder를 사용한 객체 생성 방식
        Board board2 = Board.builder().title("두 번째 테스트입니다.")
                                    .content("두번째 내용")
                                    .createDate(LocalDateTime.now()).build();
        this.boardRepository.save(board2);
        System.out.println("Board 테스트 완료 !");
    }
}
