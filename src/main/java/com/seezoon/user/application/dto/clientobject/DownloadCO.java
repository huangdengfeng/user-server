package com.seezoon.user.application.dto.clientobject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.InputStream;

/**
 * @author dfenghuang
 * @date 2022/10/13 00:47
 */
@Getter
@Setter
@AllArgsConstructor
public class DownloadCO {

    /**
     * 原始文件名
     */
    private String name;
    /**
     * 文件流
     */
    private InputStream inputStream;
    /**
     * 文件类型
     */
    private String contentType;
}
