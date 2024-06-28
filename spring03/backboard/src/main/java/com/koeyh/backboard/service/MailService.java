package com.koeyh.backboard.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MailService {

    private final JavaMailSender javaMailSender;
    // private final PasswordEncoder passwordEncoder;
    
    // 메일에서 초기화 할 화면으로 이동하기 위한 URL
    private String resetPassUrl = "http://localhost:8080/member/reset-password";

    @Value("${spring.mail.username}")
    private String from;

    // 중복되지 않는 ID 생성
    private String makeUuid() {
        return UUID.randomUUID().toString();
    }

    // 기본 메일 보내는 메서드
    public void sendMail(String to, String subject, String message) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage(); // MIME type 설정

        try {
            // MimeMessageHelper로 MimeMessage 구성, 이메일에 작성되는 글은 UTF-8로 작성됨
            MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, false, "utf-8");
            // 이메일 수신자 설정
            mmh.setTo(to);
            // 이메일 제목 설정
            mmh.setSubject(subject);
            // 이메일 본문 내용 설정
            mmh.setText(message, true);
            // 이메일 발신자 설정
            mmh.setFrom(new InternetAddress(from));
            // 이메일 전송
            javaMailSender.send(mimeMessage);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    // 패스워드 초기화 메일 발송 메서드(초기화 중 핵심기능)
    public Boolean sendResetPasswordEmail(String email) {

        String uuid = makeUuid();
        String subject = "요청하신 비밀번호 재설정입니다.";
        String message = "backboard" + "<br><br>" + "아래의 링크를 클릭하면 비밀번호 재설정 페이지로 이동합니다." + "<br>"
                       + "<a href='" + resetPassUrl + "/" + uuid + "'>"
                       + resetPassUrl + "/" + uuid + "</a>" + "<br><br>";
        
        try {
            sendMail(email, subject, message);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}
