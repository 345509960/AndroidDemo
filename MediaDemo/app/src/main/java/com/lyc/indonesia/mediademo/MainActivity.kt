package com.lyc.indonesia.mediademo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.lyc.indonesia.mediademo.photo.CustomTakePictureActivity
import com.lyc.indonesia.mediademo.photo.SystemTakeAPictureActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //系统拍照功能
        btn_camera.setOnClickListener {
            startActivity(Intent(this@MainActivity,SystemTakeAPictureActivity::class.java))
        }

        btn_custom_camera.setOnClickListener {
            startActivity(Intent(this@MainActivity,CustomTakePictureActivity::class.java))
        }
    }
}
