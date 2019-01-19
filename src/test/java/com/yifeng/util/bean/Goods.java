package com.yifeng.util.bean;

import java.math.BigDecimal;

/**
 * @Description:
 * @author: lijun
 * @Date: 2019-1-19 16:50
 */
public class Goods {

    private String goodsCode;

    private BigDecimal price;

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
