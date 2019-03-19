package com.github.utils.collection;

import com.github.jlxy04.utils.collection.ArrayUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * @Description:
 * @author: lijun
 * @Date: 2019-3-19 13:20
 */
public class ArrayUtilsTest {

    private String[] sa = {"s1", "s2", "dfafdsf", "s3", "666"};

    @Test
    public void add() {
        String[] sa1 = {"s1"};
        String[] sa2 = ArrayUtils.add(sa1, "s2");
        Assert.assertEquals(2, sa2.length);
        Assert.assertArrayEquals(new String[]{"s1", "s2"}, sa2);
    }

    @Test
    public void contains() {
        Assert.assertFalse(ArrayUtils.contains(sa, null));

        Assert.assertTrue(ArrayUtils.contains(sa, "666"));
    }

    @Test
    public void indexOf() {
        Assert.assertSame(-1, ArrayUtils.indexOf(sa, null));

        Assert.assertSame(-1, ArrayUtils.indexOf(sa, "fdsax"));

        Assert.assertSame(0, ArrayUtils.indexOf(sa, "s1"));

        Assert.assertSame(4, ArrayUtils.indexOf(sa, "666"));
    }
}
