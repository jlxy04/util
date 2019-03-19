package com.github.utils.encrypt;

import com.github.jlxy04.utils.encrypt.Md5;
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
