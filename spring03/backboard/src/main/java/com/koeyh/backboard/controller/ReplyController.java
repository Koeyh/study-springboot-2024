package com.koeyh.backboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.koeyh.backboard.entity.Board;
import com.koeyh.backboard.service.BoardService;
import com.koeyh.backboard.service.ReplyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RequestMapping("/reply")
@RequiredArgsConstructor
@Controller
@Log4j2
public class ReplyController {

    private final BoardService boardService;
    private final ReplyService replyService;

    @PostMapping("/create/{bno}")
    public String create(Model model, @PathVariable("bno") Long bno, @RequestParam(value = "content") String content) throws Exception {
        
        Board board = this.boardService.getBoard(bno);
        this.replyService.setReply(board, content);
        log.info("ReplyController 댓글 저장 처리완료");
        return String.format("redirect:/board/detail/%s", bno);
    }
    
    
}
