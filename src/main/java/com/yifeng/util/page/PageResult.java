package com.yifeng.util.page;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * @Description:
 * @author: lijun
 * @Date: 2019-3-18 15:55
 */
public class PageResult<T> implements Serializable {

    public PageResult() {
    }

    public PageResult(long total, List<T> rows, int pageNum, int pageSize) {
        this.total = total;
        this.rows = rows;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    private long total;

    private List<T> rows;

    private int pageNum = 1;

    private int pageSize = 10;

    private int totalPage;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        if (total > 0) {
            if (total % pageSize > 0) {
                return (int) (total / pageSize + 1);
            } else {
                return (int) total / pageSize;
            }
        }
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
