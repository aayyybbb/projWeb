package com.jojoldu.book.springbootdeveloper.controller;

import com.jojoldu.book.springbootdeveloper.dto.EmailRequest;
import com.jojoldu.book.springbootdeveloper.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vi/email")
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/send")
    public String sendEmail(@RequestBody EmailRequest email) throws MessagingException {
        emailService.sendEmail(email.getEmail());
        return "인증코드가 발송되었습니다.";
    }

    @PostMapping("/verify")
    public String verifyEmail(@RequestBody EmailRequest email) {
        boolean isVerify = emailService.verifyEmailCode(email.getEmail(), email.getVerifyCode());
        return isVerify ? "인증이 완료되었습니다." : "인증 실패하셨습니다.";

    }
}
