package com.lyc.indonesia.shareDemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ShareUtils {

    private Context context;

    public ShareUtils(Context context) {
        this.context = context;
    }

    public static final String WEIXIN_PACKAGE_NAME = "";
    public static final String QQ_PACKAGE_NAME = "";


    /**
     * 分享文字
     *
     * @param packageName
     * @param content
     * @param title
     * @param subject
     */
    public void shareText(String packageName, String className, String content, String title, String subject) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");

        if (stringCheck(className) && stringCheck(packageName)) {
            ComponentName componentName = new ComponentName(packageName, className);
            intent.setComponent(componentName);
        } else if (stringCheck(packageName)) {
            intent.setPackage(packageName);
        }

        intent.putExtra(Intent.EXTRA_TEXT, content);
        if (null != title && !TextUtils.isEmpty(title)) {
            intent.putExtra(Intent.EXTRA_TITLE, title);
        }
        if (null != subject && !TextUtils.isEmpty(subject)) {
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        }
        intent.putExtra(Intent.EXTRA_TITLE, title);
        Intent chooserIntent = Intent.createChooser(intent, "Share：");
        context.startActivity(chooserIntent);
    }

    /**
     * 分享网页
     */
    public void shareUrl(String content, String title, String subject) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");

        intent.putExtra(Intent.EXTRA_TEXT, content);
        if (null != title && !TextUtils.isEmpty(title)) {
            intent.putExtra(Intent.EXTRA_TITLE, title);
        }
        if (null != subject && !TextUtils.isEmpty(subject)) {
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        }
        intent.putExtra(Intent.EXTRA_TITLE, title);
        Intent chooserIntent = Intent.createChooser(intent, "Share：");
        context.startActivity(chooserIntent);
    }

    /**
     * 分享图片
     */
    public void shareImg(String packageName, String className, File file) {
        if (file.exists()) {
            Uri uri = Uri.fromFile(file);
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.setType("image/*");
            if (stringCheck(packageName) && stringCheck(className)) {
                intent.setComponent(new ComponentName(packageName, className));
            } else if (stringCheck(packageName)) {
                intent.setPackage(packageName);
            }
            intent.putExtra(Intent.EXTRA_STREAM, uri);
            Intent chooserIntent = Intent.createChooser(intent, "分享到:");
            context.startActivity(chooserIntent);
        } else {
            Toast.makeText(context, "文件不存在", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 分享音乐
     */
    public void shareAudio(String packageName, String className, File file) {
        if (file.exists()) {
            Uri uri = Uri.fromFile(file);
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.setType("audio/*");
            if (stringCheck(packageName) && stringCheck(className)) {
                intent.setComponent(new ComponentName(packageName, className));
            } else if (stringCheck(packageName)) {
                intent.setPackage(packageName);
            }
            intent.putExtra(Intent.EXTRA_STREAM, uri);
            Intent chooserIntent = Intent.createChooser(intent, "分享到:");
            context.startActivity(chooserIntent);
        } else {
            Toast.makeText(context, "文件不存在", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 分享视频
     */
    public void shareVideo(String packageName, String className, File file) {
        setIntent("video/*", packageName, className, file);
    }

    public void setIntent(String type, String packageName, String className, File file) {
        if (file.exists()) {
            Uri uri = Uri.fromFile(file);
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.setType(type);
            if (stringCheck(packageName) && stringCheck(className)) {
                intent.setComponent(new ComponentName(packageName, className));
            } else if (stringCheck(packageName)) {
                intent.setPackage(packageName);
            }
            intent.putExtra(Intent.EXTRA_STREAM, uri);
            Intent chooserIntent = Intent.createChooser(intent, "分享到:");
            context.startActivity(chooserIntent);
        } else {
            Toast.makeText(context, "文件不存在", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 分享多张图片和文字至朋友圈
     *
     * @param title
     * @param packageName
     * @param className
     * @param file        图片文件
     */
    public void shareImgToWXCircle(String title, String packageName, String className, File file) {
        if (file.exists()) {
            Uri uri = Uri.fromFile(file);
            Intent intent = new Intent();
            ComponentName comp = new ComponentName(packageName, className);
            intent.setComponent(comp);
            intent.setAction(Intent.ACTION_SEND);
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_STREAM, uri);
            intent.putExtra("Kdescription", title);
            context.startActivity(intent);
        } else {
            Toast.makeText(context, "文件不存在", Toast.LENGTH_LONG).show();
        }


    }

    /**
     * 是否安装分享app
     *
     * @param packageName
     */
    public boolean checkInstall(String packageName) {
        try {
            context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(context, "请先安装应用app", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    private static boolean stringCheck(String str) {
        return null != str && !TextUtils.isEmpty(str);
    }




    /**
     * 分享功能
     *

     * @param activityTitle
     *            Activity的名字
     * @param msgTitle
     *            消息标题
     * @param msgText
     *            消息内容
     * @param imgPath
     *            图片路径，不分享图片则传null
     */
    public void shareMsg(String activityTitle, String msgTitle, String msgText,
                         String imgPath) {
        Intent intent = new Intent(Intent.ACTION_SEND);
//        intent.setPackage("com.whatsapp");
        if (imgPath == null || "".equals(imgPath)) {
            // 纯文本
            intent.setType("text/plain");
        } else {
            File f = new File(imgPath);
            if (f.exists() && f.isFile()) {
                intent.setType("image/jpg");
                Uri u =FileProvider7.getUriForFile(context,f);
                intent.putExtra(Intent.EXTRA_STREAM, u);
            }
        }
        intent.putExtra(Intent.EXTRA_SUBJECT, msgTitle);
        intent.putExtra(Intent.EXTRA_TEXT, msgText);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(Intent.createChooser(intent, activityTitle));
    }

}
