package com.jojoldu.book.springbootdeveloper.controller;

import com.jojoldu.book.springbootdeveloper.dto.CreateAccessTokenRequest;
import com.jojoldu.book.springbootdeveloper.dto.CreateAccessTokenResponse;
import com.jojoldu.book.springbootdeveloper.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TokenApiController {

    private final TokenService tokenService;

    @PostMapping("/api/token")
    public ResponseEntity<CreateAccessTokenResponse> createToken(@RequestBody CreateAccessTokenRequest request) {
        String newAccessToken = tokenService.createToken(request.getRefreshToken());
        return ResponseEntity.status(HttpStatus.CREATED).body(new CreateAccessTokenResponse(newAccessToken));
    }

}
