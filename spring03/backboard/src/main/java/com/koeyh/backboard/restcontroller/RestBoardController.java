package com.koeyh.backboard.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.koeyh.backboard.dto.BoardDto;
import com.koeyh.backboard.dto.ReplyDto;
import com.koeyh.backboard.entity.Board;
import com.koeyh.backboard.entity.Category;
import com.koeyh.backboard.entity.Reply;
import com.koeyh.backboard.service.BoardService;
import com.koeyh.backboard.service.CategoryService;
import com.koeyh.backboard.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@RequestMapping("/api/board")
@RestController
@Log4j2
public class RestBoardController {

    private final BoardService boardService;    // 중간 연결책
    private final CategoryService categoryService;  // 카테고리 사용

    @GetMapping("/list/{category}")
    @ResponseBody
    public List<BoardDto> list(@PathVariable(value = "category") String category,
                       @RequestParam(value="page", defaultValue = "0") int page,
                       @RequestParam(value = "kw", defaultValue = "") String keyword) {

        Category cate = this.categoryService.getCategory(category); // cate는 Category 객체. 변수로 사용하면 안됨
        Page<Board> paging = this.boardService.getList(page, keyword, cate); // 검색 및 카테고리 추가
        // List<Board> list = paging.getContent();
        
        List<BoardDto> result = new ArrayList<BoardDto>();
        // paging.forEach(brd -> result.add(BoardDto.builder().bno(brd.getBno()).title(brd.getTitle()).content(brd.getContent())
        //                                         .createDate(brd.getCreateDate()).modifyDate(brd.getModifyDate())
        //                                         .writer(brd.getWriter().getUsername())
        //                                         .hit(brd.getHit())
        //                                         // .replyList(new ArrayList<ReplyDto>().add())
        //                                         .build()));

        for (Board origin : paging) {
            List<ReplyDto> subList = new ArrayList<>();

            BoardDto bdDto = new BoardDto();
            bdDto.setBno(origin.getBno());
            bdDto.setTitle(origin.getTitle());
            bdDto.setContent(origin.getContent());
            bdDto.setCreateDate(origin.getCreateDate());
            bdDto.setModifyDate(origin.getModifyDate());
            bdDto.setWriter(origin.getWriter().getUsername());
            bdDto.setHit(origin.getHit());
            if(origin.getReplyList().size() > 0) { // Board의 댓글 리스트가 0보다 크면(존재하면)
                for(Reply reply : origin.getReplyList()) {
                    ReplyDto replyDto = new ReplyDto();
                    replyDto.setRno(reply.getRno());
                    replyDto.setContent(reply.getContent());
                    replyDto.setCreateDate(reply.getCreateDate());
                    replyDto.setModifyDate(reply.getModifyDate());
                    replyDto.setWriter(reply.getWriter().getUsername());

                    subList.add(replyDto);
                }

                bdDto.setReplyList(subList);
            }

            result.add(bdDto);
        }

        log.info(String.format("oooo Count of Board, trans list ooo", result.size()));
        // model.addAttribute("paging", paging);   
        // model.addAttribute("kw", keyword);
        // model.addAttribute("category", category);

        return result;
    }
}
