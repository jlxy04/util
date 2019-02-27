package com.yifeng.util.common;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description:
 * @author: lijun
 * @Date: 2019-1-18 17:37
 */
public class RegexUtils {

    private static final String REGEX_EMIAL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    public static final String REGEX_CHINESE = "^[\u4e00-\u9fa5]{0,}$";

    public static final String REGEX_IP_ADDR = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";

    public static final String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";

    private RegexUtils() {}

    /**
     * <p>字符串是否匹配表达式</p>
     * <pre>
     *     matcher("www.baidu.com", null) == true
     *     matcher(null, "[a-z]{0,3}\\.[a-z]{0,3}") = false
     *     matcher("www.baidu.com", "[a-z]{0,3}\\.[a-z]{0,3}") = true
     * </pre>
     * @param str
     * @param express
     * @return
     */
    public static boolean matcher(String str, String express) {
        if(str == null) {
            return false;
        }

        if(express == null) {
            return true;
        }

        Pattern p = Pattern.compile(express);
        Matcher m = p.matcher(str);
        return m.find();
    }

    /**
     * <p>是否匹配邮箱地址</p>
     * <pre>
     *     matcherEmail(null) == false
     *     matcherEmail("www.baidu.com") == false
     *     matcherEmail("jlxy04@gmail.com") == true
     * </pre>
     * @param str
     * @return
     */
    public static boolean matcherEmail(String str) {
        return matcher(str, REGEX_EMIAL);
    }

    public static boolean matcherIpV4(String str) {
        return matcher(str, REGEX_IP_ADDR);
    }

    public static boolean matcherUrl(String str) {
        return matcher(str, REGEX_URL);
    }

    public static boolean matcherChinese(String str) {
        return matcher(str, REGEX_CHINESE);
    }

    public static List matcherList(String str, String express) {
        if(str == null) {
            return null;
        }

        if(express == null) {
            return null;
        }
        List<String> list = new ArrayList<String>();
        Pattern p = Pattern.compile(express);
        Matcher m = p.matcher(str);
        while (m.find()) {
            list.add(m.group());
        }
        return list;
    }
}
