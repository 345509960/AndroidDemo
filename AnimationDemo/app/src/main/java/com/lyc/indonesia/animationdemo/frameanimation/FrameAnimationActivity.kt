package com.lyc.indonesia.animationdemo.frameanimation

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.lyc.indonesia.animationdemo.R
import kotlinx.android.synthetic.main.activity_frame_animation.*

class FrameAnimationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frame_animation)
        btn_java.setOnClickListener {
            startActivity(Intent(this, FrameAnimationJavaActivity::class.java))
        }
        btn_xml.setOnClickListener {
            startActivity(Intent(this, FrameAnimationXmlActivity::class.java))
        }
    }
}
