package com.jojoldu.book.springbootdeveloper.dto;

import lombok.Getter;

@Getter
public class EmailRequest {
    private String email;
    private String verifyCode;
}
