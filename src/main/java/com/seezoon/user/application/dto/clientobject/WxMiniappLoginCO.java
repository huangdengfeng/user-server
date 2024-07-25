package com.seezoon.user.application.dto.clientobject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WxMiniappLoginCO {
    /**
     * 添加header Authorization:Bearer token
     */
    private String token;

    public WxMiniappLoginCO(String token) {
        this.token = token;
    }
}
