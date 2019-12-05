package com.example.backgroundworkdemo.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.backgroundworkdemo.R

class BroadcastActivity : AppCompatActivity() {

    lateinit var intentFilter: IntentFilter

    lateinit var myReceiver: MyReceiver

    companion object{
       var BROADCAST_TAG="com.example.backgroundworkdemo.braoad_test"
        var BROADCAST_TAG_LOCAL="com.example.backgroundworkdemo.braoad_test_LOCAL"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broadcast)
        intentFilter= IntentFilter()
        intentFilter.addAction(BROADCAST_TAG)
        intentFilter.addAction(BROADCAST_TAG_LOCAL)
        myReceiver=MyReceiver()
        registerReceiver(myReceiver,intentFilter)
        LocalBroadcastManager.getInstance(this).registerReceiver(myReceiver,intentFilter)
    }

    inner class MyReceiver:BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action?.equals(BROADCAST_TAG)!!){
                Toast.makeText(context, "检测到广播", Toast.LENGTH_SHORT).show()
            }else if (intent.action?.equals(BROADCAST_TAG_LOCAL)!!){
                Toast.makeText(context, "检测到本地广播", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun sendBroadcast(view: View) {
        sendBroadcast(Intent(BROADCAST_TAG))
    }
    fun sendLocalBroadcast(view: View) {
        LocalBroadcastManager.getInstance(this).sendBroadcast(Intent(BROADCAST_TAG_LOCAL))
    }


    override fun onDestroy() {
        unregisterReceiver(myReceiver)
        LocalBroadcastManager.getInstance(this).unregisterReceiver(myReceiver)
        super.onDestroy()
    }
}
