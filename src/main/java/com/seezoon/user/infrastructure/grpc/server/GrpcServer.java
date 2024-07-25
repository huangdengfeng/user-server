package com.seezoon.user.infrastructure.grpc.server;

import io.grpc.Server;
import io.grpc.netty.NettyServerBuilder;

import java.util.concurrent.TimeUnit;

public class GrpcServer {

    public static Server createServer(int port) {
        Server server = NettyServerBuilder.forPort(port)
                .permitKeepAliveWithoutCalls(true) //空闲时候发ping ，而不是断开连接
                .maxConnectionIdle(3, TimeUnit.MINUTES)
                .build();
        return server;
    }

}
