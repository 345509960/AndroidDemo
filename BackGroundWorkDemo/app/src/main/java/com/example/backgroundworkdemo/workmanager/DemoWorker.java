package com.example.backgroundworkdemo.workmanager;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.backgroundworkdemo.Constants;
import com.google.android.gms.common.util.ArrayUtils;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * create by lyc
 * on 2019/12/3
 * 描述 WorkManage用例
 **/
public class DemoWorker  extends Worker {
    private Context mContext;
    private Handler myHandler=new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what==1000){
                Toast.makeText(mContext,"完成一次性任务",Toast.LENGTH_SHORT).show();
            }else if (msg.what==1001){
                Toast.makeText(mContext,"完成周期性任务",Toast.LENGTH_SHORT).show();
            }else if (msg.what==1003){
                Toast.makeText(mContext,"多任务调度",Toast.LENGTH_SHORT).show();
            }
        }
    };

    public DemoWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        mContext=context;
    }

    @NonNull
    @Override
    public Result doWork() {
        Data data = getInputData();
        int requestCode = data.getInt(Constants.ALARM_REQUEST_CODE, -1);
        String strData = data.getString(Constants.ALARM_NOTIFICATION_DATA);
        boolean bNotiClick = data.getBoolean(Constants.ALARM_NOTIFICATION_CLICK, false);
        Context context = getApplicationContext();
        //your job to do
        Log.d("test","requestCode:"+requestCode);
        myHandler.sendEmptyMessage(requestCode);
        //可以再这里调用下一个一次性任务，形成周期循环。
        //现在默认执行成功，有需要再添加retry back-off相关逻辑
        return Result.success();
    }

    /**
     * @param requestCode requestCode
     * @param strData strData
     * @param bNotiClick bNotiClick
     */
    public static void schedule(final int requestCode, String strData, boolean bNotiClick) {
        WorkManager manager = WorkManager.getInstance();
        //下一次任务开始时，取消上一次相关tag类型的任务（避免网络状态不太好反复触发网络切换广播的情况）
        //这里如果queue里面存在100个以上job会crash，所以需要处理一下
        manager.cancelAllWorkByTag(String.valueOf(requestCode));
        Constraints constraints=new Constraints.Builder()
                .setRequiresCharging(true)
                .build();
        OneTimeWorkRequest oneTimeWorkRequest =
                new OneTimeWorkRequest.Builder(DemoWorker.class)
                        .addTag(String.valueOf(requestCode))
                        .setInputData(new Data.Builder()
                                .putInt(Constants.ALARM_REQUEST_CODE, requestCode)
                                .putString(Constants.ALARM_NOTIFICATION_DATA, strData)
                                .putBoolean(Constants.ALARM_NOTIFICATION_CLICK, bNotiClick)
                                .build())
                        .setConstraints(constraints)
                        .build();


        manager.enqueue(oneTimeWorkRequest);
    }

    public static void periodSchedule(final int requestCode, String strData, boolean bNotiClick) {
        WorkManager manager = WorkManager.getInstance();
        //下一次任务开始时，取消上一次相关tag类型的任务（避免网络状态不太好反复触发网络切换广播的情况）
        //这里如果queue里面存在100个以上job会crash，所以需要处理一下
        manager.cancelAllWorkByTag(String.valueOf(requestCode));
        PeriodicWorkRequest periodicWorkRequest =
                new PeriodicWorkRequest.Builder(DemoWorker.class,15, TimeUnit.MINUTES)
                        .addTag(String.valueOf(requestCode))
                        .setInputData(new Data.Builder()
                                .putInt(Constants.ALARM_REQUEST_CODE, requestCode)
                                .putString(Constants.ALARM_NOTIFICATION_DATA, strData)
                                .putBoolean(Constants.ALARM_NOTIFICATION_CLICK, bNotiClick)
                                .build())
                        .build();
        manager.enqueue(periodicWorkRequest);
    }

    public static void mulTask(final int requestCode, String strData, boolean bNotiClick) {
        WorkManager manager = WorkManager.getInstance();
        //下一次任务开始时，取消上一次相关tag类型的任务（避免网络状态不太好反复触发网络切换广播的情况）
        //这里如果queue里面存在100个以上job会crash，所以需要处理一下
        manager.cancelAllWorkByTag(String.valueOf(requestCode));
        Constraints constraints=new Constraints.Builder()
                .setRequiresCharging(true)
                .build();
        OneTimeWorkRequest oneTimeWorkRequest =
                new OneTimeWorkRequest.Builder(DemoWorker.class)
                        .addTag(String.valueOf(requestCode))
                        .setInputData(new Data.Builder()
                                .putInt(Constants.ALARM_REQUEST_CODE, requestCode)
                                .putString(Constants.ALARM_NOTIFICATION_DATA, strData)
                                .putBoolean(Constants.ALARM_NOTIFICATION_CLICK, bNotiClick)
                                .build())
                        .setConstraints(constraints)
                        .build();

        OneTimeWorkRequest oneTimeWorkRequestB =
                new OneTimeWorkRequest.Builder(DemoWorker.class)
                        .addTag(String.valueOf(requestCode))
                        .setInputData(new Data.Builder()
                                .putInt(Constants.ALARM_REQUEST_CODE, 1006)
                                .putString(Constants.ALARM_NOTIFICATION_DATA, strData)
                                .putBoolean(Constants.ALARM_NOTIFICATION_CLICK, bNotiClick)
                                .build())
                        .setConstraints(constraints)
                        .build();


        OneTimeWorkRequest oneTimeWorkRequestC =
                new OneTimeWorkRequest.Builder(DemoWorker.class)
                        .addTag(String.valueOf(requestCode))
                        .setInputData(new Data.Builder()
                                .putInt(Constants.ALARM_REQUEST_CODE, 1007)
                                .putString(Constants.ALARM_NOTIFICATION_DATA, strData)
                                .putBoolean(Constants.ALARM_NOTIFICATION_CLICK, bNotiClick)
                                .build())
                        .setConstraints(constraints)
                        .build();

        manager
                .beginWith(ArrayUtils.toArrayList(new OneTimeWorkRequest[]{oneTimeWorkRequest,oneTimeWorkRequestB}))
                .then(oneTimeWorkRequestC)
                .enqueue();
    }
}
