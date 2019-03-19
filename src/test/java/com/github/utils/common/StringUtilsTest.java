package com.github.utils.common;

import com.github.jlxy04.utils.common.StringUtils;
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
        Assert.assertEquals(StringUtils.trimToEmpty(""), "");

        // trimToEmpty("xxx") == xxx
        Assert.assertEquals(StringUtils.trimToEmpty("xxx"), "xxx");

        // trimToEmpty(" xxx ") == xxx
        Assert.assertEquals(StringUtils.trimToEmpty(" xxx "), "xxx");

        // trimToEmpty("  ") == ""
        Assert.assertEquals(StringUtils.trimToEmpty("  "), "");

        // trimToEmpty(null) == ""
        Assert.assertEquals(StringUtils.trimToEmpty(null), "");
    }

    /**
     * to substring(final String str, int start)
     */
    @Test
    public void substring() {
        // substring("abcdefg", 1) == bcdefg
        Assert.assertEquals(StringUtils.substring("abcdefg", 1), "bcdefg");

        // substring("abcdefg", 0) == abcdefg
        Assert.assertEquals(StringUtils.substring("abcdefg", 0), "abcdefg");

        // substring("abcdefg", -1) == g
        Assert.assertEquals(StringUtils.substring("abcdefg", -1), "g");

        // substring("abcdefg", -10) == abcdefg
        Assert.assertEquals(StringUtils.substring("abcdefg", -10), "abcdefg");

        // substring(null, 1) == null
        Assert.assertNull(StringUtils.substring(null, 1));

        // substring("abcdefg", "abcdefg".length()) == ""
        Assert.assertEquals(StringUtils.substring("abcdefg", "abcdefg".length()), "");

        // substring("abcdefg", 20) == ""
        Assert.assertEquals(StringUtils.substring("abcdefg", 20), "");
    }

    /**
     * to substring(final String str, int start, int end)
     */
    @Test
    public void substring2() {
        // substring("abcdefg", 1, 2) == bcdefg
        Assert.assertEquals(StringUtils.substring("abcdefg", 1, 2), "b");

        // substring("abcdefg", 0, 1) == a
        Assert.assertEquals(StringUtils.substring("abcdefg", 0, 1), "a");

        // substring("abcdefg", -1, 1) == ""
        Assert.assertEquals(StringUtils.substring("abcdefg", -1, 1), "");

        // substring(null, 1, 3) == null
        Assert.assertNull(StringUtils.substring(null, 1, 3));

        // substring("abcdefg", "abcdefg".length(), "abcdefg".length()) == ""
        Assert.assertEquals(StringUtils.substring("abcdefg", "abcdefg".length(), "abcdefg".length()), "");

        // substring("abcdefg", 20) == ""
        Assert.assertEquals(StringUtils.substring("abcdefg", 10, 20), "");
    }

    @Test
    public void left() {
        // left("abcdefg", 1) == a
        Assert.assertEquals(StringUtils.left("abcdefg", 1), "a");

        // left("abcdefg", -1) == ""
        Assert.assertEquals(StringUtils.left("abcdefg", -1), "");

        // left("abcdefg", 0) == ""
        Assert.assertEquals(StringUtils.left("abcdefg", 0), "");

        // left("abcdefg", 10) == "abcdefg"
        Assert.assertEquals(StringUtils.left("abcdefg", 10), "abcdefg");

        // left("", 2) == ""
        Assert.assertEquals(StringUtils.left("", 2), "");

        // left(null, 2) == ""
        Assert.assertNull(StringUtils.left(null, 2));
    }

    @Test
    public void right() {
        // right("abcdefg", 1) == g
        Assert.assertEquals(StringUtils.right("abcdefg", 1), "g");

        // right("abcdefg", -1) == ""
        Assert.assertEquals(StringUtils.right("abcdefg", -1), "");

        // right("abcdefg", 0) == ""
        Assert.assertEquals(StringUtils.right("abcdefg", 0), "");

        // right("abcdefg", 10) == "abcdefg"
        Assert.assertEquals(StringUtils.right("abcdefg", 10), "abcdefg");

        // right("", 2) == ""
        Assert.assertEquals(StringUtils.right("", 2), "");

        // right(null, 2) == ""
        Assert.assertNull(StringUtils.right(null, 2));
    }

    @Test
    public void rightPad() {
        // rightPad("xxx", 5) == "xxx  "
        Assert.assertEquals(StringUtils.rightPad("xxx", 5), "xxx  ");

        // rightPad(" xxx", 5) == " xxx "
        Assert.assertEquals(StringUtils.rightPad(" xxx", 5), " xxx ");
    }

    /**
     * to rightPad(final String str, final int size, final char padChar)
     */
    @Test
    public void rightPad2() {
        // rightPad("xxx", 5, 'a') == "xxxaa"
        Assert.assertEquals(StringUtils.rightPad("xxx", 5, 'a'), "xxxaa");

        // rightPad(" xxx", 5, 'b') == " xxxb"
        Assert.assertEquals(StringUtils.rightPad(" xxx", 5, 'b'), " xxxb");

        // rightPad(" xxx", 5, null) == " xxx "
        Assert.assertEquals(StringUtils.rightPad(" xxx", 5, null), " xxx ");
    }

    /**
     * to rightPad(final String str, final int size, String padStr)
     */
    @Test
    public void rightPad3() {
        // rightPad("xxx", 5, "abc") == "xxxab"
        Assert.assertEquals(StringUtils.rightPad("xxx", 5, "abc"), "xxxab");

        // rightPad(" xxx", 5, "b") == " xxxb"
        Assert.assertEquals(StringUtils.rightPad(" xxx", 5, "b"), " xxxb");

        // rightPad(" xxx", 5, null) == " xxx "
        Assert.assertEquals(StringUtils.rightPad(" xxx", 5, null), " xxx ");
    }

    @Test
    public void leftPad() {
        // leftPad("xxx", 5) == "  xxx"
        Assert.assertEquals(StringUtils.leftPad("xxx", 5), "  xxx");

        // leftPad("xxx ", 5) == " xxx "
        Assert.assertEquals(StringUtils.leftPad("xxx ", 5), " xxx ");
    }

    /**
     * to leftPad(final String str, final int size, final char padChar)
     */
    @Test
    public void leftPad2() {
        // leftPad("xxx", 5, 'a') == "aaxxx"
        Assert.assertEquals(StringUtils.leftPad("xxx", 5, 'a'), "aaxxx");

        // leftPad("xxx ", 5, 'b') == "bxxx "
        Assert.assertEquals(StringUtils.leftPad("xxx ", 5, 'b'), "bxxx ");

        // leftPad("xxx ", 5, null) == " xxx "
        Assert.assertEquals(StringUtils.leftPad("xxx ", 5, null), " xxx ");
    }

    /**
     * to leftPad(final String str, final int size, String padStr)
     */
    @Test
    public void leftPad3() {
        // leftPad("xxx", 5, "abc") == "abxxx"
        Assert.assertEquals(StringUtils.leftPad("xxx", 5, "abc"), "abxxx");

        // leftPad("xxx ", 5, "b") == "bxxx "
        Assert.assertEquals(StringUtils.leftPad("xxx ", 5, "b"), "bxxx ");

        // leftPad("xxx ", 5, null) == " xxx "
        Assert.assertEquals(StringUtils.leftPad("xxx ", 5, null), " xxx ");
    }

    @Test
    public void deleteWhitespace() {
        // deleteWhitespace(" ") == ""
        Assert.assertEquals(StringUtils.deleteWhitespace(" "), "");

        // deleteWhitespace(" abc ") == "abc"
        Assert.assertEquals(StringUtils.deleteWhitespace(" abc "), "abc");

        // deleteWhitespace(" a b c ") == "abc"
        Assert.assertEquals(StringUtils.deleteWhitespace(" a b c "), "abc");
    }
}
