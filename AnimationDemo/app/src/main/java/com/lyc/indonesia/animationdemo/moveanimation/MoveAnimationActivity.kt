package com.lyc.indonesia.animationdemo.moveanimation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lyc.indonesia.animationdemo.R
import kotlinx.android.synthetic.main.activity_move_animation.*

class MoveAnimationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_move_animation)

        btn_line_move.setOnClickListener {
            startActivity(Intent(this@MoveAnimationActivity,LineTranslateAnimationActivity::class.java))
        }

        btn_crul_move.setOnClickListener {
            startActivity(Intent(this@MoveAnimationActivity,CurvedMotionActivity::class.java))
        }

        btn_crul_move_customzilier.setOnClickListener {
            startActivity(Intent(this@MoveAnimationActivity,CustomizePathActivity::class.java))
        }
    }
}
