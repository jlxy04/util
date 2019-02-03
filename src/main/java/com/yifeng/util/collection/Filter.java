package com.yifeng.util.collection;

/**
 * @Description:
 * @author: lijun
 * @Date: 2019-1-18 17:17
 */
public interface Filter<T> {

    /**
     * 返回 false 则移动元素
     * @param t
     * @return
     */
    public boolean evaluate(T t);
}
