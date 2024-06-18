package com.koeyh.backboard.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koeyh.backboard.entity.Board;
import com.koeyh.backboard.service.BoardService;

import lombok.RequiredArgsConstructor;


@RequestMapping("/board")   // Restful URL은 /board로 시작
@Controller
@RequiredArgsConstructor
public class BoardController {

    // service는 중간 연결책 (controller <=> service <=> repository)
    private final BoardService boardService;

    // @RequestMapping("/list", method=RequestMethod.GET) // 아래와 동일한 기능
    // Model -> controller에 있는 객체를 view로 보내주는 역할을 하는 객체
    @GetMapping("/list")
    public String list(Model model) {
        List<Board> boardList = this.boardService.getList();
        model.addAttribute("boardList", boardList);     // thymeleaf, mustache, jsp등 view로 보내는 기능
        return "board/list";    // templates/board/list.html을 랜더링해서 반환하라.
    }

    @GetMapping("/detail/{bno}")
    public String detail(Model model, @PathVariable("bno") Long bno) throws Exception{
        Board board = this.boardService.getBoard(bno);
        model.addAttribute("board", board);
        return "board/detail";

    }
    
}
