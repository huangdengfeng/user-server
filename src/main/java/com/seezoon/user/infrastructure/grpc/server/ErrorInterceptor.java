package com.seezoon.user.infrastructure.grpc.server;

import com.seezoon.user.infrastructure.error.ErrorCode;
import com.seezoon.user.infrastructure.exception.BaseException;
import com.seezoon.user.infrastructure.exception.BizException;
import io.grpc.*;

public class ErrorInterceptor implements ServerInterceptor {
    private static final Metadata.Key ERROR_CODE_HEADER = Metadata.Key.of("x-err-code", Metadata.ASCII_STRING_MARSHALLER);
    private static final Metadata.Key ERROR_MSG_HEADER = Metadata.Key.of("x-err-msg", Metadata.ASCII_STRING_MARSHALLER);
    private static final Metadata.Key ERROR_TYPE_HEADER = Metadata.Key.of("x-err-type", Metadata.ASCII_STRING_MARSHALLER);

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> serverCall, Metadata metadata, ServerCallHandler<ReqT, RespT> serverCallHandler) {
        ForwardingServerCall.SimpleForwardingServerCall<ReqT, RespT> simpleForwardingServerCall = new ForwardingServerCall.SimpleForwardingServerCall<>(serverCall) {
            @Override
            public void close(Status status, Metadata trailers) {
                if (!status.isOk() && status.getCause() instanceof BaseException) {
                    BaseException e = (BaseException) status.getCause();
                    Metadata errorHeaders = new Metadata();
                    errorHeaders.put(ERROR_CODE_HEADER, e.getcode());
                    errorHeaders.put(ERROR_MSG_HEADER, e.getMessage());
                    errorHeaders.put(ERROR_TYPE_HEADER, status.getCause() instanceof BizException ? ErrorCode.ERROR_TYPE_BUSINESS : ErrorCode.ERROR_TYPE_SYSTEM);
                    serverCall.sendHeaders(errorHeaders);
                }
                super.close(status, trailers);
            }
        };
        return new ForwardingServerCallListener.SimpleForwardingServerCallListener<>(serverCallHandler.startCall(simpleForwardingServerCall, metadata)) {
        };
    }
}
