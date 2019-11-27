package com.lyc.love.dagger2demo;

import android.app.Application;

import com.lyc.love.dagger2demo.dagger2.component.AppComponent;
import com.lyc.love.dagger2demo.dagger2.component.DaggerAppComponent;

/**
 * Created by Administrator on 2018/4/6 0006.
 */

public class MyApplication extends Application{

    AppComponent appComponent;

    private static MyApplication sMyApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        sMyApplication=this;
        appComponent = DaggerAppComponent
                .builder()
                .build();
    }


    public static MyApplication newInsatnce(){
        return sMyApplication;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
