package com.seezoon.user.application.dto.clientobject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UploadCO {
    private String relativePath;

    public UploadCO(String relativePath) {
        this.relativePath = relativePath;
    }
}
