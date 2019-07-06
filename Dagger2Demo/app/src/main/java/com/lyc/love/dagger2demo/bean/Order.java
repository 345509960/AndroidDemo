package com.lyc.love.dagger2demo.bean;

import javax.inject.Inject;


public class Order {

    private String name;

    public Order(Goods mGoods) {
        this.mGoods = mGoods;
    }

    private Goods mGoods;

    public Goods getmGoods() {
        return mGoods;
    }

    public void setmGoods(Goods mGoods) {
        this.mGoods = mGoods;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
