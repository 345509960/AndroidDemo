package com.example.backgroundworkdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.backgroundworkdemo.service.IntentServiceActivity
import com.example.backgroundworkdemo.service.JobSchedulerActivity
import com.example.backgroundworkdemo.service.MessengerActivity
import com.example.backgroundworkdemo.service.ServiceActivity
import com.example.backgroundworkdemo.workmanager.WorkManageActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun goService(view: View) {
        Intent(this@MainActivity,ServiceActivity::class.java).apply {
            startActivity(this)
        }
    }

    fun goMessenger(view: View) {
        Intent(this@MainActivity,MessengerActivity::class.java).apply {
            startActivity(this)
        }
    }

    fun goIntentService(view: View) {
        Intent(this@MainActivity,IntentServiceActivity::class.java).apply {
            startActivity(this)
        }
    }

    fun goJobScheduler(view: View) {
        Intent(this@MainActivity,JobSchedulerActivity::class.java).apply {
            startActivity(this)
        }
    }

    fun goWorkManage(view: View) {
        Intent(this@MainActivity,WorkManageActivity::class.java).apply {
            startActivity(this)
        }
    }
}
