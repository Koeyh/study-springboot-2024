package com.koeyh.backboard.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

// 복합쿼리 생성용
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import com.koeyh.backboard.common.NotFoundException;
import com.koeyh.backboard.entity.Board;
import com.koeyh.backboard.entity.Member;
import com.koeyh.backboard.entity.Reply;
import com.koeyh.backboard.repository.BoardRepository;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public List<Board> getList() {
        return this.boardRepository.findAll();
    }

    // 페이징 되는 리스트 메서드
    public Page<Board> getList(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts)); // pazesize를 동적으로도 변경 가능
        return this.boardRepository.findAll(pageable);
    }

    // '24. 6. 24. 검색 기능 추가된 메서드
    public Page<Board> getList(int page, String keyword) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts)); // pazesize를 동적으로도 변경 가능

        Specification<Board> spec = searchBoard(keyword);
        return this.boardRepository.findAll(spec, pageable);
    }

    public Board getBoard(Long bno) {
        Optional<Board> board = this.boardRepository.findByBno(bno);
        if (board.isPresent()) {    // 데이터가 존재하면
            return board.get();
        } else {
            throw new NotFoundException("board not found");
        }
    }

        // '24. 06. 18. setBoard 작성
        // '24. 06. 21. Member 추가
    public void setBoard(String title, String content, Member writer) {
        // 빌더로 생성한 객체
        Board board = Board.builder().title(title).content(content).createDate(LocalDateTime.now()).build();
        board.setWriter(writer);
        this.boardRepository.save(board);   // PK가 없으면 INSERT
    }

    // '24. 6. 24. Board 수정 메서드 modBoard 추가
    public void modBoard(Board board, String title, String content) {
        board.setTitle(title);
        board.setContent(content);
        board.setModifyDate(LocalDateTime.now());   // 수정 일시 추가

        this.boardRepository.save(board);   // 이미 PK가 있으면 UPDATE
    }

    public void remBoard(Board board) {
        this.boardRepository.delete(board);
    }

    // 검색 쿼리 대신 검색기능 적용
    public Specification<Board> searchBoard(String keyword) {
        return new Specification<Board>() {
            private static final Long serialVersionUID = 1L;    // 필요한 값이라 추가

            @Override
            @Nullable
            public Predicate toPredicate(Root<Board> b, CriteriaQuery<?> query, CriteriaBuilder cb) {
                // 쿼리를 JPA로 생성
                query.distinct(true); // 중복 제거 설정(DISTINCT)
                Join<Board, Reply> r = b.join("replyList", JoinType.LEFT);  // LEFT OUTER JOIN 설정


                return cb.or(cb.like(b.get("title"), "%" + keyword + "%"),      // 게시글 제목에서 검색
                             cb.like(b.get("content"), "%" + keyword + "%"),    // 게시글 내용에서 검색
                             cb.like(r.get("content"), "%" + keyword + "%")
                             );   // 댓글 내용에서 검색
            }
        };
    } 
}
