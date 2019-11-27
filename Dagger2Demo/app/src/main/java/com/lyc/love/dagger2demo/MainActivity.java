package com.lyc.love.dagger2demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.lyc.love.dagger2demo.bean.Goods;
import com.lyc.love.dagger2demo.bean.Order;
import com.lyc.love.dagger2demo.dagger2.component.DaggerMainActivityComponet;
import com.lyc.love.dagger2demo.dagger2.module.ObjectModule;
import com.lyc.love.dagger2demo.dagger2.scope.Name;

import javax.inject.Inject;


public class MainActivity extends AppCompatActivity {

    private static final String TAG="MainActivity";

    @Inject
    Goods mGoods;

    @Inject
    Goods mGoods2;

    @Inject
    Goods mGoods3;

    @Inject
    Order mOrder;

    @Name("value")
    @Inject
    Order mOrder2;

    @Inject
    Gson mGson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerMainActivityComponet
                .builder()
                .appComponent(MyApplication.newInsatnce().getAppComponent())
                .objectModule(new ObjectModule())
                .build()
                .inject(this);

        Log.d(TAG,mGoods.toString());
        Log.d(TAG,mGoods2.toString());
        Log.d(TAG,mGoods3.toString());
        Goods goods=mOrder.getmGoods();
        Log.d(TAG,mOrder.toString());
        Log.d(TAG,goods.toString());
        Log.d(TAG,mGson.toString());
        Log.d(TAG,mOrder2.getName());


        findViewById(R.id.bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,TwoActivity.class));
            }
        });
    }




}
