package com.yifeng.util.common;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Description:
 * @author: lijun
 * @Date: 2019-1-19 17:03
 */
public class StringUtilsTest {

    @Test
    public void isEmpty() {
        // isEmpty("") == true
        Assert.assertTrue(StringUtils.isEmpty(""));

        // isEmpty(" ") == false
        Assert.assertFalse(StringUtils.isEmpty(" "));

        // isEmpty("xxx") == false
        Assert.assertFalse(StringUtils.isEmpty("xxx"));

        // isEmpty("  xxx  ") == false
        Assert.assertFalse(StringUtils.isEmpty("  xxx  "));

        // isEmpty(null) == true
        Assert.assertTrue(StringUtils.isEmpty(null));
    }

    @Test
    public void isNotEmpty() {
        // isNotEmpty("") == false
        Assert.assertFalse(StringUtils.isNotEmpty(""));

        // isNotEmpty(" ") == true
        Assert.assertTrue(StringUtils.isNotEmpty(" "));

        // isNotEmpty("xxx") == true
        Assert.assertTrue(StringUtils.isNotEmpty("xxx"));

        // isNotEmpty("  xxx  ") == true
        Assert.assertTrue(StringUtils.isNotEmpty("  xxx  "));

        // isNotEmpty(null) == false
        Assert.assertFalse(StringUtils.isNotEmpty(null));
    }

    @Test
    public void isBlank() {
        // isBlank("") == true
        Assert.assertTrue(StringUtils.isBlank(""));

        // isBlank(" ") == true
        Assert.assertTrue(StringUtils.isBlank(" "));

        // isBlank("xxx") == false
        Assert.assertFalse(StringUtils.isBlank("xxx"));

        // isBlank("  xxx  ") == false
        Assert.assertFalse(StringUtils.isBlank("  xxx  "));

        // isBlank(null) == true
        Assert.assertTrue(StringUtils.isBlank(null));
    }

    @Test
    public void isNotBlank() {
        // isNotBlank("") == false
        Assert.assertFalse(StringUtils.isNotBlank(""));

        // isNotBlank(" ") == false
        Assert.assertFalse(StringUtils.isNotBlank(" "));

        // isNotBlank("xxx") == true
        Assert.assertTrue(StringUtils.isNotBlank("xxx"));

        // isNotBlank("  xxx  ") == true
        Assert.assertTrue(StringUtils.isNotBlank("  xxx  "));

        // isNotBlank(null) == false
        Assert.assertFalse(StringUtils.isNotBlank(null));
    }

    @Test
    public void trim() {
        // trim("") == ""
        Assert.assertEquals(StringUtils.trim(""), "");

        // trim(" ") == ""
        Assert.assertEquals(StringUtils.trim(" "), "");

        // trim("xxx") == "xxx"
        Assert.assertEquals(StringUtils.trim("xxx"), "xxx");

        // trim("  xxx  ") == "xxx"
        Assert.assertEquals(StringUtils.trim("  xxx  "), "xxx");

        // trim(null) == null
        Assert.assertNull(StringUtils.trim(null));
    }

    @Test
    public void trimToNull() {
        // trimToNull("") == null
        Assert.assertNull(StringUtils.trimToNull(""));

        // trimToNull("xxx") == xxx
        Assert.assertEquals(StringUtils.trimToNull("xxx"), "xxx");

        // trimToNull(" xxx ") == xxx
        Assert.assertEquals(StringUtils.trimToNull(" xxx "), "xxx");

        // trimToNull("  ") == null
        Assert.assertNull(StringUtils.trimToNull("  "));

        // trimToNull(null) == null
        Assert.assertNull(StringUtils.trimToNull(null));
    }

    @Test
    public void trimToEmpty() {
        // trimToEmpty("") == ""
        Assert.assertNull(StringUtils.trimToEmpty(""), "");

        // trimToEmpty("xxx") == xxx
        Assert.assertEquals(StringUtils.trimToEmpty("xxx"), "xxx");

        // trimToEmpty(" xxx ") == xxx
        Assert.assertEquals(StringUtils.trimToEmpty(" xxx "), "xxx");

        // trimToEmpty("  ") == ""
        Assert.assertEquals(StringUtils.trimToEmpty("  "), "");

        // trimToEmpty(null) == ""
        Assert.assertEquals(StringUtils.trimToEmpty(null), "");
    }

    @Test
    public void substring() {
        // substring("abcdefg", 1) = bcdefg
        Assert.assertEquals(StringUtils.substring("abcdefg", 1), "bcdefg");

        System.out.println(StringUtils.substring("abcdefg", 1));
    }
}
