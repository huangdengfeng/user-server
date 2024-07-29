package com.seezoon.user.application.dto.clientobject;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.seezoon.user.infrastructure.constants.Constants;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class UserProfileCO {

    private Long uid;

    private String nickName;

    private String name;

    private String mobile;

    private String avatar;

    private String email;
    
    @JsonFormat(pattern = Constants.DATE_PATTERN)
    private LocalDate birthday;

    private String address;

    @JsonFormat(pattern = Constants.DATETIME_PATTERN)
    private LocalDateTime createTime;

}
