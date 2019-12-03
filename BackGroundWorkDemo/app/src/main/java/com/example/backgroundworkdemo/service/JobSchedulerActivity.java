package com.example.backgroundworkdemo.service;

import androidx.appcompat.app.AppCompatActivity;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.example.backgroundworkdemo.R;

public class JobSchedulerActivity extends AppCompatActivity {

    private JobScheduler mJobScheduler;
    private String TAG=JobSchedulerActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_scheduler);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mJobScheduler = (JobScheduler)
                    getSystemService(Context.JOB_SCHEDULER_SERVICE);
            //Builder构造方法接收两个参数，第一个参数是jobId，每个app或者说uid下不同的Job,它的jobId必须是不同的
            //第二个参数是我们自定义的JobService,系统会回调我们自定义的JobService中的onStartJob和onStopJob方法
            JobInfo.Builder builder = new JobInfo.Builder(1,
                    new ComponentName(getPackageName(),
                            JobSchedulerService.class.getName()));

            builder.setMinimumLatency(3000);

            if (mJobScheduler.schedule(builder.build()) == JobScheduler.RESULT_SUCCESS) {
                Log.d(TAG, "Job scheduled successfully");
            } else {
                Log.d(TAG, "Job scheduling failed");
            }

        }
    }
}
