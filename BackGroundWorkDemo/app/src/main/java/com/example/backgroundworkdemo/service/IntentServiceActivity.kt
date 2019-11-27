package com.example.backgroundworkdemo.service

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.backgroundworkdemo.R

class IntentServiceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_service)
    }

    fun startTask(v:View?){
        startService(Intent(ServiceActivity@ this, MyIntentService::class.java))
    }

    fun startQueueTask(v:View?){
        startService(Intent(ServiceActivity@ this, MyIntentService::class.java))
        startService(Intent(ServiceActivity@ this, MyIntentService::class.java))
        startService(Intent(ServiceActivity@ this, MyIntentService::class.java))
        startService(Intent(ServiceActivity@ this, MyIntentService::class.java))
        startService(Intent(ServiceActivity@ this, MyIntentService::class.java))
    }
}
