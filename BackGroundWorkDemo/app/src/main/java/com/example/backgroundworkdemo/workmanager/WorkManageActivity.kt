package com.example.backgroundworkdemo.workmanager

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.view.View
import com.example.backgroundworkdemo.R

class WorkManageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_manage)
    }

    fun singeTask(view: View) {
        DemoWorker.schedule(1000,"asdbasjd",false)
    }

    fun periodTask(view: View) {
        DemoWorker.periodSchedule(1002,"asdbasjd",false)
    }
    fun mulTask(view: View) {
        DemoWorker.mulTask(1003,"asdbasjd",false)
    }
}
