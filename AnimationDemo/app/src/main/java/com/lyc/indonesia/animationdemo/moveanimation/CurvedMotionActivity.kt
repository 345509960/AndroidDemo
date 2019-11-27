package com.lyc.indonesia.animationdemo.moveanimation

import android.animation.ObjectAnimator
import android.graphics.Path
import android.os.Build
import android.os.Bundle
import android.view.animation.PathInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.lyc.indonesia.animationdemo.R
import kotlinx.android.synthetic.main.activity_line_translate_animation.*

class CurvedMotionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_curved_motion)

        btn_move.setOnClickListener {
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
                val path= Path().apply {
                    cubicTo(0.2f, 0f, 0.1f, 1f, 0.5f, 1f)
                    lineTo(1f, 1f)
                }
                val pathInterpolator=PathInterpolator(path)
                ObjectAnimator.ofFloat(iv_image,"translationX",500f).apply {
                    interpolator=pathInterpolator
                    start()
                }
            }
        }
    }
}
