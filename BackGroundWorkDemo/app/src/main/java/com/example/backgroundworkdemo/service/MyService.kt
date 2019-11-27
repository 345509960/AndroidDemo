package com.example.backgroundworkdemo.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.example.backgroundworkdemo.service.MyService.LocalBinder
import android.R.attr.start
import android.util.Log
import com.example.backgroundworkdemo.service.ServiceActivity.Companion.TAG


class MyService : Service() {

    private var thread: Thread? = null

    private val binder = LocalBinder()
    private var count: Int = 0
    private var quit: Boolean = false

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "Service is invoke Created")
        thread = Thread(Runnable {
            // 每间隔一秒count加1 ，直到quit为true。
            while (!quit) {
                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                count++
            }
        })
        thread?.start()

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "Service is invoke onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    /**
     * 公共方法
     * @return
     */
    public fun getCount():Int{
        return count
    }


/**
 * 创建Binder对象，返回给客户端即Activity使用，提供数据交换的接口
 */
inner class LocalBinder : Binder() {
    // 声明一个方法，getService。（提供给客户端调用）
    internal// 返回当前对象LocalService,这样我们就可在客户端端调用Service的公共方法了
    val service: MyService
        get() = this@MyService
}

/**
 * 解除绑定时调用
 * @return
 */
override fun onUnbind(intent: Intent): Boolean {
    Log.i(TAG, "Service is invoke onUnbind")
    return super.onUnbind(intent)
}

override fun onDestroy() {
    Log.i(TAG, "Service is invoke Destroyed")
    this.quit = true
    super.onDestroy()
}


}
