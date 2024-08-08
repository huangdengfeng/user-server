package com.seezoon.user.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.InputStream;

/**
 * @author dfenghuang
 * @date 2022/10/13 00:40
 */
@Getter
@Setter
@AllArgsConstructor
public class UploadCmd {

    @NotEmpty
    private String originalFilename;
    @NotEmpty
    private String contentType;
    private long size;
    @JsonIgnore
    private InputStream in;

}
