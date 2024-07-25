package com.seezoon.user.infrastructure.grpc.client;

import com.seezoon.user.infrastructure.exception.ExceptionFactory;
import io.grpc.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ErrorInterceptor implements ClientInterceptor {

    private static final Metadata.Key ERROR_CODE_HEADER = Metadata.Key.of("x-err-code", Metadata.ASCII_STRING_MARSHALLER);
    private static final Metadata.Key ERROR_MSG_HEADER = Metadata.Key.of("x-err-msg", Metadata.ASCII_STRING_MARSHALLER);
    private static final Metadata.Key ERROR_TYPE_HEADER = Metadata.Key.of("x-err-type", Metadata.ASCII_STRING_MARSHALLER);

    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions, Channel channel) {
        return new ForwardingClientCall.SimpleForwardingClientCall<>(channel.newCall(methodDescriptor, callOptions)) {
            @Override
            public void start(Listener<RespT> responseListener, Metadata headers) {
                super.start(new ForwardingClientCallListener.SimpleForwardingClientCallListener<>(responseListener) {
                    Object errCode;
                    Object errMsg;
                    Object errType;

                    @Override
                    public void onClose(Status status, Metadata trailers) {
                        super.onClose(status, trailers);
                        boolean hasError = null != errCode && null != errMsg && null != errType;
                        if (hasError) {
                            log.error("call method {} errcode {},errmsg {}", methodDescriptor.getFullMethodName(), errCode, errMsg);
                            throw ExceptionFactory.bizException(Integer.parseInt(errCode.toString()), errMsg.toString());
                        }
                    }

                    @Override
                    public void onHeaders(Metadata headers) {
                        super.onHeaders(headers);
                        errCode = headers.get(ERROR_CODE_HEADER);
                        errMsg = headers.get(ERROR_MSG_HEADER);
                        errType = headers.get(ERROR_TYPE_HEADER);
                    }
                }, headers);
            }
        };
    }
}
