package com.seezoon.user.infrastructure.properties;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;

/**
 * 登录相关参数
 *
 * @author huangdengfeng
 * @date 2023/9/8 16:30
 */
@Getter
@Setter
public class LoginProperties {

    /**
     * 登录有效期,默认两小时
     */
    @NotNull
    private Duration accessTokenExpire = Duration.ofHours(2);

    /**
     * session 服务器接入点
     */
    @NotEmpty
    private String sessionEndpoint;
    @NotEmpty
    private String wxAppId;
    @NotEmpty
    private String wxSecret;

}
