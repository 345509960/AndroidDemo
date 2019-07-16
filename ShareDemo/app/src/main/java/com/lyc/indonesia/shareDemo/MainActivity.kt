package com.lyc.indonesia.shareDemo

import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.liulishuo.filedownloader.BaseDownloadTask
import com.liulishuo.filedownloader.FileDownloadListener
import com.liulishuo.filedownloader.FileDownloader
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FileDownloader.setup(this)
        btn_share_whatsapp.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                share()
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    0x123
                )
            }
        }

        btn_share.setOnClickListener {
            ShareUtils(this).shareUrl("xxx","123","2222")
        }


    }

    private fun share() {
        //                ShareUtils(this).shareUrl("7777","8888","9999")
//                ShareUtils(this).shareImageAndText("666", "test", "")
        val url="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1562836968829&di=69592f694c1609a1780266befb7fed1e&imgtype=0&src=http%3A%2F%2Fpic.k73.com%2Fup%2Fsoft%2F2016%2F0102%2F092635_44907394.jpg"
        val FileLoad = "cxstatus/"
        val savePath=Environment.getExternalStorageDirectory().absolutePath + File.separator+FileLoad+"tempimg.jpg"
        FileDownloader.getImpl().create(url)
            .setPath(savePath)
            .setForceReDownload(true)
            .setListener(object : FileDownloadListener() {
                override fun warn(task: BaseDownloadTask?) {

                }

                override fun completed(task: BaseDownloadTask?) {
                   Log.d("test",""+savePath)
                   ShareUtils(this@MainActivity).shareMsg("Share Demo","运营活动","我想要分享一些东西。https://www.hao123.com/?1562762562",savePath)
                }

                override fun pending(task: BaseDownloadTask?, soFarBytes: Int, totalBytes: Int) {
                }

                override fun error(task: BaseDownloadTask?, e: Throwable?) {
                }

                override fun progress(task: BaseDownloadTask?, soFarBytes: Int, totalBytes: Int) {
                }

                override fun paused(task: BaseDownloadTask?, soFarBytes: Int, totalBytes: Int) {

                }

            }).start()
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 0x123) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                share()
            }
        }
    }
}
