package com.lyc.indonesia.animationdemo.basemotionanimtion

import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import com.lyc.indonesia.animationdemo.R
import kotlinx.android.synthetic.main.activity_string_text_spring.*

class StringTextSpringActivity : AppCompatActivity() {
    private val mStringText="Hello World!"

    private var isShow=false

    private val height by lazy { getHeiget() }

    private val showAnims = mutableListOf<SpringAnimation>()
    private val hideAnims = mutableListOf<SpringAnimation>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_string_text_spring)
        initView()
        initListener()
    }

    private fun initView() {

        mStringText.forEach {
            val childView = TextView(this@StringTextSpringActivity)
            childView.textSize = 36f
            childView.text = it.toString()
            ll_container.addView(childView)
            childView.translationY = height.toFloat()


            val childViewShowAnim = SpringAnimation(childView, SpringAnimation.TRANSLATION_Y, 0f)
            showAnims.add(childViewShowAnim)
            childViewShowAnim.spring.stiffness = SpringForce.STIFFNESS_VERY_LOW
            childViewShowAnim.spring.dampingRatio = SpringForce.DAMPING_RATIO_MEDIUM_BOUNCY


            val childViewHideAnim = SpringAnimation(childView, SpringAnimation.TRANSLATION_Y, height.toFloat())
            hideAnims.add(childViewHideAnim)
            childViewShowAnim.spring.stiffness = SpringForce.STIFFNESS_MEDIUM
            childViewShowAnim.spring.dampingRatio = SpringForce.DAMPING_RATIO_LOW_BOUNCY
        }
    }

    private fun initListener() {
        btn_start.setOnClickListener {
            if (isShow) setHideAnim() else setShowAnim()
            isShow = !isShow
        }
    }

    private fun setShowAnim() {
        showAnims.forEachIndexed { index, anim ->
            ll_container.postDelayed({
                anim.start()
            }, index * 80L)
        }
    }

    private fun setHideAnim() {
        for (i in hideAnims.size - 1 downTo 0) {
            ll_container.postDelayed({
                hideAnims[i].start()
            }, i * 60L)
        }
    }


    private fun getHeiget(): Int {
        val wm = this.getSystemService(Context.WINDOW_SERVICE)
        val p = Point()
        if (wm is WindowManager) {
            wm.defaultDisplay.getSize(p)
        }
        return p.y
    }

}
