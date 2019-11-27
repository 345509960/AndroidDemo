package com.lyc.indonesia.mediademo.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

import java.util.ArrayList;
import java.util.List;

public class PermissionUtils {

    public static final String WRITE_EXTERNAL_STORAGE="android.permission.WRITE_EXTERNAL_STORAGE";

    public static final String READ_EXTERNAL_STORAGE="android.permission.READ_EXTERNAL_STORAGE";

    public static final String CAMERA="android.permission.CAMERA";


    /**
     * 检查权限组
     * @param context 上下文对象
     * @param permission 检测的权限数组
     * @return
     */
    public static boolean checkSelfPermission(Context context,String[] permission){
        //先排除异常情况
        if (context==null){
            return false;
        }
        if (permission.length==0){
            return true;
        }
        List<String> noPermission=new ArrayList<>();
        for (String per : permission){
            if (ActivityCompat.checkSelfPermission(context,per)!= PackageManager.PERMISSION_GRANTED){
                noPermission.add(per);
            }
        }
        return noPermission.size()==0;
    }

    /**
     * 申请运行时权限
     * @param activity
     * @param permissions
     * @param requestCode 请求码
     */
    public static void requestPermission(Activity activity, String[] permissions,int requestCode){
        ActivityCompat.requestPermissions(activity,permissions,requestCode);
    }

}
