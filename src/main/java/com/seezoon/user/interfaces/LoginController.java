package com.seezoon.user.interfaces;

import com.seezoon.user.application.dto.WxMiniappLoginCmd;
import com.seezoon.user.application.dto.clientobject.WxMiniappLoginCO;
import com.seezoon.user.application.executor.WxMiniappLoginExe;
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

    @PostMapping("/wx_miniapp_login")
    public WxMiniappLoginCO wxMiniappLogin(@RequestBody WxMiniappLoginCmd cmd) {
        WxMiniappLoginCO co = wxMiniappLoginExe.execute(cmd);
        return co;
    }
}
