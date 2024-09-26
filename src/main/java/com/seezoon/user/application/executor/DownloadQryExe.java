package com.seezoon.user.application.executor;

import com.seezoon.user.application.dto.clientobject.DownloadCO;
import com.seezoon.user.infrastructure.error.ErrorCode;
import com.seezoon.user.infrastructure.exception.ExceptionFactory;
import com.seezoon.user.infrastructure.properties.AppProperties;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * 下载
 */
@Component
@Slf4j
@RequiredArgsConstructor
@Validated
public class DownloadQryExe {

    private final AppProperties appProperties;

    public DownloadCO execute(@NotEmpty String relativePath) throws IOException {
        Path storePath = Path.of(appProperties.getUpload().getDirectory(), relativePath);
        if (!Files.exists(storePath)) {
            throw ExceptionFactory.bizException(ErrorCode.FILE_NOT_EXISTS);
        }
        InputStream inputStream = Files.newInputStream(storePath);
        Path fileName = Path.of(relativePath).getFileName();
        String contentType = Files.probeContentType(fileName);
        return new DownloadCO(fileName.toString(), inputStream, contentType);
    }

}
