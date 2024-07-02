package com.koeyh.backboard.dto;

import java.time.LocalDateTime;
import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {

    private Long bno;

    private String title;

    private String content;

    private LocalDateTime createDate;

    private LocalDateTime modifyDate;   

    private Integer hit; 

    private String writer;

    private List<ReplyDto> replyList;
}
