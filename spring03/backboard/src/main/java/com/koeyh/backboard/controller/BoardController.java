package com.koeyh.backboard.controller;

import java.security.Principal;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.koeyh.backboard.entity.Board;
import com.koeyh.backboard.entity.Category;
import com.koeyh.backboard.entity.Member;
import com.koeyh.backboard.service.BoardService;
import com.koeyh.backboard.service.CategoryService;
import com.koeyh.backboard.service.MemberService;
import com.koeyh.backboard.validation.BoardForm;
import com.koeyh.backboard.validation.ReplyForm;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.web.bind.annotation.PostMapping;





@RequestMapping("/board")   // Restful URL은 /board로 시작
@Controller
@RequiredArgsConstructor
@Log4j2
public class BoardController {

    // service는 중간 연결책 (controller <=> service <=> repository)
    private final BoardService boardService;    // 중간 연결책
    private final MemberService memberService;  // 사용자 정보
    private final CategoryService categoryService;  // 카테고리 사용

    // @RequestMapping("/list", method=RequestMethod.GET) // 아래와 동일한 기능
    // Model -> controller에 있는 객체를 view로 보내주는 역할을 하는 객체
    // @GetMapping("/list")
    // public String list(Model model, @RequestParam(value="page", defaultValue = "0") int page) {
    //     // List<Board> boardList = this.boardService.getList();
    //     // model.addAttribute("boardList", boardList);     // thymeleaf, mustache, jsp등 view로 보내는 기능

    //     Page<Board> paging = this.boardService.getList(page); // 페이징 된 Board 생성
    //     model.addAttribute("paging", paging);   // 페이징 된 Board를 view로 전달

    //     return "board/list";    // templates/board/list.html을 랜더링해서 반환하라.
    // }

    // '24. 6. 24. list 새로 변경
    // '24. 6. 26. URL입력으로 list 접근 시 자유게시판 띄우기
    @GetMapping("/list")
    public String list(Model model, @RequestParam(value="page", defaultValue = "0") int page,
                       @RequestParam(value = "kw", defaultValue = "") String keyword) {

        Page<Board> paging = this.boardService.getList(page, keyword); 
        model.addAttribute("paging", paging);   
        model.addAttribute("kw", keyword);
        return "board/list";    
    }

    // '24. 6. 25. 마지막, 카테고리 추가
    @GetMapping("/list/{category}")
    public String list(Model model, 
                      @PathVariable(value = "category") String category,
                      @RequestParam(value="page", defaultValue = "0") int page,
                      @RequestParam(value = "kw", defaultValue = "") String keyword) {

        Category cate = this.categoryService.getCategory(category); // cate는 Category 객체. 변수로 사용하면 안됨
        Page<Board> paging = this.boardService.getList(page, keyword, cate); // 검색 및 카테고리 추가
        model.addAttribute("paging", paging);   
        model.addAttribute("kw", keyword);
        model.addAttribute("category", category);

        return "board/list";    
    }

    // 댓글 검증기능을 추가하려면 매개변수로 ReplyForm을 필수적으로 전달해야 함.
    @GetMapping("/detail/{bno}")
    public String detail(Model model, @PathVariable("bno") Long bno, ReplyForm replyForm, HttpServletRequest request) {
        String prevUrl = request.getHeader("referer");  // 이전페이지 변수에 담기
        log.info(String.format("▶▶▶▶▶이전 페이지 : %s", prevUrl));
        // Board board = this.boardService.getBoard(bno);
        Board board = this.boardService.hitBoard(bno);  // '24. 6. 26. 조회수 증가 후 리턴
        model.addAttribute("board", board);
        model.addAttribute("prevUrl", prevUrl); // 이전 페이지 URL 뷰에 전달
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

    // 이후 
    // category 추가
    @PreAuthorize("isAuthenticated()")  // 로그인 시에만 사용 가능
    @GetMapping("/create/{category}")
    public String create(Model model, @PathVariable("category") String category, BoardForm boardForm) {
        
        model.addAttribute("category", category);
        return "board/create";
    }
    
    // category 추가
    @PreAuthorize("isAuthenticated()")  // 로그인 시에만 사용 가능
    @PostMapping("/create/{category}")
    public String create(Model model,
                         @PathVariable("category") String category,
                         @Valid BoardForm boardForm, 
                          BindingResult bindingResult,
                          Principal principal) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("category", category);
            return "board/create";  // 현재 html에 그대로 머무르기
        }
        Member writer = this.memberService.getMember(principal.getName());  // 현재 로그인 된 사용자 ID 받아오기
        // this.boardService.setBoard(title, content);
        Category cate = this.categoryService.getCategory(category);
        this.boardService.setBoard(boardForm.getTitle(), boardForm.getContent(), writer, cate );
        return String.format("redirect:/board/list/%s", category);
    }

    @PreAuthorize("isAuthenticated()") //로그인시만 사용 가능
    @GetMapping("/modify/{bno}")
    public String modify(BoardForm boardForm, @PathVariable("bno") Long bno, Principal principal) {
        Board board = this.boardService.getBoard(bno);  // 기존 게시글 데이터 가져옴
        
        if(!board.getWriter().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
        }

        boardForm.setTitle(board.getTitle());
        boardForm.setContent(board.getContent());
        return "board/create";      // 게시글 등록하는 화면을 수정 할때도 그대로 사용
    }
        
    // 같은 페이지(create.html)를 다른 용도로 사용할 수 있게 설정
    @PreAuthorize("isAuthenticated()") //로그인시만 사용 가능
    @PostMapping("/modify/{bno}")
    public String modify(@Valid BoardForm boardForm,
                          BindingResult bindingResult,
                          Principal principal,
                         @PathVariable("bno") Long bno) {
        
        if(bindingResult.hasErrors()) {
            return "board.create";
        }
        
        Board board = this.boardService.getBoard(bno);
        if(!board.getWriter().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
        }
        this.boardService.modBoard(board, boardForm.getTitle(), boardForm.getContent());
        return String.format("redirect:/board/detail/%s", bno);
    }

    // 게시물 삭제
    @PreAuthorize("isAuthenticated()") //로그인시만 사용 가능
    @GetMapping("/delete/{bno}")
    public String delete(@PathVariable("bno") Long bno, Principal principal) {
        Board board = this.boardService.getBoard(bno);
        if(!board.getWriter().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제 권한이 없습니다.");
        }
        this.boardService.remBoard(board);
        return "redirect:/";    // 메인화면으로 돌려보내기
    }
}
