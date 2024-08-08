package com.seezoon.user.interfaces;

import com.seezoon.user.application.dto.UserPwdLoginCmd;
import com.seezoon.user.application.dto.WxMiniappLoginCmd;
import com.seezoon.user.application.dto.clientobject.UserPwdLoginCO;
import com.seezoon.user.application.dto.clientobject.WxMiniappLoginCO;
import com.seezoon.user.application.executor.UserPwdLoginCmdExe;
import com.seezoon.user.application.executor.WxMiniappLoginCmdExe;
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

    private final WxMiniappLoginCmdExe wxMiniappLoginCmdExe;
    private final UserPwdLoginCmdExe userPwdLoginCmdExe;

    @PostMapping("/wx_miniapp_login")
    public Response<WxMiniappLoginCO> wxMiniappLogin(@RequestBody WxMiniappLoginCmd cmd) {
        WxMiniappLoginCO co = wxMiniappLoginCmdExe.execute(cmd);
        return Response.success(co);
    }

    @PostMapping("/user_pwd_login")
    public Response<UserPwdLoginCO> userPwdLogin(@RequestBody UserPwdLoginCmd cmd) {
        UserPwdLoginCO co = userPwdLoginCmdExe.execute(cmd);
        return Response.success(co);
    }
}
