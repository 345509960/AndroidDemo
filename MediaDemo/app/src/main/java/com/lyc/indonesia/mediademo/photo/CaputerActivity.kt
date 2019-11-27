package com.lyc.indonesia.mediademo.photo

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.util.Log
import com.lyc.indonesia.mediademo.R
import com.lyc.indonesia.mediademo.utils.FileSizeUtil
import com.lyc.indonesia.mediademo.utils.PermissionUtils
import kotlinx.android.synthetic.main.activity_system_take_apicture.*
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.SimpleFormatter
/**
** 作者 梁永聪
** 时间 2019/6/8
 * 描述 分流拍小视频界面
**/
class CaputerActivity : AppCompatActivity() {

    val permissions = arrayOf(PermissionUtils.CAMERA, PermissionUtils.WRITE_EXTERNAL_STORAGE)

    val permissionsVideos = arrayOf(PermissionUtils.CAMERA, PermissionUtils.WRITE_EXTERNAL_STORAGE)

    var currentPhotoPath: String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_system_take_apicture)



        btn_open.setOnClickListener {
            if (PermissionUtils.checkSelfPermission(this, permissions)) {
                //权限已存在，直接打开摄像头
                takePicture()
            } else {
                //未获取到权限，运行时请求获取
                PermissionUtils.requestPermission(this, permissions, REQUST_CODE_PERSSION)
            }
        }


        btn_video.setOnClickListener {
            if (PermissionUtils.checkSelfPermission(this, permissionsVideos)) {
                //权限已存在，直接打开摄像头
                takeVideo()
            } else {
                //未获取到权限，运行时请求获取
                PermissionUtils.requestPermission(this, permissions, REQUEST_VIDEO_CAPTURE)
            }
        }

    }

    fun takePicture() {
        // 简易的获取缩略图
//        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureInetnt ->
//            takePictureInetnt.resolveActivity(packageManager)?.also {
//                startActivityForResult(takePictureInetnt, REQUEST_IMAGE_CAPTURE)
//            }
//        }
        //自定义存储图片的路径
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureInetnt ->
            takePictureInetnt.resolveActivity(packageManager)?.also {
                val photoFile=try {
                    createImageFiles()
                }catch (ex:IOException){
                    null
                }
                Log.d("test",photoFile?.toString())
                photoFile?.also {
                    val photoURI=FileProvider.getUriForFile(this,"com.lyc.indonesia.mediademo",it)
                    takePictureInetnt.putExtra(MediaStore.EXTRA_OUTPUT,photoURI)
                    startActivityForResult(takePictureInetnt,REQUEST_IMAGE_CAPTURE)
                }

            }
        }
    }


    fun takeVideo(){
        Intent(MediaStore.ACTION_VIDEO_CAPTURE).also {taskIntent->
            taskIntent.resolveActivity(packageManager)?.also {
                val photoFile=try {
                    createImageFiles()
                }catch (ex:IOException){
                    null
                }
                Log.d("test",photoFile?.toString())
                photoFile?.also {
                    val photoURI=FileProvider.getUriForFile(this,"com.lyc.indonesia.mediademo",it)
                    taskIntent.putExtra(MediaStore.EXTRA_OUTPUT,photoURI)
                    startActivityForResult(taskIntent,REQUEST_VIDEO_CAPTURE)
                }
            }
        }
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode!= Activity.RESULT_OK){
            return
        }

        if (requestCode== REQUEST_IMAGE_CAPTURE){
            // 处理返回的缩略图
//            val thumbnailBitmap=data?.extras?.get("data") as Bitmap
//            iv_image.setImageBitmap(thumbnailBitmap)

            //拍照完成，并保存到currentPhotoPath文件中

            val bitmap=BitmapFactory.decodeFile(currentPhotoPath)
            Log.d("test","文件大小:${FileSizeUtil.getAutoFileOrFilesSize(currentPhotoPath)}")
            iv_image.setImageBitmap(bitmap)
            //裁剪后另存为图片并删除原图

            //更新图库
            galleryAddPic()


        }else if (requestCode== REQUEST_VIDEO_CAPTURE){
            Log.d("test","获取文件路径:${data?.data}")
        }
    }


    private fun  galleryAddPic(){
        Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE).also { mediaIntent->
            val f=File(currentPhotoPath)
            Log.d("test","更新图库 ${f.absolutePath}")
            mediaIntent.data= Uri.fromFile(f)
            sendBroadcast(mediaIntent)
        }
    }


    /**
     * 创建文件
     */
    private fun createImageFiles():File{
        val timeStap=SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storeFile=File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM),"Camera")
        if (!storeFile.exists()){
            storeFile.mkdirs()
        }
        return File.createTempFile("JPEG_${timeStap}_",".jpg",storeFile).apply {
            currentPhotoPath=absolutePath
        }
    }



    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        var isSuccess=true
        var isVideosSuccess=true
        when (requestCode) {
            REQUST_CODE_PERSSION -> {
                //遍历查询是否有未授权成功的权限
                for (result in grantResults){
                    if (result!=PackageManager.PERMISSION_GRANTED){
                        isSuccess=false
                        break
                    }
                }
                if (isSuccess) {
                    // 获取到权限 ,进行相关操作
                    takePicture()
                } else {
                    //没有获取到权限，进行提示
                }
            }
            REQUST_CODE_VIDEOS_PERSIMISSION->{
                //遍历查询是否有未授权成功的权限
                for (result in grantResults){
                    if (result!=PackageManager.PERMISSION_GRANTED){
                        isVideosSuccess=false
                        break
                    }
                }
                if (isVideosSuccess){

                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


    companion object {
        val REQUST_CODE_PERSSION = 0x123
        val REQUEST_IMAGE_CAPTURE = 0x124
        val REQUST_CODE_VIDEOS_PERSIMISSION = 0x125
        val REQUEST_VIDEO_CAPTURE = 0x126
    }
}
