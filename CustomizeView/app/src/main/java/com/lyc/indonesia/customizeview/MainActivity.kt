package com.lyc.indonesia.customizeview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_hoz_scroll.setOnClickListener {
            Intent(this@MainActivity,ScrollerLayoutDemoActivity::class.java).run {
                startActivity(this)
            }
        }
        btn_ver_scroll.setOnClickListener {
            Intent(this@MainActivity,VerticalScrollerLayoutDemoActivity::class.java).run {
                startActivity(this)
            }
        }
    }
}
