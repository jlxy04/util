package com.yifeng.util.encrypt;

import org.junit.Test;

/**
 * @Description:
 * @author: lijun
 * @Date: 2019-1-19 17:45
 */
public class DigestUtilsTest {

    @Test
    public void md5Test() {
        System.out.println(Md5.md5("123456"));
    }
}
