package com.lyc.indonesia.animationdemo.showorhide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewAnimationUtils
import android.view.animation.DecelerateInterpolator
import com.lyc.indonesia.animationdemo.R
import kotlinx.android.synthetic.main.activity_circle_reveal.*

class CircleRevealActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_circle_reveal)


        iv_image.viewTreeObserver.addOnGlobalLayoutListener {
            val width=iv_image.width
            val height=iv_image.height
            val max=Math.max(width,height)
            val viewAnimation=ViewAnimationUtils.createCircularReveal(iv_image,0,0,0f,max.toFloat())
            viewAnimation.duration=5000
            viewAnimation.interpolator=DecelerateInterpolator()
            viewAnimation.start()
        }

    }
}
