package com.chipsea.greendaolib.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by liangyc
 * Time :2018/3/23
 * Des:
 */

public class DbUtils {

    public static final String DB_NAME="test.db";

    private static volatile SQLiteDatabase sSQLiteDatabase;

    public static SQLiteDatabase proviceSQLiteDatabase(Context context,String dbNama){
        if (sSQLiteDatabase==null){
            synchronized (DaoMaster.DevOpenHelper.class){
                if (sSQLiteDatabase==null){
                    DaoMaster.DevOpenHelper devOpenHelper=new Helper(context,dbNama,null);
                    sSQLiteDatabase=devOpenHelper.getWritableDatabase();
                }
            }
        }
        return sSQLiteDatabase;
    }


    public static DaoSession provideDaoSession(SQLiteDatabase sqLiteDatabase){
        DaoMaster daoMaster=new DaoMaster(sqLiteDatabase);
        return daoMaster.newSession();
    }



}
