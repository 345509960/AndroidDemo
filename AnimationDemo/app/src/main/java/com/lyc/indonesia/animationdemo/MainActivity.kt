package com.lyc.indonesia.animationdemo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lyc.indonesia.animationdemo.basemotionanimtion.BaseMotionActivity
import com.lyc.indonesia.animationdemo.frameanimation.FrameAnimationJavaActivity
import com.lyc.indonesia.animationdemo.frameanimation.FrameAnimationXmlActivity
import com.lyc.indonesia.animationdemo.layoutanimation.LayoutAnimationActivity
import com.lyc.indonesia.animationdemo.moveanimation.MoveAnimationActivity
import com.lyc.indonesia.animationdemo.showorhide.ShowOrHideDemoActivity
import com.lyc.indonesia.animationdemo.viewanimation.ViewAnimationActivity
import com.lyc.indonesia.animationdemo.zoomanimation.ZoomImageViewActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_view.setOnClickListener {
            startActivity(Intent(this, ViewAnimationActivity::class.java))
        }



        btn_animation_frame_java.setOnClickListener {
            startActivity(Intent(this, FrameAnimationJavaActivity::class.java))
        }

        btn_animation_frame_xml.setOnClickListener {
            startActivity(Intent(this, FrameAnimationXmlActivity::class.java))
        }


        btn_property_animtion.setOnClickListener {
            startActivity(Intent(this,PropertyAnimationJavaActivity::class.java))
        }

        btn_moton_base.setOnClickListener {
            startActivity(Intent(this, BaseMotionActivity::class.java))
        }

        bt_show_hide.setOnClickListener {
            startActivity(Intent(this, ShowOrHideDemoActivity::class.java))
        }

        bt_translation.setOnClickListener {
            startActivity(Intent(this, MoveAnimationActivity::class.java))
        }

        btn_zoom_view.setOnClickListener {
            startActivity(Intent(this, ZoomImageViewActivity::class.java))
        }

        btn_layout_animation.setOnClickListener {
            startActivity(Intent(this, LayoutAnimationActivity::class.java))
        }
    }
}
