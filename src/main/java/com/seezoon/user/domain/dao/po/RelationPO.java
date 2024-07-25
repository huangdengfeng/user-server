package com.seezoon.user.domain.dao.po;

import java.time.LocalDateTime;

public class RelationPO extends RelationKeyPO {
    private Byte status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}