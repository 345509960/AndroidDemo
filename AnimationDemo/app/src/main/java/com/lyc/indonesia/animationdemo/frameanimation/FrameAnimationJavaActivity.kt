package com.lyc.indonesia.animationdemo.frameanimation

import android.graphics.drawable.AnimationDrawable
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.lyc.indonesia.animationdemo.R
import kotlinx.android.synthetic.main.activity_frame_animation_java.*

class FrameAnimationJavaActivity : AppCompatActivity() {
    var isStart=false
    lateinit var mAnimationDrawable: AnimationDrawable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frame_animation_java)

        mAnimationDrawable=AnimationDrawable()
        var index=0
        while (index<25){
            //根据字符串名称 固定defType获取到资源Id
            val id=resources.getIdentifier("img${index}","drawable",packageName)
            mAnimationDrawable.addFrame(ActivityCompat.getDrawable(this,id)!!,50)
            index++
        }
        //设置仅仅播放一次
        mAnimationDrawable.isOneShot=true
        // 设置背景图
        iv_image.background=mAnimationDrawable

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
