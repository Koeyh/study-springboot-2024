package com.koeyh.backboard.validation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardForm {

    @Size(max=250)
    // @NotEmpty(message = "제목 입력 필수 !") // NotEmpty는 공백(스페이스)을 허용하니 사용하지 말자 !
    @NotBlank(message = "제목을 입력하세요")
    private String title;
    
    @NotBlank(message = "내용을 입력하세요")
    // @NotEmpty(message = "내용 입력 필수 !")
    private String content;

}
