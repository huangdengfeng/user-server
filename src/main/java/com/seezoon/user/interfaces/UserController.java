package com.seezoon.user.interfaces;

import com.seezoon.user.application.dto.clientobject.UserProfileCO;
import com.seezoon.user.application.executor.UserProfileQryExe;
import com.seezoon.user.infrastructure.dto.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserProfileQryExe userProfileQryExe;

    @GetMapping("/info")
    public Response<UserProfileCO> info() {
        UserProfileCO co = userProfileQryExe.execute();
        return Response.success(co);
    }
}
