package com.seezoon.user.interfaces;

import com.seezoon.user.application.dto.UserPwdLoginCmd;
import com.seezoon.user.application.dto.WxMiniappLoginCmd;
import com.seezoon.user.application.dto.clientobject.UserPwdLoginCO;
import com.seezoon.user.application.dto.clientobject.WxMiniappLoginCO;
import com.seezoon.user.application.executor.UserPwdLoginExe;
import com.seezoon.user.application.executor.WxMiniappLoginExe;
import com.seezoon.user.infrastructure.dto.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/public")
public class LoginController {

    private final WxMiniappLoginExe wxMiniappLoginExe;
    private final UserPwdLoginExe userPwdLoginExe;

    @PostMapping("/wx_miniapp_login")
    public Response<WxMiniappLoginCO> wxMiniappLogin(@RequestBody WxMiniappLoginCmd cmd) {
        WxMiniappLoginCO co = wxMiniappLoginExe.execute(cmd);
        return Response.success(co);
    }

    @PostMapping("/user_pwd_login")
    public Response<UserPwdLoginCO> userPwdLogin(@RequestBody UserPwdLoginCmd cmd) {
        UserPwdLoginCO co = userPwdLoginExe.execute(cmd);
        return Response.success(co);
    }
}
