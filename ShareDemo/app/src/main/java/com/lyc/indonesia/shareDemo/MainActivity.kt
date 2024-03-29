package com.lyc.indonesia.shareDemo

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.net.wifi.WifiManager
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
import com.facebook.share.widget.ShareDialog
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.share.Sharer
import com.facebook.share.model.ShareLinkContent
import com.facebook.share.model.ShareMediaContent
import com.facebook.share.model.SharePhoto


class MainActivity : AppCompatActivity() {
    // 定义WifiManager对象
    private var mWifiManager: WifiManager? = null

    var callbackManager: CallbackManager? = null
    var shareDialog: ShareDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FileDownloader.setup(this)
        callbackManager = CallbackManager.Factory.create();
        shareDialog = ShareDialog(this);
        // this part is optional
        shareDialog?.registerCallback(callbackManager, object : FacebookCallback<Sharer.Result> {
                override fun onSuccess(result: Sharer.Result?) {
                    Log.d("test","ok")
                }

                override fun onCancel() {
                }

                override fun onError(error: FacebookException?) {
                    Log.e("test",error.toString())
                }

            });
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


        btn_customize.setOnClickListener {
            customizeShare()
        }
        // 取得WifiManager对象
        mWifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager?
        btn_test.setOnClickListener {
            mWifiManager?.startScan()
            // 得到扫描结果
            var mWifiList = mWifiManager?.getScanResults()
            Log.d("test",mWifiList?.size.toString())
        }

        btn_share_facebook.setOnClickListener {

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
                        if (ShareDialog.canShow(ShareLinkContent::class.java)) {
                            val sharePhoto = SharePhoto.Builder()
                                .setImageUrl(FileProvider7.getUriForFile(this@MainActivity,
                                    File(savePath)))
                                .build()
                            val shareContent = ShareMediaContent.Builder()
                                .addMedium(sharePhoto)
                                .setRef("http://agent.fonecyc.com/user/index/shareGoods?id=250")
                                .build()
                            val linkContent = ShareLinkContent.Builder()
                                .setContentUrl(Uri.parse(" http://agent-fonecyc.dxiyue.cn/user/index/shareGoods?id=165"))
                                .build()
                            shareDialog?.show(shareContent)

                        }
                    }

                    override fun pending(task: BaseDownloadTask?, soFarBytes: Int, totalBytes: Int) {
                    }

                    override fun error(task: BaseDownloadTask?, e: Throwable?) {
                        Log.d("test",""+e?.message)
                    }

                    override fun progress(task: BaseDownloadTask?, soFarBytes: Int, totalBytes: Int) {
                        Log.d("test",""+soFarBytes.toString())
                    }

                    override fun paused(task: BaseDownloadTask?, soFarBytes: Int, totalBytes: Int) {

                    }

                }).start()

        }
    }

    private fun customizeShare() {
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
                    ShareUtils(this@MainActivity).showCustomize("运营活动",
                        "我想要分享一些东西 https://www.hao123.com/?1562762562",FileProvider7.getUriForFile(this@MainActivity,
                        File(savePath)))
                }

                override fun pending(task: BaseDownloadTask?, soFarBytes: Int, totalBytes: Int) {
                }

                override fun error(task: BaseDownloadTask?, e: Throwable?) {
                    Log.d("test",""+e?.message)
                }

                override fun progress(task: BaseDownloadTask?, soFarBytes: Int, totalBytes: Int) {
                    Log.d("test",""+soFarBytes.toString())
                }

                override fun paused(task: BaseDownloadTask?, soFarBytes: Int, totalBytes: Int) {

                }

            }).start()
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
                   ShareUtils(this@MainActivity).shareFb("Share Demo","运营活动","我想要分享一些东西。https://www.hao123.com/?1562762562",savePath)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager?.onActivityResult(requestCode, resultCode, data);
    }
}
