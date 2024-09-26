package com.seezoon.user.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.seezoon.user.infrastructure.constants.Constants;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RecordCO {
    private Integer id;

    private Long uid;

    private Byte type;

    private String src;

    private String dst;

    private Byte status;
    @JsonFormat(pattern = Constants.DATETIME_PATTERN)
    private LocalDateTime createTime;
}
