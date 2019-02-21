package com.yifeng.util.common;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * @Description:
 * @author: lijun
 * @Date: 2019-2-12 15:07
 */
public class DateTimeUtilsTest {

    private Date date;

    private String dateStr;

    @Before
    public void init() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, 01, 10, 23, 12, 10);
        date = calendar.getTime();

        dateStr = "2019-02-15 09:12:10";
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

        // formatDate(date) == "2019-02-10 23:12:10"
        Assert.assertEquals(DateTimeUtils.formatDate(date), "2019-02-10 23:12:10");
    }

    /**
     * to formatDate(final Date date, final String pattern)
     */
    @Test
    public void formatDate2() {
        // formatDate(date, "yyyy/MM/dd HH:mm:ss") = "2019/02/10 23:12:10"
        Assert.assertEquals(DateTimeUtils.formatDate(date, "yyyy/MM/dd HH:mm:ss"), "2019/02/10 23:12:10");


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
        System.out.println(calendar.get(Calendar.YEAR));
        Assert.assertTrue(calendar.get(Calendar.YEAR) == 2019);
        Assert.assertTrue(calendar.get(Calendar.MONTH) == 1);
    }
}
