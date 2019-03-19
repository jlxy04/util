package com.github.utils.page;

import com.github.jlxy04.utils.page.PageResult;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;

/**
 * @Description:
 * @author: lijun
 * @Date: 2019-3-18 16:05
 */
public class PageResultTest {

    @Test
    public void test() {
        PageResult<String> pageResult = new PageResult<>();
        Assert.assertNotSame(pageResult.getTotalPage(), null);

        PageResult<String> pageResult2 = new PageResult<>(130, Collections.emptyList(), 1, 10);
        Assert.assertEquals(pageResult2.getTotalPage(), 13);

        PageResult<String> pageResult3 = new PageResult<>(115, Collections.emptyList(), 1, 10);
        Assert.assertEquals(pageResult3.getTotalPage(), 12);
    }
}
