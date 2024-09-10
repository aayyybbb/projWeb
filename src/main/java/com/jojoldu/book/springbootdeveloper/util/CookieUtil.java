package com.jojoldu.book.springbootdeveloper.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.SerializationUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Base64;

public class CookieUtil {

    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return;
        }

        for (Cookie cookie : cookies) {
            if(name.equals(cookie.getName())) {
                cookie.setValue("");
                cookie.setPath("/");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
    }

    public static String serialize(Object obj) {
        return Base64.getUrlEncoder().encodeToString(SerializationUtils.serialize(obj));
    }

    public static <T> T deserialize(Cookie cookie, Class<T> cls) {
        try {
                // 쿠키 값에서 Base64 디코딩하여 바이트 배열로 변환
                byte[] data = Base64.getUrlDecoder().decode(cookie.getValue());

                // 바이트 배열을 사용하여 객체를 역직렬화
                try (ByteArrayInputStream bis = new ByteArrayInputStream(data);
                     ObjectInputStream ois = new ObjectInputStream(bis)) {
                    // cls 타입으로 캐스팅하여 반환
                    return cls.cast(ois.readObject());
                }
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException("Failed to deserialize object", e);
            }
    }
}
