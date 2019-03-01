package com.yifeng.util.common;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.validator.ValidateWith;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @Description:
 * @author: lijun
 * @Date: 2019-2-12 15:07
 */
public class DateTimeUtilsTest {

    private Date date;

    private String dateStr;

    private String bigDateStr;

    @Before
    public void init() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, 01, 15, 9, 12, 10);
        calendar.set(Calendar.MILLISECOND, 200);
        date = calendar.getTime();

        dateStr = "2019-02-15 09:12:10";

        bigDateStr = "2030-02-15 09:12:10";
    }

    @Test
    public void formatDate() {
        // formatDate(null) throw IllegalArgumentException
        boolean isThrow = false;
        try {
            DateTimeUtils.formatDate(null);
        } catch (IllegalArgumentException e) {
            isThrow = true;
        }
        Assert.assertTrue(isThrow);

        // formatDate(date) == "2019-02-15 09:12:10"
        Assert.assertEquals(DateTimeUtils.formatDate(date), "2019-02-15 09:12:10");
    }

    /**
     * to formatDate(final Date date, final String pattern)
     */
    @Test
    public void formatDate2() {
        // formatDate(date, "yyyy/MM/dd HH:mm:ss") = "2019/02/15 09:12:10"
        Assert.assertEquals(DateTimeUtils.formatDate(date, "yyyy/MM/dd HH:mm:ss"), "2019/02/15 09:12:10");

        // formatDate(date, "abc") throw IllegalArgumentException(Illegal pattern)
        boolean isThrow = false;
        try {
            DateTimeUtils.formatDate(date, "abc");
        } catch (IllegalArgumentException e) {
            isThrow = true;
        }
        Assert.assertTrue(isThrow);
    }

    @Test
    public void parseDate() {
        // "2019-02-15 09:12:10"
        Date date = DateTimeUtils.parseDate(dateStr);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Assert.assertTrue(calendar.get(Calendar.YEAR) == 2019);
        Assert.assertTrue(calendar.get(Calendar.MONTH) == 1);
        Assert.assertTrue(calendar.get(Calendar.DATE) == 15);

        Assert.assertTrue(calendar.get(Calendar.HOUR) == 9);
        Assert.assertTrue(calendar.get(Calendar.MINUTE) == 12);
        Assert.assertTrue(calendar.get(Calendar.SECOND) == 10);
    }

    /**
     * to public static Date parseDate(final String str, final String... parsePatterns)
     */
    @Test
    public void parseDate2() {
        // "2019-02-15 09:12:10"
        Date date = DateTimeUtils.parseDate(dateStr, "yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Assert.assertTrue(calendar.get(Calendar.YEAR) == 2019);
        Assert.assertTrue(calendar.get(Calendar.MONTH) == 1);
        Assert.assertTrue(calendar.get(Calendar.DATE) == 15);

        Assert.assertTrue(calendar.get(Calendar.HOUR) == 9);
        Assert.assertTrue(calendar.get(Calendar.MINUTE) == 12);
        Assert.assertTrue(calendar.get(Calendar.SECOND) == 10);

        // "2019-02-15 09:12:10"
        Date date2 = DateTimeUtils.parseDate(dateStr, new String[]{"yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd HH:mm:ss"});
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);
        Assert.assertTrue(calendar2.get(Calendar.YEAR) == 2019);
        Assert.assertTrue(calendar2.get(Calendar.MONTH) == 1);
        Assert.assertTrue(calendar2.get(Calendar.DATE) == 15);

        Assert.assertTrue(calendar2.get(Calendar.HOUR) == 9);
        Assert.assertTrue(calendar2.get(Calendar.MINUTE) == 12);
        Assert.assertTrue(calendar2.get(Calendar.SECOND) == 10);
    }

    /**
     * to public static Date parseDate(final String str, final Locale locale, final String... parsePatterns)
     */
    @Test
    public void parseDate3() {
        // "2019-02-15 09:12:10"
        Date date = DateTimeUtils.parseDate(dateStr, Locale.CHINA, "yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Assert.assertTrue(calendar.get(Calendar.YEAR) == 2019);
        Assert.assertTrue(calendar.get(Calendar.MONTH) == 1);
        Assert.assertTrue(calendar.get(Calendar.DATE) == 15);

        Assert.assertTrue(calendar.get(Calendar.HOUR) == 9);
        Assert.assertTrue(calendar.get(Calendar.MINUTE) == 12);
        Assert.assertTrue(calendar.get(Calendar.SECOND) == 10);

        Date date2 = DateTimeUtils.parseDate(dateStr, Locale.US, "yyyy-MM-dd HH:mm:ss");
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);
        Assert.assertTrue(calendar2.get(Calendar.YEAR) == 2019);
        Assert.assertTrue(calendar2.get(Calendar.MONTH) == 1);
        Assert.assertTrue(calendar2.get(Calendar.DATE) == 15);

        Assert.assertTrue(calendar2.get(Calendar.HOUR) == 9);
        Assert.assertTrue(calendar2.get(Calendar.MINUTE) == 12);
        Assert.assertTrue(calendar2.get(Calendar.SECOND) == 10);
    }

    @Test
    public void compareDate() {
        boolean rs = false;
        try {
            DateTimeUtils.compareDate(null, null);
        } catch (Exception e) {
            rs = true;
        }
        Assert.assertTrue(rs);

        Assert.assertTrue(DateTimeUtils.compareDate(new Date(), new Date()) == 0);

        Assert.assertTrue(DateTimeUtils.compareDate(DateTimeUtils.parseDate(dateStr), new Date()) < 0);

        Assert.assertTrue(DateTimeUtils.compareDate(DateTimeUtils.parseDate(bigDateStr), new Date()) > 0);
    }

    @Test
    public void compareTime() {
        Assert.assertTrue(DateTimeUtils.compareTime(new Date(), new Date()) == 0);

        Assert.assertTrue(DateTimeUtils.compareTime(DateTimeUtils.parseDate("2019-02-15 09:12:10"), DateTimeUtils.parseDate("2019-02-02 21:21:21")) < 0);

        Assert.assertTrue(DateTimeUtils.compareTime(DateTimeUtils.parseDate("2019-02-15 21:30:10"), DateTimeUtils.parseDate("2019-02-02 21:21:21")) > 0);
    }

    @Test
    public void toCalendar() {

        Assert.assertNull(DateTimeUtils.toCalendar(null));

        // "2019-02-15 09:12:10"
        Calendar calendar = DateTimeUtils.toCalendar(DateTimeUtils.parseDate(dateStr));

        Assert.assertTrue(calendar.get(Calendar.YEAR) == 2019);
        Assert.assertTrue(calendar.get(Calendar.MONTH) == 1);
        Assert.assertTrue(calendar.get(Calendar.DATE) == 15);

        Assert.assertTrue(calendar.get(Calendar.HOUR) == 9);
        Assert.assertTrue(calendar.get(Calendar.MINUTE) == 12);
        Assert.assertTrue(calendar.get(Calendar.SECOND) == 10);
    }

    @Test
    public void addYears() {
        boolean rs = false;
        try {
            DateTimeUtils.addYears(null, 1);
        } catch (Exception e) {
            rs = true;
        }
        Assert.assertTrue(rs);

        // 2019-02-15 09:12:10
        Date date2 = DateTimeUtils.addYears(date, 1);
        int year = DateTimeUtils.toCalendar(date2).get(Calendar.YEAR);
        Assert.assertTrue(year == 2020);

        // 2019-02-15 09:12:10
        Date date3 = DateTimeUtils.addYears(date, -1);
        int year2 = DateTimeUtils.toCalendar(date3).get(Calendar.YEAR);
        Assert.assertTrue(year2 == 2018);
    }

    @Test
    public void addMonths() {
        boolean rs = false;
        try {
            DateTimeUtils.addMonths(null, 1);
        } catch (Exception e) {
            rs = true;
        }
        Assert.assertTrue(rs);


        // 2019-02-15 09:12:10
        Date date2 = DateTimeUtils.addMonths(date, 1);
        int month = DateTimeUtils.toCalendar(date2).get(Calendar.MONTH);
        Assert.assertTrue(month == 02);

        // 2019-02-15 09:12:10
        Date date3 = DateTimeUtils.addMonths(date, -1);
        int month2 = DateTimeUtils.toCalendar(date3).get(Calendar.MONTH);
        Assert.assertTrue(month2 == 0);
    }

    @Test
    public void addWeeks() {
        boolean rs = false;
        try {
            DateTimeUtils.addWeeks(null, 1);
        } catch (Exception e) {
            rs = true;
        }
        Assert.assertTrue(rs);


        // 2019-02-15 09:12:10
        Date date2 = DateTimeUtils.addWeeks(date, 1);
        int week = DateTimeUtils.toCalendar(date2).get(Calendar.WEEK_OF_YEAR);
        Assert.assertTrue(week == 8);

        // 2019-02-15 09:12:10
        Date date3 = DateTimeUtils.addWeeks(date, -1);
        int week2 = DateTimeUtils.toCalendar(date3).get(Calendar.WEEK_OF_YEAR);
        Assert.assertTrue(week2 == 6);
    }

    @Test
    public void addDays() {
        boolean rs = false;
        try {
            DateTimeUtils.addDays(null, 1);
        } catch (Exception e) {
            rs = true;
        }
        Assert.assertTrue(rs);


        // 2019-02-15 09:12:10
        Date date2 = DateTimeUtils.addDays(date, 1);
        int day = DateTimeUtils.toCalendar(date2).get(Calendar.DAY_OF_MONTH);
        Assert.assertTrue(day == 16);

        // 2019-02-15 09:12:10
        Date date3 = DateTimeUtils.addDays(date, -1);
        int day2 = DateTimeUtils.toCalendar(date3).get(Calendar.DAY_OF_MONTH);
        Assert.assertTrue(day2 == 14);
    }

    @Test
    public void addHours() {
        boolean rs = false;
        try {
            DateTimeUtils.addHours(null, 1);
        } catch (Exception e) {
            rs = true;
        }
        Assert.assertTrue(rs);


        // 2019-02-15 09:12:10
        Date date2 = DateTimeUtils.addHours(date, 1);
        int hours = DateTimeUtils.toCalendar(date2).get(Calendar.HOUR_OF_DAY);
        Assert.assertTrue(hours == 10);

        // 2019-02-15 09:12:10
        Date date3 = DateTimeUtils.addHours(date, -1);
        int hours2 = DateTimeUtils.toCalendar(date3).get(Calendar.HOUR_OF_DAY);
        Assert.assertTrue(hours2 == 8);
    }

    @Test
    public void addMinutes() {
        boolean rs = false;
        try {
            DateTimeUtils.addMinutes(null, 1);
        } catch (Exception e) {
            rs = true;
        }
        Assert.assertTrue(rs);


        // 2019-02-15 09:12:10
        Date date2 = DateTimeUtils.addMinutes(date, 1);
        int minutes = DateTimeUtils.toCalendar(date2).get(Calendar.MINUTE);
        Assert.assertTrue(minutes == 13);

        // 2019-02-15 09:12:10
        Date date3 = DateTimeUtils.addMinutes(date, -1);
        int minutes2 = DateTimeUtils.toCalendar(date3).get(Calendar.MINUTE);
        Assert.assertTrue(minutes2 == 11);
    }

    @Test
    public void addSeconds() {
        boolean rs = false;
        try {
            DateTimeUtils.addSeconds(null, 1);
        } catch (Exception e) {
            rs = true;
        }
        Assert.assertTrue(rs);


        // 2019-02-15 09:12:10
        Date date2 = DateTimeUtils.addSeconds(date, 1);
        int seconds = DateTimeUtils.toCalendar(date2).get(Calendar.SECOND);
        Assert.assertTrue(seconds == 11);

        // 2019-02-15 09:12:10
        Date date3 = DateTimeUtils.addSeconds(date, -1);
        int seconds2 = DateTimeUtils.toCalendar(date3).get(Calendar.SECOND);
        Assert.assertTrue(seconds2 == 9);
    }

    @Test
    public void addMilliseconds() {
        boolean rs = false;
        try {
            DateTimeUtils.addMilliseconds(null, 1);
        } catch (Exception e) {
            rs = true;
        }
        Assert.assertTrue(rs);


        // 2019-02-15 09:12:10.200
        Date date2 = DateTimeUtils.addMilliseconds(date, 1);
        int seconds = DateTimeUtils.toCalendar(date2).get(Calendar.MILLISECOND);
        Assert.assertTrue(seconds == 201);

        // 2019-02-15 09:12:10.200
        Date date3 = DateTimeUtils.addMilliseconds(date, -1);
        int seconds2 = DateTimeUtils.toCalendar(date3).get(Calendar.MILLISECOND);
        Assert.assertTrue(seconds2 == 199);
    }
}
