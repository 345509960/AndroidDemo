package com.lyc.indonesia.animationdemo.basemotionanimtion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import com.lyc.indonesia.animationdemo.R
import kotlinx.android.synthetic.main.activity_simple_text.*

class CustomSpringForceActivity : AppCompatActivity() {
    private var mSpringAnimation:SpringAnimation?=null
    private var mUpdateListener:DynamicAnimation.OnAnimationUpdateListener?=null
    private var mEndListener:DynamicAnimation.OnAnimationEndListener?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_text)
        // 创建SpringAnimation对象
        mSpringAnimation= SpringAnimation(tv_text,DynamicAnimation.TRANSLATION_Y,100f)
        mSpringAnimation?.setMinValue(100f)
        mSpringAnimation?.setMaxValue(700f)
        // 设置开始值
        mSpringAnimation?.setStartValue(700f)

        // 设置动画的速度
        val pixelPerSecond = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 10f,
        resources.displayMetrics
        )
        mSpringAnimation?.setStartVelocity(pixelPerSecond)

        val springForce=SpringForce()

        // 设置阻尼比
        springForce.dampingRatio=SpringForce.DAMPING_RATIO_HIGH_BOUNCY
        // 设置刚度
        springForce.stiffness=SpringForce.STIFFNESS_MEDIUM
        //设置自定义的SpringForce对象
        mSpringAnimation?.spring=springForce


        btn_start.setOnClickListener {
            //开启动画
            mSpringAnimation?.cancel()
            mSpringAnimation?.start()
        }
        //创建动画更新监听
        mUpdateListener=
            DynamicAnimation.OnAnimationUpdateListener { animation, value, velocity ->
                // animation 添加监听器的动画对象
                // value 当前动画值
                // velocity 当前动画速度
            }
        //设置更新动画值监听器
        mSpringAnimation?.addUpdateListener(mUpdateListener);
        //创建动画结束监听
        mEndListener=DynamicAnimation.OnAnimationEndListener { animation, canceled, value, velocity ->
            // animation 添加监听器的动画对象
            // canceled 动画是否被取消结束的
            // value 当前动画值
            // velocity 当前动画速度
        }
        //设置一个结束的监听器
        mSpringAnimation?.addEndListener(mEndListener)
    }


    override fun onDestroy() {
        mSpringAnimation?.removeUpdateListener(mUpdateListener)
        mSpringAnimation?.removeEndListener(mEndListener)
        super.onDestroy()
    }

}
