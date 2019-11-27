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
public class Test {
    @Id
    private long id;

    private String name;

    @Generated(hash = 986923955)
    public Test(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Generated(hash = 372557997)
    public Test() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
