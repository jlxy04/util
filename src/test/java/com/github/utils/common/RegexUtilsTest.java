package com.github.utils.common;

import com.github.jlxy04.utils.common.RegexUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

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

    @Test
    public void matcherIpV4() {
        Assert.assertFalse(RegexUtils.matcherIpV4(null));

        Assert.assertTrue(RegexUtils.matcherIpV4("192.168.0.1"));

        Assert.assertFalse(RegexUtils.matcherIpV4("www.baidu.com"));

        Assert.assertFalse(RegexUtils.matcherIpV4("666.666.666.1"));
    }

    @Test
    public void matcherUrl() {
        Assert.assertFalse(RegexUtils.matcherUrl(null));

        Assert.assertTrue(RegexUtils.matcherUrl("http://www.baidu.com"));

        Assert.assertFalse(RegexUtils.matcherUrl("www.baidu.com"));

        Assert.assertFalse(RegexUtils.matcherUrl("baidu.com"));
    }

    @Test
    public void matcherChinese() {
        Assert.assertFalse(RegexUtils.matcherChinese(null));

        Assert.assertTrue(RegexUtils.matcherChinese("李四"));

        Assert.assertFalse(RegexUtils.matcherChinese("李四666"));

        Assert.assertFalse(RegexUtils.matcherChinese("abc"));

        Assert.assertFalse(RegexUtils.matcherChinese("456"));
    }

    @Test
    public void matcherList() {
        Assert.assertNull(RegexUtils.matcherList(null, null));

        String s1 = "4651321";
        List<String> list = RegexUtils.matcherList(s1, "\\d");
        for (int i = 0; i < list.size(); i++) {
            String s =  list.get(i);
            Assert.assertEquals(s1.substring(i, i+1), s);
        }

        String s2 = "李四66GetIf";
        List<String> list2 = RegexUtils.matcherList(s2, "\\d{2}");
        System.out.println(list2);
        for (int i = 0; i < list2.size(); i++) {
            String s =  list2.get(i);
            Assert.assertEquals("66", s);
        }
    }
}
