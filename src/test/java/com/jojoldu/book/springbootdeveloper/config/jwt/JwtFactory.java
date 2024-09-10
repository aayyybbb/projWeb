package com.jojoldu.book.springbootdeveloper.config.jwt;

import java.time.Duration;
import java.util.Date;
import java.util.Map;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Builder;
import lombok.Getter;

import static java.util.Collections.emptyMap;

@Getter
public class JwtFactory {
    private String subject = "test@email.com";
    private Date issued = new Date();
    private Date expires = new Date(new Date().getTime() + Duration.ofDays(14).toMillis());
    private Map<String, Object> claims = emptyMap();

    @Builder
    public JwtFactory(String subject, Date issued, Date expires, Map<String, Object> claims){
        this.subject = subject != null ? subject : this.subject;
        this.issued = issued != null ? issued : this.issued;
        this.expires = expires != null ? expires : this.expires;
        this.claims = claims != null ? claims : emptyMap();
    }

    public static JwtFactory withDefaultValues(){
        return JwtFactory.builder().build();
    }

    public String createToken(JwtProperties properties){
        return Jwts.builder()
                .setSubject(subject)
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer(properties.getIssuer())
                .setIssuedAt(issued)
                .setExpiration(expires)
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS256, properties.getSecretKey())
                .compact();
    }
}
