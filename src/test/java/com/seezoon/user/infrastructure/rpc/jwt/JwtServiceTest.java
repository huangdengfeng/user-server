package com.seezoon.user.infrastructure.rpc.jwt;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

@SpringBootTest
class JwtServiceTest {

    @Autowired
    private JwtService jwtService;

    @Test
    void sign() {
        String test = jwtService.sign("test", Duration.ofHours(2));
        System.out.println(test);
    }

    @Test
    void verfiy() {
        String verify = jwtService.verify("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3MjA0ODU2NDksInN1YiI6InRlc3QifQ.mAeYZJZnY29xrDtr-ZO6JA7zXDUWGbMEsC7SG5d8ZhY");
        System.out.println(verify);
    }
}