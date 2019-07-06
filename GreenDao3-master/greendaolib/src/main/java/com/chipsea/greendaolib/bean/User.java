package com.chipsea.greendaolib.bean;

import android.support.annotation.NonNull;
import android.util.Log;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by liangyc
 * Time :2018/3/23
 * Des:
 */
@Entity
public class User implements Cloneable{
    @Id(autoincrement = true)
    private Long id;
    private String name;
    @Transient
    private int sex;
    @Generated(hash = 212410614)
    public User(Long id, String name, @NonNull String likes) {
        this.id = id;
        this.name = name;
        this.likes = likes;
    }
    @NonNull
    private String likes;

    @Generated(hash = 586692638)
    public User() {
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getLikes() {
        return this.likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", likes='" + likes + '\'' +
                '}';
    }
}
