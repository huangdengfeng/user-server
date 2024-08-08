package com.seezoon.user.infrastructure.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * 文件上传配置
 */
@Getter
@Setter
public class UploadProperties {

    /**
     * 可访问的网址前缀，如https://xxx.com
     */
    private String urlPrefix;
    // 上传目录
    private String directory = "./upload";

}