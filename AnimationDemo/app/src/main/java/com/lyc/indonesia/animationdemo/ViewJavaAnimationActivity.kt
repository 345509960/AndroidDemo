package com.lyc.indonesia.animationdemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.animation.*
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
        mTranslateAnimation.repeatCount=10
        mTranslateAnimation.repeatMode=Animation.REVERSE
        mTranslateAnimation.interpolator = AccelerateInterpolator()

        mScaleAnimation = ScaleAnimation(0.5f,5f,0.5f,5f)
        mScaleAnimation.duration=3000
        mAlphaAnimation = AlphaAnimation(0.1f,1f)
        mAlphaAnimation.duration=3000
        mRotateAnimation = RotateAnimation(0f,180f)
        mRotateAnimation.duration=3000


        mUniAnimation = AnimationSet(true)
        mUniAnimation.duration=3000
        mUniAnimation.addAnimation(mTranslateAnimation)
        mUniAnimation.addAnimation(mScaleAnimation)
        mUniAnimation.addAnimation(mRotateAnimation)
        mUniAnimation.addAnimation(mAlphaAnimation)


        mTranslateAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationStart(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                iv_image.startAnimation(mScaleAnimation)
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
