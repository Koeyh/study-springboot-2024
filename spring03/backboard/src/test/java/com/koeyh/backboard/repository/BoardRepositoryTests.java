package com.koeyh.backboard.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    @Test
    void testSelectBoard() {
        List<Board> all = this.boardRepository.findAll(); // select * from board;
        assertEquals(4, all.size());

        Board bd = all.get(0);  // 게시글 중 가장 첫번째 값
        assertEquals(1, bd.getBno());   // 첫 번째 게시글의 PK값이 1인지 확인
        System.out.println(bd.getBno());
        System.out.println(bd.getBno());
    }

    @Test
    void testUpdateBoard() {
        // Optional 사용 시 null 체크 용이
        Optional<Board> bd = this.boardRepository.findById(52L);
        assertTrue(bd.isPresent());     // bno가 1번인 게시물 객체가 넘어왔는 지 확인
        Board ubd = bd.get();
        ubd.setContent("테스트에서 수정합니다");
        this.boardRepository.save(ubd); // save() : id가 없으면 INSERT, 있으면 UPDATE 쿼리 자동 실행
        System.out.println("수정 완료");
    }

    @Test
    void testDeleteBoard() {
        Optional<Board> bd = this.boardRepository.findById(52L);
        assertTrue(bd.isPresent()); // 값이 있으면
        Board dbd = bd.get();
        this.boardRepository.delete(dbd);
        System.out.println("삭제 완료");
    }
}
