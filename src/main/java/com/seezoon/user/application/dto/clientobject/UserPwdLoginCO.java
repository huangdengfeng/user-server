package com.seezoon.user.application.dto.clientobject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPwdLoginCO {
    /**
     * 添加header Authorization:Bearer token
     */
    private String token;

    public UserPwdLoginCO(String token) {
        this.token = token;
    }
}
