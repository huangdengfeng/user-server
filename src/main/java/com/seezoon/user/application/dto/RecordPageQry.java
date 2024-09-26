package com.seezoon.user.application.dto;

import com.seezoon.user.infrastructure.dto.PageQuery;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecordPageQry extends PageQuery {

    /**
     * @see com.seezoon.user.domain.valueobj.RecordType
     */
    @NotNull
    private Byte type;

}
