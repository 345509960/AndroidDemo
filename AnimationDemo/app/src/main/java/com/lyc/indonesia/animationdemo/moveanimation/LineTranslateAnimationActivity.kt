package com.lyc.indonesia.animationdemo.moveanimation

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lyc.indonesia.animationdemo.R
import kotlinx.android.synthetic.main.activity_line_translate_animation.*

class LineTranslateAnimationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_line_translate_animation)

        btn_move.setOnClickListener {
            ObjectAnimator.ofFloat(iv_image,"translationX",500f).apply {
                duration=2000
                start()
            }
        }
    }
}
