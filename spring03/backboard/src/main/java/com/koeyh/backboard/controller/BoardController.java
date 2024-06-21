package com.koeyh.backboard.controller;

import java.security.Principal;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.koeyh.backboard.entity.Board;
import com.koeyh.backboard.entity.Member;
import com.koeyh.backboard.service.BoardService;
import com.koeyh.backboard.service.MemberService;
import com.koeyh.backboard.validation.BoardForm;
import com.koeyh.backboard.validation.ReplyForm;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;




@RequestMapping("/board")   // Restful URL은 /board로 시작
@Controller
@RequiredArgsConstructor
public class BoardController {

    // service는 중간 연결책 (controller <=> service <=> repository)
    private final BoardService boardService;    // 중간 연결책
    private final MemberService memberService;  // 사용자 정보

    // @RequestMapping("/list", method=RequestMethod.GET) // 아래와 동일한 기능
    // Model -> controller에 있는 객체를 view로 보내주는 역할을 하는 객체
    @GetMapping("/list")
    public String list(Model model, @RequestParam(value="page", defaultValue = "0") int page) {
        // List<Board> boardList = this.boardService.getList();
        // model.addAttribute("boardList", boardList);     // thymeleaf, mustache, jsp등 view로 보내는 기능

        Page<Board> paging = this.boardService.getList(page); // 페이징 된 Board 생성
        model.addAttribute("paging", paging);   // 페이징 된 Board를 view로 전달

        return "board/list";    // templates/board/list.html을 랜더링해서 반환하라.
    }
    // 댓글 검증기능을 추가하려면 매개변수로 ReplyForm을 필수적으로 전달해야 함.
    @GetMapping("/detail/{bno}")
    public String detail(Model model, @PathVariable("bno") Long bno, ReplyForm replyForm) {
        Board board = this.boardService.getBoard(bno);
        model.addAttribute("board", board);
        return "board/detail";
    }

    @PreAuthorize("isAuthenticated()")  // 로그인 시에만 사용 가능
    @GetMapping("/create")
    public String create(BoardForm boardForm) {
        
        return "board/create";
    }
    
    @PostMapping("/create")
    public String create(@Valid BoardForm boardForm, 
                          BindingResult bindingResult,
                          Principal principal) {
        if(bindingResult.hasErrors()) {
            return "board/create";  // 현재 html에 그대로 머무르기
        }
        Member writer = this.memberService.getMember(principal.getName());  // 현재 로그인 된 사용자 ID 받아오기
        // this.boardService.setBoard(title, content);
        this.boardService.setBoard(boardForm.getTitle(), boardForm.getContent(), writer );
        return "redirect:/board/list";
    }

    
}
