package com.yifeng.util.encrypt;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @Description:
 * @author: lijun
 * @Date: 2019-1-21 16:15
 */
public class AesUtils {

    private AesUtils() {}

    public static byte[] encrypt(String text, String password) {
        if (text == null) {
            return null;
        }

        if (text.length() == 0) {
            return new byte[]{};
        }
        try {
            // 创建AES的Key生产者
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            // 利用用户密码作为随机数初始化出
            kgen.init(128, new SecureRandom(password.getBytes()));
            // 128位的key生产者
            //加密没关系，SecureRandom是生成安全随机数序列，password.getBytes()是种子，只要种子相同，序列就一样，所以解密只要有password就行
            // 根据用户密码，生成一个密钥
            SecretKey secretKey = kgen.generateKey();
            // 返回基本编码格式的密钥，如果此密钥不支持编码，则返回
            byte[] enCodeFormat = secretKey.getEncoded();

            // 转换为AES专用密钥
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");

            // 创建密码器
            Cipher cipher = Cipher.getInstance("AES");

            byte[] byteContent = text.getBytes("utf-8");

            // 初始化为加密模式的密码器
            cipher.init(Cipher.ENCRYPT_MODE, key);

            // 加密
            byte[] result = cipher.doFinal(byteContent);

            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] decrypt(byte[] text, String password) {
        try {
            // 创建AES的Key生产者
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, new SecureRandom(password.getBytes()));
            // 根据用户密码，生成一个密钥
            SecretKey secretKey = kgen.generateKey();
            // 返回基本编码格式的密钥
            byte[] enCodeFormat = secretKey.getEncoded();
            // 转换为AES专用密钥
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            // 创建密码器
            Cipher cipher = Cipher.getInstance("AES");
            // 初始化为解密模式的密码器
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] result = cipher.doFinal(text);
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
