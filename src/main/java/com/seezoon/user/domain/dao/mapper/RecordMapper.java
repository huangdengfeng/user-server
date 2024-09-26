package com.seezoon.user.domain.dao.mapper;

import com.seezoon.user.domain.dao.po.RecordPO;

import java.util.List;

public interface RecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RecordPO row);

    RecordPO selectByPrimaryKey(Integer id);

    List<RecordPO> selectByCondition(RecordPO.Condition condition);

    int updateByPrimaryKeySelective(RecordPO row);

    int updateByPrimaryKey(RecordPO row);
}