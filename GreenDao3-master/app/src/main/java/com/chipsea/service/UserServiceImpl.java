package com.chipsea.service;

import android.content.Context;

import com.chipsea.greendaolib.bean.User;
import com.chipsea.greendaolib.dao.DbUtils;
import com.chipsea.greendaolib.dao.UserDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

import static com.chipsea.greendaolib.dao.DbUtils.DB_NAME;

/**
 * Created by liangyc
 * Time :2018/3/29
 * Des:
 */

public class UserServiceImpl implements IUserService {
    UserDao userDao;

    public  UserServiceImpl(Context context){
        userDao= DbUtils.provideDaoSession(DbUtils.proviceSQLiteDatabase(context,DB_NAME)).getUserDao();
    }

    @Override
    public void insert(User user) {
        userDao.insert(user);
    }

    @Override
    public void delete(User user) {
        userDao.delete(user);
    }


    public List<User> query(){
        return userDao.queryBuilder().list();
    }

    public void update(User user){
        userDao.update(user);
    }

    /**
     * 根据ID 更新 不支持更新Key
     * @param userId
     */
    public void updateById(Long userId,User user){
        User userT=userDao.load(userId);
        user.setName(userT.getName());
        userDao.update(userT);
    }

    /**
     * 更新所有相同name的数据
     * @param name
     */
    public void updateByName(String name,User user) throws CloneNotSupportedException {
        List<User> userList=userDao.queryBuilder().where(UserDao.Properties.Name.eq(name)).list();
        List<User> result=new ArrayList<>(userList.size());
        for (User u:userList){
            User user1= (User) user.clone();
            user1.setId(u.getId());
            result.add(user1);
        }
        userDao.updateInTx(result);
    }

    /**
     * 根据 Likes 和 name关键词查找
     *  where 条件关键词 用于 并 的情况
     *  orderAsc 升序排序
     * @param name
     * @param likes
     * @return
     */
    public List<User> findLikesAndName(String name, String likes){
       QueryBuilder<User> userQueryBuilder=userDao.queryBuilder().where(UserDao.Properties.Name.eq(name),UserDao.Properties.Likes.eq(likes)).orderAsc(UserDao.Properties.Id);
      return userQueryBuilder.list();
    };


    /**
     *  根据 Likes 或 name关键词查找
     *  whereOr 条件关键词 用于 或 的情况
     *  orderDesc 降序排序
     * @param name
     * @param likes
     * @return
     */
    public List<User> findLikesOrName(String name, String likes){
        QueryBuilder<User> userQueryBuilder=userDao.queryBuilder().whereOr(UserDao.Properties.Name.eq(name),UserDao.Properties.Likes.eq(likes)).orderDesc(UserDao.Properties.Id);
        return userQueryBuilder.list();
    };

}
