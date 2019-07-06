package com.lyc.love.dagger2demo.bean;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
    @作者 lyc
    create date: $time
    des:
**/
public class Goods {

    public Goods(){

    }

    private long id;

    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
