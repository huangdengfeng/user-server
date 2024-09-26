package com.seezoon.user.application.executor;

import com.seezoon.user.application.dto.AddRecordCmd;
import com.seezoon.user.application.dto.clientobject.AddRecordCO;
import com.seezoon.user.domain.service.AddRecordService;
import com.seezoon.user.domain.valueobj.RecordType;
import com.seezoon.user.infrastructure.configuration.context.SecurityContextHolder;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@RequiredArgsConstructor
@Slf4j
@Component
@Validated
public class AddRecordCmdExe {

    private final AddRecordService addRecordService;

    public AddRecordCO execute(@Valid @NotNull AddRecordCmd cmd) {
        Integer id = addRecordService.add(SecurityContextHolder.getUid(), cmd.getRelativePath(), RecordType.convert(cmd.getType()));
        AddRecordCO co = new AddRecordCO();
        co.setId(id);
        return co;
    }
}
