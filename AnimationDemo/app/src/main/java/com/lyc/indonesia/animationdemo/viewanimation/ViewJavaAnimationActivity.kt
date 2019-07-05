package com.lyc.indonesia.animationdemo.viewanimation

import android.os.Bundle
import android.util.Log
import android.view.animation.*
import androidx.appcompat.app.AppCompatActivity
import com.lyc.indonesia.animationdemo.R
import kotlinx.android.synthetic.main.activity_view_animation.*

class ViewJavaAnimationActivity : AppCompatActivity() {
    lateinit var mTranslateAnimation: Animation
    lateinit var mScaleAnimation: Animation
    lateinit var mAlphaAnimation: Animation
    lateinit var mRotateAnimation: Animation
    lateinit var mUniAnimation: AnimationSet

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_animation)

        mTranslateAnimation = TranslateAnimation(0f,500f,0f,500f)
        mTranslateAnimation.duration=3000
        mTranslateAnimation.repeatCount=Animation.INFINITE
        mTranslateAnimation.repeatMode=Animation.REVERSE
        mTranslateAnimation.interpolator = AccelerateInterpolator()

        mScaleAnimation = ScaleAnimation(0.5f,5f,0.5f,5f)
        mScaleAnimation.duration=3000
        mAlphaAnimation = AlphaAnimation(0.1f,1f)
        mAlphaAnimation.duration=3000
        mRotateAnimation = RotateAnimation(0f,180f)
        mRotateAnimation.duration=3000


        mUniAnimation = AnimationSet(true)
        //动画间隔
        mUniAnimation.duration=3000
        //插值d
        mUniAnimation.interpolator=AccelerateInterpolator()
        //重复模式
        mUniAnimation.repeatMode=Animation.REVERSE
        //重复次数
        mUniAnimation.repeatCount=5
        //添加组合动画
        mUniAnimation.addAnimation(mTranslateAnimation)
        mUniAnimation.addAnimation(mScaleAnimation)
        mUniAnimation.addAnimation(mRotateAnimation)
        mUniAnimation.addAnimation(mAlphaAnimation)


        mTranslateAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
                // 当动画重复的时候触发
                Log.d("test","onAnimationRepeat")
            }

            override fun onAnimationStart(animation: Animation?) {
                // 当动画开始的时候触发
                Log.d("test","onAnimationStart")
            }

            override fun onAnimationEnd(animation: Animation?) {
                // 当动画结束的时候触发
                Log.d("test","onAnimationEnd")
            }

        })

        btn_translate.setOnClickListener {
            iv_image.startAnimation(mTranslateAnimation)
        }

        btn_scale.setOnClickListener {
            iv_image.startAnimation(mScaleAnimation)
        }

        btn_alpha.setOnClickListener {
            iv_image.startAnimation(mAlphaAnimation)
        }

        btn_rotate.setOnClickListener {
            iv_image.startAnimation(mRotateAnimation)
        }

        btn_uni.setOnClickListener {
            iv_image.startAnimation(
                mUniAnimation
            )
        }
    }
}
