package com.yifeng.util.encrypt;

import com.yifeng.util.Charset;
import com.yifeng.util.reflect.ClassUtils;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * @Description:
 * @author: lijun
 * @Date: 2019-1-21 15:27
 */
public class Base64s {

    private static final String EMPTY = "";

    private static final org.apache.commons.codec.binary.Base64 base64 = new org.apache.commons.codec.binary.Base64();

    private Base64s() {}

    public static byte[] encode(byte[] src) {
        if (src == null || src.length <= 0) {
            return src;
        }

        if (isJdkBase64()) {
            return Base64.getEncoder().encode(src);
        } else {
            return base64.encode(src);
        }
    }

    public static byte[] decode(byte[] src) {
        if (src == null || src.length <= 0) {
            return src;
        }

        if (isJdkBase64()) {
            return Base64.getDecoder().decode(src);
        } else {
            return base64.decode(src);
        }
    }

    public static String encodeToString(byte[] src, String charset) {
        if (src == null) {
            return null;
        }

        if (src.length == 0) {
            return EMPTY;
        }

        try {
            return new String(encode(src), charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String encodeToString(byte[] src) {
        return encodeToString(src, Charset.UTF8);
    }

    public static byte[] decodeFromString(String src) {
        return decodeFromString(src, Charset.UTF8);
    }

    public static byte[] decodeFromString(String src, String charset) {
        if (src == null) {
            return null;
        }

        if (src.length() == 0) {
            return new byte[0];
        }

        try {
            return decode(src.getBytes(charset));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean isJdkBase64() {
        return ClassUtils.isPresent("java.util.Base64", Base64s.class.getClassLoader());
    }
}
