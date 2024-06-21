package com.koeyh.backboard.validation;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberForm {

    @Size(min = 2, max = 40)
    @NotBlank(message = "사용자 이름은 필수입니다.")
    private String username;

    @Email  // Email 형식으로 입력받는 어노테이션
    @NotBlank(message = "E-mail 입력은 필수입니다.")
    private String email;
    
    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password1;   // 기본 패스워드

    @NotBlank(message = "비밀번호 확인은 필수입니다.")
    private String password2;   // 확인 패스워드
}
