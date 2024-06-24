package com.koeyh.backboard.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.koeyh.backboard.entity.Board;
import java.util.List;
import java.util.Optional;
// 페이징을 위한 namespace "중요 !"
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


// 인터페이스만 있어도 CRUD 가능 
@Repository
public interface BoardRepository extends JpaRepository<Board, Long>{
    
    // 메서드명만 "잘" 만들면 쿼리 쿼리 생성 없이 JPA가 쿼리를 생성해 주는 기능
    Optional<Board> findByBno(Long Bno);
    Board findByContent(String content);
    List<Board> findByTitleLike(String title);

    // 페이징용 JPA 쿼리 자동생성 인터페이스 메서드 작성
    @SuppressWarnings("null") // 경고 메시지 없애주는 어노테이션
    // select b1_0.bno,b1_0.content,b1_0.create_date,b1_0.title from board b1_0 offset 0 rows fetch first 10 rows only 쿼리를 만들어서 실행
    Page<Board> findAll(Pageable pageable);

    Page<Board> findAll(Specification<Board> spec, Pageable pageable);
}
