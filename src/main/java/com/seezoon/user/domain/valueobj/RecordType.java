package com.seezoon.user.domain.valueobj;

import com.seezoon.user.infrastructure.error.ErrorCode;
import com.seezoon.user.infrastructure.exception.ExceptionFactory;

import java.util.Objects;

public enum RecordType {
    Photo((byte) 1),
    Video((byte) 2);
    private byte code;

    RecordType(byte code) {
        this.code = code;
    }

    public static RecordType convert(byte type) {
        if (Objects.equals(Photo.code, type)) {
            return Photo;
        } else if (Objects.equals(Video.code, type)) {
            return Video;
        }
        throw ExceptionFactory.bizException(ErrorCode.PARAM_INVALID.code(), "type must [1|2]");
    }

    public byte code() {
        return code;
    }
}
