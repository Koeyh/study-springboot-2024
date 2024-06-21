package com.koeyh.backboard.controller;

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koeyh.backboard.entity.Board;
import com.koeyh.backboard.entity.Member;
import com.koeyh.backboard.service.BoardService;
import com.koeyh.backboard.service.MemberService;
import com.koeyh.backboard.service.ReplyService;
import com.koeyh.backboard.validation.ReplyForm;

import jakarta.validation.Valid;
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
    private final MemberService memberService;  // 작성자 입력을 위해 추가

    // Principal 객체를 추가하면 로그인 한 사용자명(Member객체)을 알 수 있음.
    @PreAuthorize("isAuthenticated()")  // 로그인 시에만 작성 가능
    @PostMapping("/create/{bno}")
    public String create(Model model, @PathVariable("bno") Long bno, 
                         @Valid ReplyForm replyForm, BindingResult bindingResult, 
                         Principal principal) throws Exception {

        Board board = this.boardService.getBoard(bno);
        Member writer = this.memberService.getMember(principal.getName());  // 중요 !! 지금 로그인중인 사용자의 ID를 가져온다
        if(bindingResult.hasErrors()) {
            model.addAttribute("board", board);
            return "board/detail";
        }
        this.replyService.setReply(board, replyForm.getContent(), writer);
        log.info("ReplyController 댓글 저장 처리완료");
        return String.format("redirect:/board/detail/%s", bno);
    }
        
}
