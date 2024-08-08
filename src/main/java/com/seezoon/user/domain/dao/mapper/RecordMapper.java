package com.seezoon.user.domain.dao.mapper;

import com.seezoon.user.domain.dao.po.RecordPO;

public interface RecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RecordPO row);

    int insertSelective(RecordPO row);

    RecordPO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RecordPO row);

    int updateByPrimaryKey(RecordPO row);
}