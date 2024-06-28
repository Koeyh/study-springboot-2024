package com.koeyh.backboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.koeyh.backboard.entity.Member;
import com.koeyh.backboard.service.MailService;
import com.koeyh.backboard.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;



@RequestMapping("/mail")
@RequiredArgsConstructor
@Controller
@Log4j2
public class MailController {

    private final MemberService memberService;
    private final MailService mailService;

    @PostMapping("/reset-mail")
    public String reset_mail(Model model, @RequestParam("email") String email) {
        log.info(String.format("▶▶▶▶▶ reset.html에서 넘겨준 email : %s", email));

        // DB에서 메일 주소가 있는지 확인
        // 있으면 초기화 메일을 보내고, 없으면 에러 발생시키기
        try {
            Member member = this.memberService.getMemberByEmail(email);

            // 메일 전송
            Boolean result = this.mailService.sendResetPasswordEmail(member.getEmail());

            if(result) {
                log.info("▶▶▶▶▶ Reset Mail Transfer Success ◀◀◀◀");
                model.addAttribute("result", "초기화 메일 전송 성공");
            }
            else {
                model.addAttribute("result", "초기화 메일 전송 실패! 관리자에게 문의바랍니다");
            }
        } catch (Exception e) {
            model.addAttribute("result", "초기화 메일 발생 실패! 사용자가 없습니다");
        }

        return "member/reset_result";   // /templates/member/reset_result.html 생셩
    }
    
    
}
