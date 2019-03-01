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

    public static final String REGEX_IP_ADDR = "(2(5[0-5]{1}|[0-4]\\d{1})|[0-1]?\\d{1,2})(\\.(2(5[0-5]{1}|[0-4]\\d{1})|[0-1]?\\d{1,2})){3}";

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

    /**
     * <p>匹配一个ip v4的地址</p>
     * <pre>
     *     matcherIpV4(null) == false
     *     matcherIpV4("192.168.0.1") == true
     *     matcherIpV4("www.baidu.com") == false
     *     matcherIpV4("666.666.666.1") == false
     * </pre>
     * @param str
     * @return
     */
    public static boolean matcherIpV4(String str) {
        return matcher(str, REGEX_IP_ADDR);
    }

    /**
     * <p>匹配url</p>
     * <pre>
     *     matcherUrl("http://www.baidu.com") == true
     *     matcherUrl("www.baidu.com") == false
     *     matcherUrl("baidu.com") = false
     * </pre>
     * @param str
     * @return
     */
    public static boolean matcherUrl(String str) {
        return matcher(str, REGEX_URL);
    }

    /**
     * <p>匹配中文字符</p>
     * <pre>
     *     matcherChinese("李四") == true
     *     matcherChinese("李四666") == false
     *     matcherChinese("abc") == false
     *     matcherChinese("456") == false
     * </pre>
     * @param str
     * @return
     */
    public static boolean matcherChinese(String str) {
        return matcher(str, REGEX_CHINESE);
    }

    /**
     * <p>获取字符串中的匹配结果</p>
     * @param str 字符串
     * @param express 正则表达式
     * @return  匹配的结果集
     */
    public static List<String> matcherList(String str, String express) {
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
