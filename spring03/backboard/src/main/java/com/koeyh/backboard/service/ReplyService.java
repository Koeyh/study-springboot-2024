package com.koeyh.backboard.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.koeyh.backboard.common.NotFoundException;
import com.koeyh.backboard.entity.Board;
import com.koeyh.backboard.entity.Member;
import com.koeyh.backboard.entity.Reply;
import com.koeyh.backboard.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReplyService {

    private final ReplyRepository replyRepository;

    // return값이 void에서 Reply타입으로 변경
    public Reply setReply(Board board, String content, Member writer) {
        // 빌더를 이용한 방식
        Reply reply = Reply.builder().content(content).createDate(LocalDateTime.now()).board(board).build();
        reply.setWriter(writer);
        this.replyRepository.save(reply);

        return reply;
    }

    public Reply getReply(Long rno) {
        Optional<Reply> reply = this.replyRepository.findById(rno);
        if(reply.isPresent())
            return reply.get();
        else
            throw new NotFoundException("Reply Not Found");
    }

    // 댓글 수정처리
    public void modReply(Reply reply, String content) {
        reply.setContent(content);
        reply.setModifyDate(LocalDateTime.now());

        this.replyRepository.save(reply);
    }

    // 댓글 삭제
    public void remReply(Reply reply) {
        this.replyRepository.delete(reply);
    }
}
