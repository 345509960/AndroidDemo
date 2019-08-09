package com.lyc.indonesia.animationdemo.layoutanimation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lyc.indonesia.animationdemo.R
import kotlinx.android.synthetic.main.activity_layout_animation.*

class LayoutAnimationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout_animation)

        btn_auto_animation.setOnClickListener {
            startActivity(Intent(this@LayoutAnimationActivity,AutoAnimationLayoutUpdateActivity::class.java))
        }
    }
}
