package com.seezoon.user.application.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddRecordCmd {
    /**
     * @see com.seezoon.user.domain.valueobj.RecordType
     */
    @NotNull
    private Byte type;
    @NotEmpty
    private String relativePath;
}
