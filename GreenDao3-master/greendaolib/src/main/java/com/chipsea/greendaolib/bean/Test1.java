package com.chipsea.greendaolib.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by liangyc
 * Time :2018/3/23
 * Des:
 */

@Entity
public class Test1 {
    private String name;
    @Id(autoincrement = true)
    private long id;
    @Generated(hash = 433169841)
    public Test1(String name, long id) {
        this.name = name;
        this.id = id;
    }
    @Generated(hash = 2050631462)
    public Test1() {
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
}
