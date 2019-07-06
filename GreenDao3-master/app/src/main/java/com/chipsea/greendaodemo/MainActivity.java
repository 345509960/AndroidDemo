package com.chipsea.greendaodemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chipsea.greendaolib.bean.User;
import com.chipsea.service.UserServiceImpl;

import org.greenrobot.greendao.query.QueryBuilder;

public class MainActivity extends AppCompatActivity {

    private LinearLayout mLlTop;
    private Button mBtInsert;
    private Button mBtUpdate;
    private Button mBtDelete;
    private Button mBtQuery;
    private TextView mTv;

    UserServiceImpl service;

    private void assignViews() {
        service=new UserServiceImpl(this);
        mLlTop = (LinearLayout) findViewById(R.id.ll_top);
        mBtInsert = (Button) findViewById(R.id.bt_insert);
        mBtUpdate = (Button) findViewById(R.id.bt_update);
        mBtDelete = (Button) findViewById(R.id.bt_delete);
        mBtQuery = (Button) findViewById(R.id.bt_query);
        mTv = (TextView) findViewById(R.id.tv);

        mBtInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user=new User();
                user.setName("dfbhj");
                user.setLikes("asdasd");
                service.insert(user);
            }
        });

        mBtUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //默认用key索引 不设置key会崩溃
                User user=new User();
                user.setName("123456");
                try {
                    service.updateByName("84846",user);
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }
        });


        mBtDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        mBtQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queryMsgUi();
            }
        });
    }

    private void queryMsgUi() {
//        mTv.setText(""+service.query());
        mTv.setText(""+service.findLikesOrName("lc","游戏"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTv=findViewById(R.id.tv);

        QueryBuilder.LOG_SQL=true;
        QueryBuilder.LOG_VALUES=true;
        assignViews();



//

    }
}
