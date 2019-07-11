package com.lyc.indonesia.animationdemo.basemotionanimtion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.FlingAnimation
import com.lyc.indonesia.animationdemo.R
import kotlinx.android.synthetic.main.activity_fling_animation.*

class FlingAnimationActivity : AppCompatActivity() {

    lateinit var gestureDetector: GestureDetector
    var maxTranslationX: Int? = null
    var maxTranslationY: Int? = null

    private val gestureListener = object : GestureDetector.SimpleOnGestureListener() {
        override fun onDown(e: MotionEvent?): Boolean {
            return true
        }

        override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
            if (Math.abs(velocityX) > Math.abs(velocityY)) {
                FlingAnimation(iv_view, DynamicAnimation.TRANSLATION_X).apply {
                    setStartVelocity(velocityX)
                    setMinValue(0f)
                    setMaxValue(maxTranslationX!!.toFloat())
                    friction=1.1f
                    start()
                }
            }else{
                FlingAnimation(iv_view,DynamicAnimation.TRANSLATION_Y).apply {
                    setStartVelocity(velocityY)
                    setMinValue(0f)
                    setMaxValue(maxTranslationY!!.toFloat())
                    friction=1.1f
                    start()
                }
            }

            return true
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fling_animation)

        root.viewTreeObserver.addOnGlobalLayoutListener {
            maxTranslationX=root.width-iv_view.width
            maxTranslationY=root.height-iv_view.height
        }

        gestureDetector= GestureDetector(this,gestureListener)
        iv_view.setOnTouchListener { v, event ->
            gestureDetector.onTouchEvent(event)
        }

    }
}
