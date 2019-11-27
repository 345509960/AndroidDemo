package com.lyc.indonesia.animationdemo.layoutanimation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.lyc.indonesia.animationdemo.R
import kotlinx.android.synthetic.main.activity_auto_animation_layout_update.*

class AutoAnimationLayoutUpdateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auto_animation_layout_update)


        btn_add_view.setOnClickListener {
            val newView=TextView(this@AutoAnimationLayoutUpdateActivity)
            newView.text="Hello World"
            ll_root_view.addView(newView,0)
        }

        btn_remove_view.setOnClickListener {
           if (ll_root_view.childCount>0){
               ll_root_view.removeViewAt(0)
           }
        }
    }
}
