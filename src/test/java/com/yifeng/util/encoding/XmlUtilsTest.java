package com.yifeng.util.encoding;

import com.yifeng.util.bean.Goods;
import com.yifeng.util.bean.User;
import com.yifeng.util.generate.GenerUtils;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @Description:
 * @author: lijun
 * @Date: 2019-1-19 16:45
 */
public class XmlUtilsTest {


    @Test
    public void parseXmlTest() {
//        XmlUtils.parseXml();
    }

    @Test
    public void toXmlTest() {
        User user = new User();
        user.setId(GenerUtils.id());
        user.setAge(18);
        Goods goods = new Goods();
        goods.setGoodsCode(GenerUtils.id());
        goods.setPrice(new BigDecimal("15.3"));
        user.setGoods(goods);
        System.out.println(XmlUtils.toXml(user));
    }
}
