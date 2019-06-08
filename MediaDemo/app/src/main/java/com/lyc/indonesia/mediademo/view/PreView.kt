package com.lyc.indonesia.mediademo.view

import android.app.Activity
import android.content.Context
import android.hardware.Camera
import android.util.Log
import android.view.*
import java.io.IOException

class PreView(context:Context,
              val surfaceView:SurfaceView= SurfaceView(context)
):ViewGroup(context),SurfaceHolder.Callback {

    private val TAG = this.javaClass.simpleName
    private var mCallBack: SurfaceHolder.Callback? = null









    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
    }

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {
    }

    override fun surfaceCreated(holder: SurfaceHolder?) {

    }


}