package com.lyc.indonesia.mediademo.utils;

import android.graphics.Bitmap;

import java.io.*;

public class FileUtils {

    public static String saveBitmap2File(Bitmap bitmap,String path){
        File file=new File(path);
        try {
            BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,bos);
            bos.flush();
            bos.close();
            return file.getAbsolutePath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }
}
