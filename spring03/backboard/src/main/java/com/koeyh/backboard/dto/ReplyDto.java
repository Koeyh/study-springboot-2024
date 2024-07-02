package com.koeyh.backboard.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplyDto {

    private Long rno;

    private String content;

    private LocalDateTime createDate;

    private LocalDateTime modifyDate;  

    private String writer;

    // private List<ReplyDto> replyList;
}
