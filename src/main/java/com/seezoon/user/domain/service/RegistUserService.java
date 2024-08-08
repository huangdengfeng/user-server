package com.seezoon.user.domain.service;


import com.seezoon.user.domain.dao.mapper.OauthMapper;
import com.seezoon.user.domain.dao.mapper.UserMapper;
import com.seezoon.user.domain.dao.mapper.UserProfileMapper;
import com.seezoon.user.domain.dao.po.OauthPO;
import com.seezoon.user.domain.dao.po.UserPO;
import com.seezoon.user.domain.dao.po.UserProfilePO;
import com.seezoon.user.domain.valueobj.OauthType;
import com.seezoon.user.domain.valueobj.UserStatus;
import com.seezoon.user.infrastructure.constants.DbRecordStatus;
import com.seezoon.user.infrastructure.exception.Assertion;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
@Validated
public class RegistUserService {

    private final UserMapper userMapper;
    private final OauthMapper oauthMapper;
    private final UserProfileMapper userProfileMapper;

    public Long execute(@NotNull OauthType oauthType, @NotEmpty String oauthId, String unionId) {
        UserPO user = new UserPO();
        user.setSecretKey(genSecretKey());
        user.setStatus(UserStatus.VALID);
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(user.getCreateTime());
        int affectedRows = userMapper.insert(user);
        Assertion.affectedOne(affectedRows);
        Assertion.notNull(user.getUid());

        this.saveOauth(user.getUid(), oauthType.type(), oauthId, unionId);
        this.saveProfile(user.getUid());

        return user.getUid();
    }

    private void saveOauth(Long uid, Byte oauthType, String oauthId, String unionId) {
        OauthPO po = new OauthPO();
        po.setUid(uid);
        po.setOauthType(oauthType);
        po.setOauthId(oauthId);
        po.setUnionId(unionId);
        po.setStatus(DbRecordStatus.VALID);
        po.setCreateTime(LocalDateTime.now());
        po.setUpdateTime(po.getCreateTime());
        int affectedRows = oauthMapper.insert(po);
        Assertion.affectedOne(affectedRows);
    }

    private void saveProfile(Long uid) {
        UserProfilePO po = new UserProfilePO();
        po.setUid(uid);
        po.setCreateTime(LocalDateTime.now());
        po.setUpdateTime(po.getCreateTime());
        int affectedRows = userProfileMapper.insert(po);
        Assertion.affectedOne(affectedRows);
    }

    private String genSecretKey() {
        return RandomStringUtils.randomAlphanumeric(32);
    }

}
