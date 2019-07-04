package com.lyc.indonesia.animationdemo.viewanimation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.lyc.indonesia.animationdemo.R
import kotlinx.android.synthetic.main.activity_view_animation.*

class ViewXmlAnimationActivity : AppCompatActivity() {
    lateinit var mTranslateAnimation: Animation
    lateinit var mScaleAnimation: Animation
    lateinit var mAlphaAnimation: Animation
    lateinit var mRotateAnimation: Animation
    lateinit var mUniAnimation: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_animation)

        mTranslateAnimation = AnimationUtils.loadAnimation(this, R.anim.translate)
        mScaleAnimation = AnimationUtils.loadAnimation(this, R.anim.scale)
        mAlphaAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha)
        mRotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate)
        mUniAnimation = AnimationUtils.loadAnimation(this, R.anim.uni_animation)


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
