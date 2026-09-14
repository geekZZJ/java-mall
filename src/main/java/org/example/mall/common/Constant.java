package org.example.mall.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constant {
    public static final String SALT = "faklajkfjlka";
    public static final String MALL_USER = "mall_user";
    public static String FILE_UPLOAD_DIR;

    @Value("${file.upload.dir}")
    public void setFileUploadDir(String fileUploadDir) {
        FILE_UPLOAD_DIR = fileUploadDir;
    }
}
