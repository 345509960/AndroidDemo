package com.lyc.indonesia.shareDemo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.List;
import java.util.function.Consumer;

public class ShareUtils {

    private Context context;

    public ShareUtils(Context context) {
        this.context = context;
    }

    public static final String WEIXIN_PACKAGE_NAME = "";
    public static final String QQ_PACKAGE_NAME = "";

    private static final String WHATSAPP_PACKGE="com.whatsapp";


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



    private static boolean stringCheck(String str) {
        return null != str && !TextUtils.isEmpty(str);
    }




    /**
     * 分享Feedbook相关功能
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
    public void shareFb(String activityTitle, String msgTitle, String msgText,
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


    public void showCustomizeShare(final String title,final String shareText, final String shareSubject, final String shareLink, final Uri imgUri){
        final BottomSheetDialog sheetDialog = new BottomSheetDialog(context);
        RecyclerView recyclerView = new RecyclerView(context);
        recyclerView.setLayoutManager(new GridLayoutManager(context,4));
        final List<ResolveInfo> arrayList = getShareActivities(context);
        printlnShareList(arrayList) ;
        ShareAdapter bottomProductsAdapter = new ShareAdapter(context, arrayList);
        recyclerView.setAdapter(bottomProductsAdapter);
        bottomProductsAdapter.setmOnClickListener(new ShareAdapter.OnClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                ResolveInfo info = arrayList.get(position);
                if (info != null) {
                    intent.setClassName(info.activityInfo.packageName, info.activityInfo.name);
                    intent.setType("text/plain");
                    if (info.activityInfo.packageName.startsWith(WHATSAPP_PACKGE)){

                        if (imgUri != null) {
                            intent.setType("image/*");
                            intent.putExtra(Intent.EXTRA_STREAM, imgUri);
                        }

                    }
                    intent.putExtra(Intent.EXTRA_SUBJECT, shareSubject);
                    intent.putExtra(Intent.EXTRA_TEXT, shareText + "#" + shareLink);
                    context.startActivity(intent);

                }
                sheetDialog.dismiss();
            }
        });

        sheetDialog.setContentView(recyclerView);
        View view1 = sheetDialog.getDelegate().findViewById(android.support.design.R.id.design_bottom_sheet);
        final BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(view1);
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                    sheetDialog.dismiss();
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
        sheetDialog.show();
    }

    private static void printlnShareList(List<ResolveInfo> shareList){
       if (shareList==null){
           return;
       }
       for (ResolveInfo info:shareList){
           Log.d("test", info.activityInfo.packageName);
       }
    }

    private static List<ResolveInfo> getShareActivities(Context context) {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        PackageManager pm = context.getPackageManager();
        return pm.queryIntentActivities(sharingIntent, 0);
    }

}
