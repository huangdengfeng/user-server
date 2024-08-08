package com.seezoon.user.application.executor;

import com.seezoon.user.application.dto.UserPwdLoginCmd;
import com.seezoon.user.application.dto.clientobject.UserPwdLoginCO;
import com.seezoon.user.domain.dao.mapper.UserMapper;
import com.seezoon.user.domain.dao.po.UserPO;
import com.seezoon.user.domain.service.UserPasswdVerifyService;
import com.seezoon.user.infrastructure.error.ErrorCode;
import com.seezoon.user.infrastructure.exception.ExceptionFactory;
import com.seezoon.user.infrastructure.properties.AppProperties;
import com.seezoon.user.infrastructure.properties.LoginProperties;
import com.seezoon.user.infrastructure.rpc.jwt.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * 账号密码登录
 */
@RequiredArgsConstructor
@Slf4j
@Component
@Validated
public class UserPwdLoginCmdExe {

    private final UserPasswdVerifyService userPasswdVerifyService;
    private final AppProperties appProperties;
    private final JwtService jwtService;
    private final UserMapper userMapper;


    public UserPwdLoginCO execute(@Valid UserPwdLoginCmd cmd) {
        UserPO po = userMapper.selectByUsername(cmd.getUsername());
        if (null == po) {
            log.info("login user not exists [%s]", cmd.getUsername());
            throw ExceptionFactory.bizException(ErrorCode.USER_PWD_ERROR);
        }
        boolean verified = userPasswdVerifyService.verify(po.getUid(), cmd.getPassword());
        if (!verified) {
            throw ExceptionFactory.bizException(ErrorCode.USER_PWD_ERROR);
        }
        LoginProperties login = appProperties.getLogin();
        String token = jwtService.sign(String.valueOf(po.getUid()), login.getAccessTokenExpire());
        UserPwdLoginCO co = new UserPwdLoginCO(token);
        return co;
    }
}
