package com.example.androidtoolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

public abstract class ToolBarActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private TextView mTvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContenLayout());
        mToolbar = findViewById(R.id.toolbar);
        if (mToolbar == null) {
            throw new RuntimeException("继承ToolBarActivity必须依赖view_common_toolbar.xml布局");
        }
        setSupportActionBar(mToolbar);
        if (getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        mTvTitle=findViewById(R.id.tv_title);
        mTvTitle.setText(getString(R.string.app_name));
    }

    public abstract int getContenLayout();


    public void setToolBarTitle(String title){
        if (mTvTitle!=null&& !TextUtils.isEmpty(title)){
            mTvTitle.setText(title);
        }
    }

    public void setToolBarColor(int resId){
        if (mTvTitle!=null&&resId!=-1){
            mTvTitle.setTextColor(ActivityCompat.getColor(this,resId));
        }
    }


    public void setToolBarBackgroundColor(int resId){
        if (mToolbar!=null&&resId!=-1){
            mToolbar.setBackgroundColor(ActivityCompat.getColor(this,resId));
        }
    }
}
