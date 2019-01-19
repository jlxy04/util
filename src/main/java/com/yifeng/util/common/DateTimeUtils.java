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

    private static final String[] FORMART_PATTERNS = {"yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd HH:mm:ss", "yyyyMMddHHmmss"};

    private static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private DateTimeUtils() {}

    public static String formatDate(final Date date) {
        return formatDate(date, DEFAULT_PATTERN);

    }

    public static String formatDate(final Date date, final String pattern) {
        if (date == null || pattern == null) {
            throw new IllegalArgumentException("Date and Patterns must not be null");
        }

        return new SimpleDateFormat(pattern).format(date);
    }

    public static Date parseDate(final String str) throws ParseException {
        return parseDate(str, null, FORMART_PATTERNS);
    }

    public static Date parseDate(final String str, final String... parsePatterns) {
        return parseDate(str, null, parsePatterns);
    }

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

    public static int compareDate(Date date1, Date date2) {
        if(date1 == null || date2 == null) {
            throw new IllegalArgumentException("date1 and date2 must not be null");
        }

        if(date1.getTime() == date2.getTime()) {
            return 0;
        }

        return date1.getTime() > date2.getTime() ? 1 : -1;
    }

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

    public static Calendar toCalendar(Date date) {
        if(date == null) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    public static Date addYears(final Date date, final int amount) {
        return add(date, Calendar.YEAR, amount);
    }

    public static Date addMonths(final Date date, final int amount) {
        return add(date, Calendar.MONTH, amount);
    }

    public static Date addWeeks(final Date date, final int amount) {
        return add(date, Calendar.WEEK_OF_YEAR, amount);
    }

    public static Date addDays(final Date date, final int amount) {
        return add(date, Calendar.DAY_OF_MONTH, amount);
    }

    public static Date addHours(final Date date, final int amount) {
        return add(date, Calendar.HOUR_OF_DAY, amount);
    }

    public static Date addMinutes(final Date date, final int amount) {
        return add(date, Calendar.MINUTE, amount);
    }

    public static Date addSeconds(final Date date, final int amount) {
        return add(date, Calendar.SECOND, amount);
    }

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
