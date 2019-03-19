package com.github.jlxy04.utils.page;

/**
 * @Description:
 * @author: lijun
 * @Date: 2019-1-19 16:01
 */
public class Pagination {

    private int pageNum = 1;
    private int pageSize = 100;
    private String orderColumn;

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

    public String getOrderColumn() {
        return orderColumn;
    }

    public void setOrderColumn(String orderColumn) {
        this.orderColumn = orderColumn;
    }
}
