package com.chipsea.service;

import com.chipsea.greendaolib.bean.User;

/**
 * Created by liangyc
 * Time :2018/3/29
 * Des:
 */

public interface IUserService {
    void insert(User user);
    void delete(User user);
}
