package com.lyc.indonesia.animationdemo

import android.animation.*
import android.graphics.PointF
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.LinearInterpolator
import kotlinx.android.synthetic.main.activity_property_animation_java.*

class PropertyAnimationJavaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_property_animation_java)

        //  ValueAnimator动画
        btn_valueanimation.setOnClickListener {
            //            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                ValueAnimator.ofArgb(0xB0E0E6F,0x66CD00).apply {
//                    duration=4000
//                    addUpdateListener {updateAnimation->
//                        Log.d("test",(updateAnimation.animatedValue as Int).toString())
//                        iv_image.setBackgroundColor(updateAnimation.animatedValue as Int)
//                    }
//                    start()
//                }
//            }


            ValueAnimator.ofFloat(0f, 100f).apply {
                //设置动画持续时间
                duration = 4000
                repeatCount = 10
                //设置重复次数
                repeatMode = ValueAnimator.REVERSE
                //设置加速插值器
                interpolator = AccelerateInterpolator()
                addUpdateListener { updateAnimation ->
                    //改变目标的属性值
                    iv_image.translationX = (updateAnimation.animatedValue as Float)
                }
                addListener(object:AnimatorListenerAdapter(){
                    override fun onAnimationEnd(animation: Animator?) {
                        super.onAnimationEnd(animation)
                        iv_image.visibility=View.GONE
                    }
                })
                start()
            }
        }


        // ObjectAnimator 动画
        btn_obejctanimation.setOnClickListener {
            ObjectAnimator.ofFloat(iv_image, "translationX", 0f, 100f).apply {
                duration = 4000
                repeatCount = 10
                //设置重复次数
                repeatMode = ValueAnimator.REVERSE
                //设置加速插值器
                interpolator = AccelerateInterpolator()
                start()
            }
        }


        // 组合动画
        btn_animationSet.setOnClickListener {
            val translateAnimation = ObjectAnimator.ofFloat(iv_image, "translationX", 0f, 100f).apply {
                duration = 1000
            }
            val translateAnimation1 = ObjectAnimator.ofFloat(iv_image, "translationX", 0f, 200f).apply {
                duration = 1000
            }
            val translateAnimation2 = ObjectAnimator.ofFloat(iv_image, "translationX", 0f, 300f).apply {
                duration = 1000
            }
            val scaleXAnimator = ObjectAnimator.ofFloat(iv_image, "scaleX", 0f, 1f).apply {
                duration = 1000
            }
            // 创建AnimationSet
            val boundAnimbouncer = AnimatorSet().apply {
                // translateAnimation在scaleXAnimator之前执行
                play(translateAnimation).before(scaleXAnimator)
                // translateAnimation在translateAnimation1之后执行
                play(translateAnimation).after(translateAnimation1)
                // translateAnimation和translateAnimation1一起执行
                play(translateAnimation).with(translateAnimation2)
//                start()
            }
            // 创建透明度改变动画
            val faceAnim = ObjectAnimator.ofFloat(iv_image, "alpha", 0f, 1f).apply {
                duration = 5000
            }

            AnimatorSet().apply {
                //faceAnim在boundAnimbouncer之前执行
                play(faceAnim).before(boundAnimbouncer)
                start()
            }
        }


        btn_parabola.setOnClickListener {
            ValueAnimator().apply {
                //设置动画持续时间
                duration = 4000
                //设置目标值
                setObjectValues(PointF(0f,0f))
                //设置自定义的 TypeEvalutor
                setEvaluator(PointFEvaluator())
                //设置加速插值器
                interpolator = LinearInterpolator()
                addUpdateListener { updateAnimation ->
                    //改变目标的属性值
                    val pointF=updateAnimation.animatedValue as PointF
                    iv_image.x=pointF.x
                    iv_image.y=pointF.y

                }
                start()
            }
        }
    }



}
