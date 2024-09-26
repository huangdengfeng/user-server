package com.seezoon.user.interfaces;

import com.seezoon.user.application.dto.UploadCmd;
import com.seezoon.user.application.dto.clientobject.DownloadCO;
import com.seezoon.user.application.dto.clientobject.UploadCO;
import com.seezoon.user.application.executor.DownloadQryExe;
import com.seezoon.user.application.executor.UploadCmdExe;
import com.seezoon.user.infrastructure.dto.Response;
import com.seezoon.user.infrastructure.error.ErrorCode;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

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
    private final DownloadQryExe downloadQryExe;

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

    @GetMapping("/download")
    public void download(HttpServletResponse response, @RequestParam String relativePath) throws IOException {
        DownloadCO co = downloadQryExe.execute(relativePath);
        response.setContentType(co.getContentType());
        try (InputStream inputStream = co.getInputStream();
             OutputStream outputStream = response.getOutputStream();
             BufferedOutputStream bos = new BufferedOutputStream(outputStream);
             BufferedInputStream bin = new BufferedInputStream(inputStream)) {
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode(co.getName(), StandardCharsets.UTF_8));
            // 1M 一写
            byte[] buffer = new byte[1024 * 1024];
            int len = 0;
            while (-1 != (len = bin.read(buffer))) {
                bos.write(buffer, 0, len);
            }
        }
    }
}