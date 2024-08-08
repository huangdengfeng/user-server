package com.seezoon.user.domain.service;

import com.seezoon.user.domain.dao.mapper.RecordMapper;
import com.seezoon.user.domain.dao.po.RecordPO;
import com.seezoon.user.domain.valueobj.RecordStatus;
import com.seezoon.user.domain.valueobj.RecordType;
import com.seezoon.user.infrastructure.exception.Assertion;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
@Validated
public class AddRecordService {

    private final RecordMapper recordMapper;

    public Integer add(@NotNull Long uid, @NotEmpty String relativePath, RecordType type) {
        RecordPO po = new RecordPO();
        po.setUid(uid);
        po.setType(type.code());
        po.setSrc(relativePath);
        po.setStatus(RecordStatus.Waiting.code());
        po.setCreateTime(LocalDateTime.now());
        po.setUpdateTime(po.getCreateTime());
        int affectedRows = recordMapper.insert(po);
        Assertion.affectedOne(affectedRows);
        return po.getId();
    }
}
