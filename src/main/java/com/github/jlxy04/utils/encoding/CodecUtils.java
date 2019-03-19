package com.github.jlxy04.utils.encoding;

import com.github.jlxy04.utils.Charset;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @Description:
 * @author: lijun
 * @Date: 2019-1-19 16:13
 */
public class CodecUtils {

    private static final String EMPTY = "";

    public static String urlEncode(String url) {
        try {
            return URLEncoder.encode(url, Charset.UTF8);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String urlEncode(String url, String charset) {
        try {
            return URLEncoder.encode(url, charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String urlDecode(String str) {
        try {
            return URLDecoder.decode(str, Charset.UTF8);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String urlDecode(String str, String charset) {
        try {
            return URLDecoder.decode(str, charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String toGBK(String str) {
        return to(str, Charset.GBK);
    }

    public static String toISO885901(String str) {
        return to(str, Charset.ISO88591);
    }

    public static String toUTF8(String str) {
        return to(str, Charset.UTF8);
    }

    private static String to(String s, String charset) {
        if(s == null) {
            return null;
        }

        if(s.length() == 0) {
            return EMPTY;
        }

        try {
            return new String(s.getBytes(charset));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
