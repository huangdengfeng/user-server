package com.seezoon.user.interfaces;

import com.seezoon.user.application.dto.UploadCmd;
import com.seezoon.user.application.dto.clientobject.UploadCO;
import com.seezoon.user.application.executor.UploadCmdExe;
import com.seezoon.user.infrastructure.dto.Response;
import com.seezoon.user.infrastructure.error.ErrorCode;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * 文件
 *
 * @author dfenghuang
 * @date 2022/10/12 20:50
 */
@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/file")
@Validated
public class FileController {

    private final UploadCmdExe uploadCmdExe;

    @PostMapping("/upload")
    public Response<UploadCO> upload(@NotNull @RequestParam MultipartFile file) {
        try (InputStream inputStream = file.getInputStream()) {
            UploadCmd uploadCmd = new UploadCmd(file.getOriginalFilename(), file.getContentType(), file.getSize(), inputStream);
            return Response.success(uploadCmdExe.execute(uploadCmd));
        } catch (IOException e) {
            log.error("upload error", e);
            return Response.error(ErrorCode.FILE_UPLOAD_FAILED.code(), ErrorCode.FILE_UPLOAD_FAILED.msg());
        }
    }
}