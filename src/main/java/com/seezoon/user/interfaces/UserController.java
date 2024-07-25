package com.seezoon.user.interfaces;

import com.seezoon.user.infrastructure.configuration.context.SecurityContextHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/info")
    public String info() {
        return SecurityContextHolder.getUid() + "";
    }
}
