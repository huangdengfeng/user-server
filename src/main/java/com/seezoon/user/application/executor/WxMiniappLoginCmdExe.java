package com.seezoon.user.application.executor;

import com.seezoon.user.application.dto.WxMiniappLoginCmd;
import com.seezoon.user.application.dto.clientobject.WxMiniappLoginCO;
import com.seezoon.user.domain.dao.mapper.OauthMapper;
import com.seezoon.user.domain.dao.po.OauthPO;
import com.seezoon.user.domain.service.RegistUserService;
import com.seezoon.user.domain.valueobj.OauthType;
import com.seezoon.user.infrastructure.error.ErrorCode;
import com.seezoon.user.infrastructure.exception.Assertion;
import com.seezoon.user.infrastructure.exception.ExceptionFactory;
import com.seezoon.user.infrastructure.properties.AppProperties;
import com.seezoon.user.infrastructure.properties.LoginProperties;
import com.seezoon.user.infrastructure.rpc.jwt.JwtService;
import com.seezoon.user.infrastructure.rpc.wx.Code2SessionService;
import com.seezoon.user.infrastructure.rpc.wx.dto.Code2SessionResp;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * 微信小程序登录
 */
@RequiredArgsConstructor
@Slf4j
@Component
@Validated
public class WxMiniappLoginCmdExe {

    private final Code2SessionService code2SessionService;
    private final AppProperties appProperties;
    private final JwtService jwtService;
    private final RegistUserService registUserService;
    private final OauthMapper oauthMapper;

    public WxMiniappLoginCO execute(@Valid @NotNull WxMiniappLoginCmd cmd) {
        LoginProperties login = appProperties.getLogin();
        Code2SessionResp resp = code2SessionService.execute(login.getWxAppId(), login.getWxSecret(), cmd.getCode());
        if (!resp.success()) {
            log.error("call wx code2session error [{}] [{}]", resp.getErrcode(), resp.getErrmsg());
            throw ExceptionFactory.bizException(ErrorCode.WX_ERROR);
        }
        if (StringUtils.isEmpty(resp.getOpenid())) {
            log.error("get wx openId is empty code{}", cmd.getCode());
            throw ExceptionFactory.bizException(ErrorCode.WX_ERROR);
        }
        Long uid = null;
        OauthPO oauthPO = oauthMapper.selectByOauth(OauthType.WX.type(), resp.getOpenid());
        if (null != oauthPO) {
            uid = oauthPO.getUid();
        } else {
            uid = registUserService.execute(OauthType.WX, resp.getOpenid(), resp.getUnionid());
        }
        Assertion.notNull(uid, "uid must not null");
        String token = jwtService.sign(String.valueOf(uid), login.getAccessTokenExpire());
        WxMiniappLoginCO co = new WxMiniappLoginCO(token);
        return co;
    }
}
