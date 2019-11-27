package com.example.backgroundworkdemo.service

import android.app.IntentService
import android.content.Intent
import android.content.Context
import android.util.Log


class MyIntentService : IntentService("MyIntentService") {
    var count=1
    companion object{
        val TAG=MyIntentService.javaClass.name
    }

    override fun onHandleIntent(intent: Intent?) {
        Thread.sleep(2000)
        count++
        Log.d(TAG, "onHandleIntent:$count")
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy:$count")
        super.onDestroy()
    }
}
