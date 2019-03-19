package com.github.utils.bean;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * @Description:
 * @author: lijun
 * @Date: 2019-1-19 16:48
 */
@XmlRootElement(name="user")
public class User {

    private String id;

    private String name;

    private int age;

    private Date date;

    private Goods goods;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }
}
