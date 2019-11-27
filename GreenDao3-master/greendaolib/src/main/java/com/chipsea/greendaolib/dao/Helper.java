package com.chipsea.greendaolib.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.database.Database;

/**
 * Created by liangyc
 * Time :2018/3/29
 * Des:
 */

public class Helper extends DaoMaster.DevOpenHelper {
    public Helper(Context context, String name) {
        super(context, name);
    }

    public Helper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
       if (oldVersion<newVersion){
           MigrationHelper.getInstance().migrate(db,
                   UserDao.class);
       }
    }
}
