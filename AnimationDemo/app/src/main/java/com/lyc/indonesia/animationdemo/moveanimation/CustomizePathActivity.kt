package com.lyc.indonesia.animationdemo.moveanimation

import android.animation.ObjectAnimator
import android.graphics.Path
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.lyc.indonesia.animationdemo.R
import kotlinx.android.synthetic.main.activity_line_translate_animation.*

class CustomizePathActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customize_path)
        btn_move.setOnClickListener {
            if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP){
                val path= Path().apply {
                    moveTo(100f, 100f)
                    quadTo(1000f, 300f, 300f, 700f)
                }
                ObjectAnimator.ofFloat(iv_image, View.X, View.Y, path).apply {
                    start()
                }
            }
        }
    }
}
