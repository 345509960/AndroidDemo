package com.lyc.indonesia.animationdemo.basemotionanimtion

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.lyc.indonesia.animationdemo.R
import kotlinx.android.synthetic.main.activity_base_motion.*

class BaseMotionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_motion)



        btn_spring.setOnClickListener {
            startActivity(Intent(this,SpringActivity::class.java))
        }

    }
}
