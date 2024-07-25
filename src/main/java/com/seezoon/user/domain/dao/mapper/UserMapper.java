package com.seezoon.user.domain.dao.mapper;

import com.seezoon.user.domain.dao.po.UserPO;

public interface UserMapper {
    int deleteByPrimaryKey(Long uid);

    int insert(UserPO row);

    UserPO selectByPrimaryKey(Long uid);

    UserPO selectByUsername(String username);

    int updateByPrimaryKeySelective(UserPO row);

    int updateByPrimaryKey(UserPO row);
}