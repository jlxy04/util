package com.yifeng.util.collection;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Description:
 * @author: lijun
 * @Date: 2019-3-19 13:20
 */
public class ArrayUtilsTest {

    @Test
    public void add() {
        String[] sa = ArrayUtils.add(new String[]{"s1"}, "s2");
        Assert.assertEquals(2, sa.length);
        Assert.assertArrayEquals(new String[]{"s1", "s2"}, sa);
    }
}
