package com.lyc.indonesia.animationdemo

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.lyc.indonesia.animationdemo.frameanimation.FrameAnimationJavaActivity
import com.lyc.indonesia.animationdemo.frameanimation.FrameAnimationXmlActivity
import com.lyc.indonesia.animationdemo.viewanimation.ViewAnimationActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_view.setOnClickListener {
            startActivity(Intent(this, ViewAnimationActivity::class.java))
        }



        btn_animation_frame_java.setOnClickListener {
            startActivity(Intent(this, FrameAnimationJavaActivity::class.java))
        }

        btn_animation_frame_xml.setOnClickListener {
            startActivity(Intent(this, FrameAnimationXmlActivity::class.java))
        }


        btn_property_animtion.setOnClickListener {
            startActivity(Intent(this,PropertyAnimationJavaActivity::class.java))
        }
    }
}
