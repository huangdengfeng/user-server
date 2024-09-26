package com.seezoon.user.application.executor;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageSerializable;
import com.seezoon.user.application.convertor.RecordConvertor;
import com.seezoon.user.application.dto.RecordCO;
import com.seezoon.user.application.dto.RecordPageQry;
import com.seezoon.user.domain.dao.mapper.RecordMapper;
import com.seezoon.user.domain.dao.po.RecordPO;
import com.seezoon.user.infrastructure.configuration.context.SecurityContextHolder;
import com.seezoon.user.infrastructure.dto.Page;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Component
@Validated
public class RecordPageQryExe {

    private final RecordMapper recordMapper;

    public Page<RecordCO> execute(@Valid @NotNull RecordPageQry qry) {
        RecordPO.Condition condition = new RecordPO.Condition();
        condition.setType(qry.getType());
        condition.setUid(SecurityContextHolder.getUid());
        PageHelper.startPage(qry.getPage(), qry.getPageSize());
        List<RecordPO> pos = recordMapper.selectByCondition(condition);
        PageSerializable<RecordPO> pageSerializable = PageSerializable.of(pos);
        List<RecordCO> list = pageSerializable.getList().stream().map(RecordConvertor::toCO).collect(Collectors.toList());
        Page<RecordCO> page = new Page<>(list);
        page.setTotal(pageSerializable.getTotal());
        return page;
    }
}
