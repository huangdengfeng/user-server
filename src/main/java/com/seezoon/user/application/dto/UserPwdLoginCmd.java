package com.seezoon.user.application.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * 账号密码登录
 */
@Getter
@Setter
public class UserPwdLoginCmd {

    @NotEmpty
    private String username;
    @NotEmpty
    @Size(min = 6)
    private String password;
}
