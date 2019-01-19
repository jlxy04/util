package com.yifeng.util.generate;

import java.util.UUID;

/**
 * @Description:
 * @author: lijun
 * @Date: 2019-1-19 16:53
 */
public class GenerUtils {

    public static String UUID() {
        return UUID.randomUUID().toString();
    }

    public static String id() {
        return ObjectId.get().toString();
    }
}
