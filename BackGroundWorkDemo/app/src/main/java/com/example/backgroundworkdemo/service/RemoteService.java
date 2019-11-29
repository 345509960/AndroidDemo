package com.example.backgroundworkdemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.example.backgroundworkdemo.IRemoteService;

public class RemoteService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // Return the interface
        return binder;
    }

    private final IRemoteService.Stub binder = new IRemoteService.Stub() {

        public void basicTypes(int anInt, long aLong, boolean aBoolean,
                               float aFloat, double aDouble, String aString) {
            // Does nothing
        }
    };
}
