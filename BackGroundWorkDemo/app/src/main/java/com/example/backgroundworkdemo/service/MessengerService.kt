package com.example.backgroundworkdemo.service

import android.app.Service
import android.content.Intent
import android.os.*
import android.util.Log
import com.example.backgroundworkdemo.service.MessengerService.IncomingHandler


class MessengerService : Service() {

    companion object{
        val MSG_SAY_HELLO=1
        val TAG=MessengerService.javaClass.name
    }

    /**
     * 创建Messenger并传入Handler实例对象
     */
    val mMessenger = Messenger(IncomingHandler())


    inner class IncomingHandler : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                MSG_SAY_HELLO -> {
                    Log.i(TAG, "thanks,Service had receiver message from client!")
                    val client=msg.replyTo
                    //获取回复信息的消息实体
                    val replyMsg = Message.obtain(null, MessengerService.MSG_SAY_HELLO)
                    val bundle = Bundle()
                    bundle.putString("reply", "ok~,I had receiver message from you! ")
                    replyMsg.data = bundle
                    try{
                        client.send(replyMsg)
                    }catch (e:RemoteException){
                        e.printStackTrace()
                    }
                }
                else -> super.handleMessage(msg)
            }
        }
    }

    override fun onBind(intent: Intent): IBinder {
        return mMessenger.binder
    }
}
