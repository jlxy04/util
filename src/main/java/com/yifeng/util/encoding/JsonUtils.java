package com.yifeng.util.encoding;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * @Description:
 * @author: lijun
 * @Date: 2019-1-19 16:11
 */
public class JsonUtils {

    public static <T> T parse(String text, Class<T> clazz) {
        return JSON.parseObject(text, clazz);
    }

    public static <T> List<T> parseArray(String text, Class<T> clazz) {
        return JSON.parseArray(text, clazz);
    }

    public static String toJsonString(Object o) {
        return JSON.toJSONString(o);
    }
}
