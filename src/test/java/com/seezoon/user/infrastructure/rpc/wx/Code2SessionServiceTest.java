package com.seezoon.user.infrastructure.rpc.wx;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.seezoon.user.infrastructure.rpc.wx.dto.Code2SessionResp;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class Code2SessionServiceTest {

    @Autowired
    private Code2SessionService code2SessionService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void execute() throws JsonProcessingException {
        Code2SessionResp execute = code2SessionService.execute("wx76561e919bb4753a", "9946a3c0127a810648307815edf1f8fb", "0c3LTC0w3tNX933ubV2w3AXdhp3LTC0P");
        log.info(objectMapper.writeValueAsString(execute));
    }
}