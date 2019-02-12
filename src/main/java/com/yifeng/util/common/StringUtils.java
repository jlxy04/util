package com.yifeng.util.common;

/**
 * @description: 字符串常用工具类
 * @author: lijun
 * @date: 2019-1-18 14:52
 * @since 1.0.0
 */
public class StringUtils {

    private static final String SPACE = " ";

    private static final String EMPTY = "";

    private static final int PAD_LIMIT = 8192;

    private StringUtils() {}

    /**
     * <p>判断字符是否为空</p>
     *
     *  <pre>
     *  isEmpty("") == true
     *  isEmpty(" ") == false
     *  isEmpty("xxx") == false
     *  isEmpty("  xxx  ") == false
     *  isEmpty(null) == true
     *  </pre>
     *
     * @param cs 字符串
     * @return boolean
     */
    public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    /**
     * <p>判断字符是否不为空</p>
     * <pre>
     *  isNotEmpty("") == false
     *  isNotEmpty(" ") == true
     *  isNotEmpty("xxx") == true
     *  isNotEmpty("  xxx  ") == true
     *  isNotEmpty(null) == false
     * </pre>
     * @param cs
     * @return
     */
    public static boolean isNotEmpty(final CharSequence cs) {
        return !isEmpty(cs);
    }

    /**
     * <p> 是否为空白 <p/>
     * <pre>
     *   isBlank("") == true
     *   isBlank(" ") == true
     *   isBlank("xxx") == false
     *   isBlank("  xxx  ") == false
     *   isBlank(null) == true
     * </pre>
     * @param cs
     * @return
     */
    public static boolean isBlank(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p> 是否不为空白 <p/>
     * <pre>
     *   isBlank("") == false
     *   isBlank(" ") == false
     *   isBlank("xxx") == true
     *   isBlank("  xxx  ") == true
     *   isBlank(null) == false
     * </pre>
     * @param cs
     * @return
     */
    public static boolean isNotBlank(final CharSequence cs) {
        return !isBlank(cs);
    }

    /**
     * <p> 去除前后空格 <p/>
     * <pre>
     *  trim("") == ""
     *  trim(" ") == ""
     *  trim("xxx") == "xxx"
     *  trim("  xxx  ") == "xxx"
     *  trim(null) == null
     * </pre>
     * @param str
     * @return
     */
    public static String trim(final String str) {
        return str == null ? null : str.trim();
    }

    /**
     * <p> 去除前后空格，如果为空则为null <p/>
     * <pre>
     *  trimToNull("") == null
     *  trimToNull("xxx") == xxx
     *  trimToNull(" xxx ") == xxx
     *  trimToNull("  ") == null
     *  trimToNull(null) == null
     * </pre>
     * @param str
     * @return
     */
    public static String trimToNull(final String str) {
        final String ts = trim(str);
        return isEmpty(ts) ? null : ts;
    }

    /**
     * <p> 去除前后空格，如果为空则为null <p/>
     * <pre>
     *  trimToEmpty("") == ""
     *  trimToEmpty("xxx") == xxx
     *  trimToEmpty(" xxx ") == xxx
     *  trimToEmpty("  ") == ""
     *  trimToEmpty(null) == ""
     * </pre>
     * @param str
     * @return
     */
    public static String trimToEmpty(final String str) {
        return str == null ? EMPTY : str.trim();
    }

    /**
     * <p> 字符串截取 </p>
     * <pre>
     *     substring("abcdefg", 1) = bcdefg
     *     substring("abcdefg", 0) = abcdefg
     *     substring("abcdefg", -1) = g
     *     substring("abcdefg", -10) = abcdefg
     *     substring(null, 1) = null
     *     substring("abcdefg", "abcdefg".length()) = ""
     *     substring("abcdefg", 20) = ""
     * </pre>
     * @param str
     * @param start
     * @return
     */
    public static String substring(final String str, int start) {
        if (str == null) {
            return null;
        }

        if (start < 0) {
            start = str.length() + start;
        }

        if (start < 0) {
            start = 0;
        }
        if (start > str.length()) {
            return EMPTY;
        }

        return str.substring(start);
    }

    /**
     * <p>字符串截取</p>
     * <pre>
     *     substring("abcdefg", 1, 2) = bcdefg
     *     substring("abcdefg", 0, 1) = a
     *     substring("abcdefg", -1, 1) = ""
     *     substring(null, 1, 3) = null
     *     substring("abcdefg", "abcdefg".length(), "abcdefg".length()) = ""
     *     substring("abcdefg", 20) = ""
     * </pre>
     * @param str
     * @param start
     * @param end
     * @return
     */
    public static String substring(final String str, int start, int end) {
        if (str == null) {
            return null;
        }

        if (end < 0) {
            end = str.length() + end;
        }
        if (start < 0) {
            start = str.length() + start;
        }

        if (end > str.length()) {
            end = str.length();
        }

        if (start > end) {
            return EMPTY;
        }

        if (start < 0) {
            start = 0;
        }
        if (end < 0) {
            end = 0;
        }

        return str.substring(start, end);
    }

    /**
     * <p>字符串从左边开始截取</p>
     * <pre>
     * left("abcdefg", 1) = a
     * left("abcdefg", -1) = ""
     * left("abcdefg", 0) = ""
     * left("abcdefg", 10) = "abcdefg"
     * left("", 2) = ""
     * left(null, 2) = ""
     * </pre>
     * @param str
     * @param len
     * @return
     */
    public static String left(final String str, final int len) {
        if (str == null) {
            return null;
        }
        if (len < 0) {
            return EMPTY;
        }
        if (str.length() <= len) {
            return str;
        }
        return str.substring(0, len);
    }

    /**
     * <p>字符串从右边开始截取</p>
     * <pre>
     *  right("abcdefg", 1) = g
     *  right("abcdefg", -1) = ""
     *  right("abcdefg", 0) = ""
     *  right("abcdefg", 10) = "abcdefg"
     *  right("", 2) = ""
     *  right(null, 2) = ""
     * </pre>
     * @param str
     * @param len
     * @return
     */
    public static String right(final String str, final int len) {
        if (str == null) {
            return null;
        }
        if (len < 0) {
            return EMPTY;
        }
        if (str.length() <= len) {
            return str;
        }
        return str.substring(str.length() - len);
    }

    public static String rightPad(final String str, final int size) {
        return rightPad(str, size, ' ');
    }

    public static String rightPad(final String str, final int size, final char padChar) {
        if (str == null) {
            return null;
        }
        final int pads = size - str.length();
        if (pads <= 0) {
            return str;
        }
        if (pads > PAD_LIMIT) {
            return rightPad(str, size, String.valueOf(padChar));
        }
        return str.concat(repeat(padChar, pads));
    }

    public static String repeat(final char ch, final int repeat) {
        if (repeat <= 0) {
            return EMPTY;
        }
        final char[] buf = new char[repeat];
        for (int i = repeat - 1; i >= 0; i--) {
            buf[i] = ch;
        }
        return new String(buf);
    }

    public static String rightPad(final String str, final int size, String padStr) {
        if (str == null) {
            return null;
        }
        if (isEmpty(padStr)) {
            padStr = SPACE;
        }
        final int padLen = padStr.length();
        final int strLen = str.length();
        final int pads = size - strLen;
        if (pads <= 0) {
            return str;
        }
        if (padLen == 1 && pads <= PAD_LIMIT) {
            return rightPad(str, size, padStr.charAt(0));
        }

        if (pads == padLen) {
            return str.concat(padStr);
        } else if (pads < padLen) {
            return str.concat(padStr.substring(0, pads));
        } else {
            final char[] padding = new char[pads];
            final char[] padChars = padStr.toCharArray();
            for (int i = 0; i < pads; i++) {
                padding[i] = padChars[i % padLen];
            }
            return str.concat(new String(padding));
        }
    }

    public static String leftPad(final String str, final int size) {
        return leftPad(str, size, ' ');
    }

    public static String leftPad(final String str, final int size, final char padChar) {
        if (str == null) {
            return null;
        }
        final int pads = size - str.length();
        if (pads <= 0) {
            return str;
        }
        if (pads > PAD_LIMIT) {
            return leftPad(str, size, String.valueOf(padChar));
        }
        return repeat(padChar, pads).concat(str);
    }

    public static String leftPad(final String str, final int size, String padStr) {
        if (str == null) {
            return null;
        }
        if (isEmpty(padStr)) {
            padStr = SPACE;
        }
        final int padLen = padStr.length();
        final int strLen = str.length();
        final int pads = size - strLen;
        if (pads <= 0) {
            return str;
        }
        if (padLen == 1 && pads <= PAD_LIMIT) {
            return leftPad(str, size, padStr.charAt(0));
        }

        if (pads == padLen) {
            return padStr.concat(str);
        } else if (pads < padLen) {
            return padStr.substring(0, pads).concat(str);
        } else {
            final char[] padding = new char[pads];
            final char[] padChars = padStr.toCharArray();
            for (int i = 0; i < pads; i++) {
                padding[i] = padChars[i % padLen];
            }
            return new String(padding).concat(str);
        }
    }

    public static String deleteWhitespace(final String str) {
        if (isEmpty(str)) {
            return str;
        }
        final int sz = str.length();
        final char[] chs = new char[sz];
        int count = 0;
        for (int i = 0; i < sz; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                chs[count++] = str.charAt(i);
            }
        }
        if (count == sz) {
            return str;
        }
        return new String(chs, 0, count);
    }

}
