package com.seezoon.user.infrastructure.rpc.jwt;

import com.seezoon.stub.jwt.*;
import com.seezoon.user.infrastructure.error.ErrorCode;
import com.seezoon.user.infrastructure.exception.ExceptionFactory;
import com.seezoon.user.infrastructure.grpc.client.Client;
import com.seezoon.user.infrastructure.properties.AppProperties;
import io.grpc.ManagedChannel;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Slf4j
@Component
@Validated
public class JwtService implements InitializingBean {

    private final AppProperties appProperties;
    private JwtGrpc.JwtBlockingStub stub;

    public String sign(@NotEmpty String subject, @NotNull Duration timeout) {

        try {
            SignResp signResp = stub.sign(SignReq.newBuilder()
                    .setJwtInfo(JwtInfo.newBuilder().setSub(subject).setExp(Instant.now().plus(timeout).getEpochSecond()).build())
                    .build());
            return signResp.getToken();
        } catch (Exception e) {
            log.error("sign error {}", e.getMessage());
            throw ExceptionFactory.bizException(ErrorCode.RPC_ERROR);
        }
    }

    public String verify(@NotEmpty String token) {
        try {
            VerifyResp verifyResp = stub.verify(VerifyReq.newBuilder().setToken(token).build());
            if (verifyResp.getValid()) {
                return verifyResp.getJwtInfo().getSub();
            }
            log.info("jwt invalid {}", token);
            return null;
        } catch (Exception e) {
            log.error("verify error {}", e.getMessage());
            throw ExceptionFactory.bizException(ErrorCode.RPC_ERROR);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        String sessionEndpoint = appProperties.getLogin().getSessionEndpoint();
        ManagedChannel channel = Client.createChannel(sessionEndpoint);
        stub = JwtGrpc.newBlockingStub(channel)
                .withDeadlineAfter(Client.DEFAULT_READTIMEOUT, TimeUnit.SECONDS);
    }
}
