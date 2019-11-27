package com.example.backgroundworkdemo.service

import androidx.appcompat.app.AppCompatActivity
import com.example.backgroundworkdemo.R
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import android.view.View
import android.os.Messenger
import android.util.Log


class MessengerActivity : AppCompatActivity() {

    var mService:Messenger?=null

    var mBound=false

    /**
     * 实现与服务端链接的对象
     */
    private val mConnection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            /**
             * 通过服务端传递的IBinder对象,创建相应的Messenger
             * 通过该Messenger对象与服务端进行交互
             */
            mService = Messenger(service)
            mBound = true
        }

        override fun onServiceDisconnected(className: ComponentName) {
            // This is called when the connection with the service has been
            // unexpectedly disconnected -- that is, its process crashed.
            mService = null
            mBound = false
        }
    }

    fun sayHello(v: View) {
        if (!mBound) return
        // 创建与服务交互的消息实体Message
        val msg = Message.obtain(null, MessengerService.MSG_SAY_HELLO, 0, 0)
        msg.replyTo=mRecevierReplyMsg
        try {
            //发送消息
            mService?.send(msg)
        } catch (e: RemoteException) {
            e.printStackTrace()
        }

    }

    fun bindService(v:View?){
        Intent(MessengerActivity@this,MessengerService::class.java).apply {
            bindService(this,mConnection,Context.BIND_AUTO_CREATE)
        }
    }

    fun unBindService(v:View?){
        if (mBound){
            unbindService(mConnection)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_messenger)
    }

    /**
     * 用于接收服务器返回的信息
     */
    private val mRecevierReplyMsg = Messenger(ReceiverReplyMsgHandler())


    private class ReceiverReplyMsgHandler : Handler() {

        override fun handleMessage(msg: Message) {
            when (msg.what) {
                //接收服务端回复
                MessengerService.MSG_SAY_HELLO -> Log.i(
                    TAG,
                    "receiver message from service:" + msg.data.getString("reply")
                )
                else -> super.handleMessage(msg)
            }
        }

        companion object {
            private val TAG = "zj"
        }
    }
}
