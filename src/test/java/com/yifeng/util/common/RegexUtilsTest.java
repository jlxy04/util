package com.yifeng.util.common;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Description:
 * @author: lijun
 * @Date: 2019-2-27 19:52
 */
public class RegexUtilsTest {

    @Test
    public void matcher() {
        Assert.assertTrue(RegexUtils.matcher("www.baidu.com", null));

        Assert.assertFalse(RegexUtils.matcher(null, "[a-z]{0,3}\\.[a-z]{0,3}"));

        Assert.assertTrue(RegexUtils.matcher("www.baidu.com", "[a-z]{0,3}\\.[a-z]{0,3}"));
    }

    @Test
    public void matcherEmail() {
        Assert.assertFalse(RegexUtils.matcherEmail(null));

        Assert.assertFalse(RegexUtils.matcherEmail("www.baidu.com"));

        Assert.assertTrue(RegexUtils.matcherEmail("jlxy04@gmail.com"));

        Assert.assertTrue(RegexUtils.matcherEmail("fdasfasfsa@fdsaxx.cc"));
    }
}
