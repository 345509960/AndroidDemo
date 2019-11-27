package com.lyc.love.dagger2demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.lyc.love.dagger2demo.bean.Person;
import com.lyc.love.dagger2demo.dagger2.component.ThirdCompnent;
import com.lyc.love.dagger2demo.dagger2.component.TwoComponent;
import com.lyc.love.dagger2demo.dagger2.module.ThirdModule;

import javax.inject.Inject;

public class ThirdActivity extends AppCompatActivity {

    @Inject
    Person mPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        ThirdCompnent thirdCompnent=MyApplication.newInsatnce().getAppComponent().addSub(new ThirdModule());
        thirdCompnent.inject(this);

        Log.d("ThirdActivity",mPerson.toString());
    }
}
