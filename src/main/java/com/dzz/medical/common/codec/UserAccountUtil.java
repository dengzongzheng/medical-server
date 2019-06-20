package com.dzz.medical.common.codec;

import org.apache.commons.codec.digest.Md5Crypt;

/**
 * 用户帐户工具
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年06月17 09:49
 */
public final class UserAccountUtil {

    private static final int SALT_SIZE = 8;
    public static final String HASH_ALGORITHM = "SHA-1";
    public static final int HASH_ITERATIONS = 1024;

    /**
     * 用户密码加密盐值
     */
    private static final String[] SALT_DATA_SOURCE = {
            "$1$a1Bef7829d235ebc", "$1$bade123512d8a3f5", "$1$5d8a9e1b8d8a3ce1", "$1$e41ad6e6fb731d9e",
            "$1$b91ad4ef8bd8fead", "$1$3ade5fedb64a9538", "$1$987ade91bde913ad", "$1$98de12bf83adf931"};

    /**
     * 获取盐值值
     *
     * @param userName 用户账号
     * @return 密码盐值
     */
    public static String getSaltValue(String userName) {
        return SALT_DATA_SOURCE[Math.abs(userName.hashCode() % SALT_DATA_SOURCE.length)];
    }

    /**
     * 加密用户密码
     * @param userName 用户名
     * @param password 密码
     * @return 返回加密密文
     */
    public static String encodePassword(String userName,String password) {

        return Md5Crypt.md5Crypt(password.getBytes(),getSaltValue(userName));
    }

    /**
     * 验证用户密码密码是否正确
     * @param userName 用户名
     * @param password 密码
     * @param inputPassword 用户输入的密码
     * @return 验证结果
     */
    public static Boolean validatePassword(String userName, String password, String inputPassword) {

        return encodePassword(userName, password).equals(inputPassword);
    }
}
