package com.koeyh.backboard.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.koeyh.backboard.entity.Board;
import com.koeyh.backboard.entity.Reply;
import com.koeyh.backboard.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReplyService {

    private final ReplyRepository replyRepository;

    public void setReply(Board board, String content) {
        // 빌더를 이용한 방식
        Reply reply = Reply.builder().content(content).createDate(LocalDateTime.now()).board(board).build();
        this.replyRepository.save(reply);
    }
}
