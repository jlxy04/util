package com.yifeng.util.encrypt;

import com.yifeng.util.Charset;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Description:
 * @author: lijun
 * @Date: 2019-1-19 17:39
 */
public class Md5Utils {

    private static final String EMPTY = "";

    private static final String[] hexDigits = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};

    private Md5Utils() {}

    public static String md5(String str) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            return byteArrayToHexString(md5.digest(str.getBytes(Charset.UTF8)));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private static String byteArrayToHexString(byte[] b){
        StringBuffer resultSb = new StringBuffer();
        for(int i=0;i<b.length;i++){
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b){
        int n = b;
        if(n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }
}
