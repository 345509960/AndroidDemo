package com.lyc.indonesia.mediademo.photo

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import com.lyc.indonesia.mediademo.R
import kotlinx.android.synthetic.main.activity_custom_take_picture.*
import java.io.File

class CustomTakePictureActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_take_picture)

        btn_open.setOnClickListener {
            Intent(this@CustomTakePictureActivity,TakePictureActivity::class.java).also {
                val file=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                it.putExtra("path",file.absolutePath)
                startActivityForResult(it,0x123)
            }

        }

        btn_caputer.setOnClickListener {
            Intent(this@CustomTakePictureActivity,MediaRecorderActivity::class.java).also {
                val file=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                it.putExtra("path",file.absolutePath)
                startActivityForResult(it,0x124)
            }
        }


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode== Activity.RESULT_OK){
            when(requestCode){
                0x123->{
                    var dataPath=data?.getStringExtra("data")
                    if (!TextUtils.isEmpty(dataPath)){
                        iv_image.setImageBitmap(BitmapFactory.decodeFile(dataPath))
                        galleryAddPic(dataPath)
                    }
                }
            }
        }
    }

    private fun  galleryAddPic(path:String?){
        Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE).also { mediaIntent->
            val f= File(path)
            Log.d("test","更新图库 ${f.absolutePath}")
            mediaIntent.data= Uri.fromFile(f)
            sendBroadcast(mediaIntent)
        }
    }
}
