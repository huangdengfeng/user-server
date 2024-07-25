package com.seezoon.user.application.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

/**
 * 微信小程序登录
 */
@Getter
@Setter
public class WxMiniappLoginCmd {

    @NotEmpty
    private String code;
}
