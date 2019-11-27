package com.lyc.indonesia.animationdemo.showorhide

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lyc.indonesia.animationdemo.R
import kotlinx.android.synthetic.main.content_show_or_hide_demo.*

class ShowOrHideDemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_show_or_hide_demo)


        btn_crossfade_animation.setOnClickListener {
            startActivity(Intent(this, CrossfadeActivity::class.java))
        }

        btn_circular_animation.setOnClickListener {
            startActivity(Intent(this, CircleRevealActivity::class.java))
        }

        btn_card_flip_animation.setOnClickListener {
            startActivity(Intent(this, CardFlipActivity::class.java))
        }
    }

}
