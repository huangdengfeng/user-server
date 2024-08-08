package com.seezoon.user.domain.valueobj;

public enum RecordStatus {
    Waiting((byte) 1),
    Success((byte) 2),
    Failed((byte) 2);
    private byte code;

    RecordStatus(byte code) {
        this.code = code;
    }

    public byte code() {
        return code;
    }
}
