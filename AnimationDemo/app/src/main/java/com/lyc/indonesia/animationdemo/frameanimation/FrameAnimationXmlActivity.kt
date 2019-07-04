package com.lyc.indonesia.animationdemo.frameanimation

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.lyc.indonesia.animationdemo.R
import kotlinx.android.synthetic.main.activity_frame_animation_java.*

class FrameAnimationXmlActivity : AppCompatActivity() {
    lateinit var mAnimationDrawable: AnimationDrawable
    var isStart=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frame_animation_java)

        iv_image.setBackgroundResource(R.drawable.animationlist)
        mAnimationDrawable=iv_image.background as AnimationDrawable

        btn_start.setOnClickListener {
            if (isStart){
                isStart=false
                btn_start.text= "开启动画"
                mAnimationDrawable.stop()
            }else{
                isStart=true
                btn_start.text= "停止动画"
                mAnimationDrawable.start()
            }
        }


    }
}
