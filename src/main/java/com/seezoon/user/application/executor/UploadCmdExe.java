package com.seezoon.user.application.executor;

import com.seezoon.user.application.dto.UploadCmd;
import com.seezoon.user.application.dto.clientobject.UploadCO;
import com.seezoon.user.infrastructure.error.ErrorCode;
import com.seezoon.user.infrastructure.exception.Assertion;
import com.seezoon.user.infrastructure.exception.ExceptionFactory;
import com.seezoon.user.infrastructure.properties.AppProperties;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Component
@Validated
public class UploadCmdExe {

    private static final String LINE_THROUGH = "-";
    private static final String SLASH = "/";

    private final AppProperties appProperties;

    public UploadCO execute(@Valid UploadCmd cmd) {
        String directory = appProperties.getUpload().getDirectory();
        Assertion.notNull(directory, "upload directory is empty");
        String fileId = createFileId();
        String newName = rename(fileId, cmd.getOriginalFilename());
        String relativePath = createRelativeDirectory() + newName;
        Path storePath = Path.of(directory, relativePath);
        try {
            FileUtils.copyInputStreamToFile(cmd.getIn(), storePath.toFile());
        } catch (IOException e) {
            log.error("upload file error", e);
            throw ExceptionFactory.bizException(ErrorCode.FILE_UPLOAD_FAILED);
        }
        return new UploadCO(relativePath);
    }

    private String createFileId() {
        return UUID.randomUUID().toString().replaceAll(LINE_THROUGH, StringUtils.EMPTY);
    }

    /**
     * 生成新的文件名
     *
     * @param fileId
     * @param originalFileName
     * @return
     */
    public String rename(String fileId, String originalFileName) {
        return fileId + getFileSuffix(originalFileName);
    }

    public String getFileName(@NotEmpty String relativePath) {
        return relativePath.substring(relativePath.lastIndexOf(SLASH) + 1);
    }

    /**
     * 获取文件后缀
     *
     * @param fileName 包含后缀
     * @return
     */
    public String getFileSuffix(String fileName) {
        Assert.hasText(fileName, "fileName must not be empty");
        String suffix = "";
        int lastIndex;
        if (-1 != (lastIndex = fileName.lastIndexOf("."))) {
            suffix = fileName.substring(lastIndex);
        }
        return suffix;
    }

    /**
     * 生成相对目录
     *
     * @return
     */
    private String createRelativeDirectory() {
        LocalDate now = LocalDate.now();
        return SLASH + now.getYear() + SLASH + now.getMonthValue() + SLASH + now.getDayOfMonth() + SLASH;
    }
}
