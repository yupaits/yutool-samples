package com.yupaits.sample.shirojwt.utils;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * 加密工具类
 * @author yupaits
 * @date 2019/8/22
 */
public class EncryptUtils {
    private static final String ALGORITHM = "SHA-256";
    private static final int ITERATIONS = 3;

    /**
     * 原始密码加密
     * @param password 原始密码
     * @param salt 加盐
     * @return 密码密文
     */
    public static String encryptPassword(String password, String salt) {
        return new SimpleHash(ALGORITHM, password, salt, ITERATIONS).toHex();
    }
}
