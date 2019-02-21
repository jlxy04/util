package com.yifeng.util.test;

import com.alibaba.fastjson.JSON;
import com.yifeng.util.collection.CollectionUtils;
import com.yifeng.util.generate.RandomUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Test {

    public static List<ActivityPrize> random(List<ActivityPrize> activityPrizeList) {

        BigDecimal totalRateTemp = BigDecimal.ZERO;
        for (ActivityPrize activityPrize : activityPrizeList) {
            totalRateTemp = totalRateTemp.add(activityPrize.getRate());
        }

        // 必中的项
        List<ActivityPrize> list = new ArrayList<>();
        BigDecimal finalTotalRateTemp = totalRateTemp;
        activityPrizeList = activityPrizeList.stream().filter(t -> {
            boolean r = true;
            // 必中
            if(t.getRate().compareTo(BigDecimal.ONE) >= 0) {
                r = false;
                list.add(t);
            }
            // 总比例大于0时才去除库存为0的
            if(finalTotalRateTemp.compareTo(BigDecimal.ONE) >= 0) {
                // 过滤掉库存为0的
                if (t.getRemainingPrizeNum() <= 0) {
                    r = false;
                }
            }

            return r;
        }).sorted((t1, t2) -> {
            if(t1.getRate().compareTo(t2.getRate()) <= 0) {
                return -1;
            }
            return 1;
        }).collect(Collectors.toList());

        if(CollectionUtils.isNotEmpty(activityPrizeList)) {

            // 取精度
            int precision = 0;
            BigDecimal totalRate = BigDecimal.ZERO;
            for (ActivityPrize activityPrize : activityPrizeList) {
                totalRate = totalRate.add(activityPrize.getRate());
                if(activityPrize.getRate().scale() > precision) {
                    precision = activityPrize.getRate().scale();
                }
            }
            System.out.println("精度为 -> " + precision + "，总比率为 ->" + totalRate);

            // 基准值
            BigDecimal precisionBase = new BigDecimal("10").pow(precision);
            System.out.println("基准值为 -> " + precisionBase);

            // 区间list
            List<BigDecimal> rateList = new ArrayList<>();
            List<BigDecimal> rateNumList = new ArrayList<>();
            for (ActivityPrize activityPrize : activityPrizeList) {
                rateList.add(activityPrize.getRate().multiply(precisionBase));
            }
            BigDecimal total = BigDecimal.ZERO;
            for (BigDecimal bigDecimal : rateList) {
                total = total.add(bigDecimal);
                rateNumList.add(total);
            }
            int randomNum = 0;
            System.out.println(totalRate.compareTo(BigDecimal.ONE));
            if (totalRateTemp.compareTo(BigDecimal.ONE) < 0) {
                // 如果小于1，则有可能不中
                rateNumList.add(precisionBase);
                randomNum = RandomUtils.nextInt(0, precisionBase.intValue());
            } else if(totalRateTemp.compareTo(BigDecimal.ONE) >= 0) {
                // 如果等于1，则必中
                randomNum = RandomUtils.nextInt(0, total.intValue());
            }

            System.out.println("rateList -> " + rateList);
            System.out.println("rateNumList -> " + rateNumList);
            System.out.println("precisionBase -> " + precisionBase.intValue());

            System.out.println("totalVal -> " + total.intValue());

            System.out.println("随机数 -> " + randomNum);

            int winningIndex = -1;
            for (int i = 0; i < rateNumList.size(); i++) {
                BigDecimal b =  rateNumList.get(i);
                if(i == 0) {
                    if(new BigDecimal(randomNum).compareTo(b) <= 0) {
                        winningIndex = 0;
                        continue ;
                    }
                } else if(i == rateNumList.size()) {
                    // 如果是最后一个
                    winningIndex = i;
                    continue;
                } else {
                    if (new BigDecimal(randomNum).compareTo(rateNumList.get(i - 1)) >= 0 && new BigDecimal(randomNum).compareTo(b) < 0) {
                        winningIndex = i;
                        if (winningIndex + 1 > rateList.size()) {
                            winningIndex = -1;
                        }
                    }
                }
            }
            System.out.println("取的个数为 -> " + winningIndex);
            if (winningIndex != -1) {
                list.add(activityPrizeList.get(winningIndex));
            }
        }
        return list;
    }

    public static void main(String[] args) {
        List<ActivityPrize> activityPrizeList = new ArrayList<>();
        ActivityPrize activityPrize = new ActivityPrize();
        activityPrize.setId("1");
        activityPrize.setRate(new BigDecimal("0.1"));
        activityPrize.setRemainingPrizeNum(10);
        activityPrizeList.add(activityPrize);

        ActivityPrize activityPrize2 = new ActivityPrize();
        activityPrize2.setId("2");
        activityPrize2.setRate(new BigDecimal("0.1"));
        activityPrize2.setRemainingPrizeNum(0);
        activityPrizeList.add(activityPrize2);

        ActivityPrize activityPrize3 = new ActivityPrize();
        activityPrize3.setId("3");
        activityPrize3.setRate(new BigDecimal("0.27"));
        activityPrize3.setRemainingPrizeNum(50);
        activityPrizeList.add(activityPrize3);

        ActivityPrize activityPrize4 = new ActivityPrize();
        activityPrize4.setId("5");
        activityPrize4.setRate(new BigDecimal("0.21"));
        activityPrize4.setRemainingPrizeNum(1000);
        activityPrizeList.add(activityPrize4);

        ActivityPrize activityPrize5 = new ActivityPrize();
        activityPrize5.setId("6");
        activityPrize5.setRate(new BigDecimal("0.22"));
        activityPrize5.setRemainingPrizeNum(10000);
        activityPrizeList.add(activityPrize5);

        System.out.println(JSON.toJSONString(random(activityPrizeList)));
    }
}
