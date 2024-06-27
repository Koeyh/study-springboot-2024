package com.koeyh.backboard.service;

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

    @Value("${spring.mail.username}")
    private String from;

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
            mmh.setText(message);
            // 이메일 발신자 설정
            mmh.setFrom(new InternetAddress(from));
            // 이메일 전송
            javaMailSender.send(mimeMessage);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
