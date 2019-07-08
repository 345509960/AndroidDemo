package com.lyc.indonesia.animationdemo.basemotionanimtion

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lyc.indonesia.animationdemo.R
import kotlinx.android.synthetic.main.activity_spring.*

class SpringActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spring)
        btn_simple.setOnClickListener {
            startActivity(Intent(this,SimpleTextActivity::class.java))
        }
        btn_stringText.setOnClickListener {
            startActivity(Intent(this,StringTextSpringActivity::class.java))
        }
        btn_custom_spring_force.setOnClickListener {
            startActivity(Intent(this,CustomSpringForceActivity::class.java))
        }

    }
}
