package com.seezoon.user.infrastructure.grpc.client;

import io.grpc.ManagedChannel;
import io.grpc.netty.NettyChannelBuilder;
import io.netty.channel.ChannelOption;

import java.util.concurrent.TimeUnit;

public class Client {

    public static final long DEFAULT_READTIMEOUT = 3000;
    private static final int DEFAULT_CONNECTION_TIMEOUTMS = 1000;

    public static ManagedChannel createChannel(String address, int connectionTimeoutMs) {
        ManagedChannel channel = NettyChannelBuilder.forTarget(address)
                .usePlaintext()
                .keepAliveTime(1, TimeUnit.MINUTES)  // 客户端每隔1分钟发送一次心跳ping
                .keepAliveTimeout(10, TimeUnit.SECONDS)  // 如果没有收到服务端的心跳响应，认为连接失败的超时时间
                .keepAliveWithoutCalls(true)  // 即使没有活动的RPC流，也允许发送心跳
                .withOption(ChannelOption.CONNECT_TIMEOUT_MILLIS, connectionTimeoutMs)
                .intercept(new ErrorInterceptor())
                .build();
        return channel;
    }

    public static ManagedChannel createChannel(String address) {
        return createChannel(address, DEFAULT_CONNECTION_TIMEOUTMS);
    }
}
