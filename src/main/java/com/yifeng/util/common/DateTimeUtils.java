package com.yifeng.util.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @Description:
 * @author: lijun
 * @Date: 2019-1-18 15:11
 */
public class DateTimeUtils {

    private static final Logger log = LoggerFactory.getLogger(DateTimeUtils.class);

    private static final String EMPTY = "";

    private static final String[] FORMAT_PATTERNS = {"yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd HH:mm:ss", "yyyyMMddHHmmss"};

    private static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private DateTimeUtils() {}

    /**
     * <p>格式化字符串，默认格式 yyyy-MM-dd HH:mm:ss </p>
     * <pre>
     *  formatDate(null) throw IllegalArgumentException
     *  formatDate(date) == "2019-02-10 23:12:10"
     * </pre>
     * @param date
     * @return
     */
    public static String formatDate(final Date date) {
        return formatDate(date, DEFAULT_PATTERN);

    }

    /**
     * <p>格式化日期</p>
     * <pre>
     * formatDate(date, "yyyy/MM/dd HH:mm:ss") = "2019/02/10 23:12:10"
     * formatDate(date, "abc") throw IllegalArgumentException(Illegal pattern)
     * </pre>
     * @param date
     * @param pattern 格式字符
     * @return
     */
    public static String formatDate(final Date date, final String pattern) {
        if (date == null || pattern == null) {
            throw new IllegalArgumentException("Date and Patterns must not be null");
        }

        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * <p>解析日期，默认支持的格式为："yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd HH:mm:ss", "yyyyMMddHHmmss"</p>
     * <pre>
     *
     * </pre>
     * @param str
     * @return
     * @throws ParseException
     */
    public static Date parseDate(final String str) {
        return parseDate(str, null, FORMAT_PATTERNS);
    }

    /**
     * <p>解析日期</p>
     * @param str 日期字符串
     * @param parsePatterns 格式数组，支持输入多个格式
     * @return
     */
    public static Date parseDate(final String str, final String... parsePatterns) {
        return parseDate(str, null, parsePatterns);
    }

    /**
     * <p>解析日期</p>
     * @param str
     * @param locale
     * @param parsePatterns
     * @return
     */
    public static Date parseDate(final String str, final Locale locale, final String... parsePatterns) {
        try {
            return parseDateWithLeniency(str, locale, parsePatterns, true);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private static Date parseDateWithLeniency(
            final String str, final Locale locale, final String[] parsePatterns, final boolean lenient) throws ParseException {
        if (str == null || parsePatterns == null) {
            throw new IllegalArgumentException("Date and Patterns must not be null");
        }

        SimpleDateFormat parser;
        if (locale == null) {
            parser = new SimpleDateFormat();
        } else {
            parser = new SimpleDateFormat("", locale);
        }

        parser.setLenient(lenient);
        final ParsePosition pos = new ParsePosition(0);
        for (final String parsePattern : parsePatterns) {

            String pattern = parsePattern;

            // LANG-530 - need to make sure 'ZZ' output doesn't get passed to SimpleDateFormat
            if (parsePattern.endsWith("ZZ")) {
                pattern = pattern.substring(0, pattern.length() - 1);
            }

            parser.applyPattern(pattern);
            pos.setIndex(0);

            String str2 = str;
            if (parsePattern.endsWith("ZZ")) {
                str2 = str.replaceAll("([-+][0-9][0-9]):([0-9][0-9])$", "$1$2");
            }

            final Date date = parser.parse(str2, pos);
            if (date != null && pos.getIndex() == str2.length()) {
                return date;
            }
        }

        throw new ParseException("Unable to parse the date: " + str, -1);
    }

    /**
     * <p>比较二个日期大小</p>
     * <pre>
     *     compareDate(new Date(), new Date()) == 0
     *     compareDate(parseDate("2019-02-15 09:12:10"), parseDate("2019-02-27 09:12:10")) < 0
     *     compareDate(parseDate("2030-02-15 09:12:10"), parseDate("2030-02-27 09:12:10")) > 0
     * </pre>
     * @param date1
     * @param date2
     * @return
     */
    public static int compareDate(Date date1, Date date2) {
        if(date1 == null || date2 == null) {
            throw new IllegalArgumentException("date1 and date2 must not be null");
        }

        if(date1.getTime() == date2.getTime()) {
            return 0;
        }

        return date1.getTime() > date2.getTime() ? 1 : -1;
    }

    /**
     * <p>比较日期中二个时间的大小</p>
     * <pre>
     *    compareTime(new Date(), new Date()) == 0
     *    compareTime(parseDate("2019-02-15 09:12:10"), parseDate("2019-02-02 21:21:21")) < 0
     *    compareTime(parseDate("2019-02-15 21:30:10"), parseDate("2019-02-02 21:21:21")) > 0
     * </pre>
     * @param date1
     * @param date2
     * @return
     */
    public static int compareTime(Date date1, Date date2) {
        if(date1 == null || date2 == null) {
            throw new IllegalArgumentException("date1 and date2 must not be null");
        }

        Calendar calendar = toCalendar(date1);
        Calendar calendar2 = toCalendar(date2);

        // 比较 时
        int h = calendar.get(Calendar.HOUR_OF_DAY);
        int h2 = calendar2.get(Calendar.HOUR_OF_DAY);
        if(h > h2) {
            return 1;
        } else if(h < h2) {
            return -1;
        }

        // 分
        int m = calendar.get(Calendar.MINUTE);
        int m2 = calendar2.get(Calendar.MINUTE);
        if(m > m2) {
            return 1;
        } else if(m < m2) {
            return -1;
        }

        // 秒
        int s = calendar.get(Calendar.SECOND);
        int s2 = calendar2.get(Calendar.SECOND);
        if(s > s2) {
            return 1;
        } else if(s < s2) {
            return -1;
        }
        return 0;
    }

    /**
     * <p>日期转日历</p>
     * <pre>
     *     toCalendar(null) == null
     * </pre>
     * @param date
     * @return
     */
    public static Calendar toCalendar(Date date) {
        if(date == null) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * <p>增加年份</p>
     * <pre>
     *     addYears(date, 1).getYear == 2020
     *     addYears(date, -1).getYear = 2018
     * </pre>
     * @param date
     * @param amount 正数为加, 负数为减
     * @return
     */
    public static Date addYears(final Date date, final int amount) {
        return add(date, Calendar.YEAR, amount);
    }

    /**
     * <p>增加月份</p>
     * @param date
     * @param amount 正数为加, 负数为减
     * @return
     */
    public static Date addMonths(final Date date, final int amount) {
        return add(date, Calendar.MONTH, amount);
    }

    /**
     * <p>增加周数</p>
     * @param date
     * @param amount 正数为加, 负数为减
     * @return
     */
    public static Date addWeeks(final Date date, final int amount) {
        return add(date, Calendar.WEEK_OF_YEAR, amount);
    }

    /**
     * <p>增加天数</p>
     * @param date
     * @param amount 正数为加, 负数为减
     * @return
     */
    public static Date addDays(final Date date, final int amount) {
        return add(date, Calendar.DAY_OF_MONTH, amount);
    }

    /**
     * <p>增加小时</p>
     * @param date
     * @param amount 正数为加, 负数为减
     * @return
     */
    public static Date addHours(final Date date, final int amount) {
        return add(date, Calendar.HOUR_OF_DAY, amount);
    }

    /**
     * <p>增加分钟数</p>
     * @param date
     * @param amount 正数为加, 负数为减
     * @return
     */
    public static Date addMinutes(final Date date, final int amount) {
        return add(date, Calendar.MINUTE, amount);
    }

    /**
     * <p>增加秒数</p>
     * @param date
     * @param amount 正数为加, 负数为减
     * @return
     */
    public static Date addSeconds(final Date date, final int amount) {
        return add(date, Calendar.SECOND, amount);
    }

    /**
     * <p>增加毫秒数</p>
     * @param date
     * @param amount 正数为加, 负数为减
     * @return
     */
    public static Date addMilliseconds(final Date date, final int amount) {
        return add(date, Calendar.MILLISECOND, amount);
    }

    private static Date add(final Date date, final int calendarField, final int amount) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        final Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(calendarField, amount);
        return c.getTime();
    }
}
