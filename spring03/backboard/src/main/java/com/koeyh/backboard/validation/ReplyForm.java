package com.koeyh.backboard.validation;

import jakarta.validation.constraints.NotBlank;
// import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplyForm {

    // @NotEmpty(message = "댓글 내용을 입력하세요.")
    @NotBlank(message = "댓글 내용을 입력하세요.")
    private String content;
}
