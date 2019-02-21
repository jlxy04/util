package com.yifeng.util.test;

import java.math.BigDecimal;

public class ActivityPrize {
    private String id;
    private BigDecimal rate;
    private Integer remainingPrizeNum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Integer getRemainingPrizeNum() {
        return remainingPrizeNum;
    }

    public void setRemainingPrizeNum(Integer remainingPrizeNum) {
        this.remainingPrizeNum = remainingPrizeNum;
    }
}
