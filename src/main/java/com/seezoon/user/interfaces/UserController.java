package com.seezoon.user.interfaces;

import com.seezoon.user.application.dto.AddRecordCmd;
import com.seezoon.user.application.dto.clientobject.AddRecordCO;
import com.seezoon.user.application.dto.clientobject.UserProfileCO;
import com.seezoon.user.application.executor.AddRecordCmdExe;
import com.seezoon.user.application.executor.UserProfileQryExe;
import com.seezoon.user.infrastructure.dto.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserProfileQryExe userProfileQryExe;
    private final AddRecordCmdExe addRecordCmdExe;

    @GetMapping("/info")
    public Response<UserProfileCO> info() {
        UserProfileCO co = userProfileQryExe.execute();
        return Response.success(co);
    }

    @PostMapping("/add_record")
    public Response<AddRecordCO> addRecord(@RequestBody AddRecordCmd cmd) {
        return Response.success(addRecordCmdExe.execute(cmd));
    }
}
