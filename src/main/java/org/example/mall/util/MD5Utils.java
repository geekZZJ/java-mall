package org.example.mall.util;

import org.apache.commons.codec.binary.Base64;
import org.example.mall.common.Constant;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
    public static String getMD5Str(String strValue) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        return Base64.encodeBase64String(messageDigest.digest((strValue + Constant.SALT).getBytes()));
    }

    //    测试方法
    public static void main(String[] args) {
        String md5 = null;
        try {
            md5 = getMD5Str("1234");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        System.out.println(md5);
    }
}
