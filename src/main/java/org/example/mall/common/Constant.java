package org.example.mall.common;

import org.springframework.beans.factory.annotation.Value;

public class Constant {
    public static final String SALT = "faklajkfjlka";
    public static final String MALL_USER = "mall_user";

    @Value("${file.upload.dir}")
    public static String FILE_UPLOAD_DIR;
}
