package com.example.backgroundworkdemo.service

import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import com.example.backgroundworkdemo.R

class ServiceActivity : AppCompatActivity() {
    companion object {
        val TAG = ServiceActivity.javaClass.name
    }

    private var connection: ServiceConnection? = null
    private var myService: MyService? = null
    private var isBind = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)
        connection = object : ServiceConnection {
            override fun onServiceDisconnected(name: ComponentName?) {

            }

            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                Log.d(TAG, "绑定成功")
                val binder = service as MyService.LocalBinder
                myService = binder.service
                isBind = true
            }

        }
    }

    fun startService(view: View) {
        startService(Intent(ServiceActivity@ this, MyService::class.java))
    }

    fun bindService(view: View) {
        bindService(
            Intent(ServiceActivity@ this, MyService::class.java),
            connection!!,
            Service.BIND_AUTO_CREATE
        )
    }


    fun unBindService(view: View) {
        if (isBind){
            isBind = false
            unbindService(connection!!)
        }
    }

    fun stopService(view: View) {
        stopService(Intent(ServiceActivity@ this, MyService::class.java))
    }

    override fun onDestroy() {
        connection=null
        super.onDestroy()
    }

    fun callCount(view: View) {
        if (isBind) {
            Log.d(TAG, "count:" + myService?.getCount())
        }
    }

}
