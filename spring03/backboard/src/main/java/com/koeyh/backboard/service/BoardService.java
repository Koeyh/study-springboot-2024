package com.koeyh.backboard.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.koeyh.backboard.entity.Board;
import com.koeyh.backboard.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public List<Board> getList() {
        return this.boardRepository.findAll();
    }

    public Board getBoard(Long bno) throws Exception {
        Optional<Board> board = this.boardRepository.findById(bno);
        if(board.isPresent()) { // 데이터가 존재하면 반환
            return board.get();
        } else {                // 데이터가 없으면 예외 메시지 출력
            throw new Exception("board no found");
        }
    }
}
