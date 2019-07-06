package com.lyc.love.dagger2demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.lyc.love.dagger2demo.bean.Goods;
import com.lyc.love.dagger2demo.dagger2.component.DaggerTwoComponent;
import com.lyc.love.dagger2demo.dagger2.module.ObjectModule;

import javax.inject.Inject;

public class TwoActivity extends AppCompatActivity {

    @Inject
    Goods mGoods;

    private String TAG="TwoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        DaggerTwoComponent
                .builder()
                .appComponent(MyApplication.newInsatnce().getAppComponent())
                .build()
                .inject(this);

        Log.d(TAG,mGoods.toString());

        findViewById(R.id.bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TwoActivity.this,ThirdActivity.class));
            }
        });

    }
}
