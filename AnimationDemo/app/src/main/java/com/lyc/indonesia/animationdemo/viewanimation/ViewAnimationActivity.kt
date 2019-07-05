package com.lyc.indonesia.animationdemo.viewanimation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lyc.indonesia.animationdemo.R
import kotlinx.android.synthetic.main.activity_select_animation.*

class ViewAnimationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_animation)


        btn_java.setOnClickListener {
            startActivity(Intent(this, ViewJavaAnimationActivity::class.java))
        }
        btn_xml.setOnClickListener {
            startActivity(Intent(this, ViewXmlAnimationActivity::class.java))
        }
    }
}
