
package com.lyc.indonesia.mediademo.photo

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.hardware.Camera
import android.os.Build
import android.os.Bundle
import android.os.Environment.DIRECTORY_PICTURES
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import android.view.*
import android.widget.AdapterView
import com.lyc.indonesia.mediademo.R
import com.lyc.indonesia.mediademo.utils.FileUtils
import kotlinx.android.synthetic.main.activity_take_picture.*
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import android.support.v4.view.ViewCompat.setRotation
import android.hardware.Camera.CameraInfo
import android.view.OrientationListener.ORIENTATION_UNKNOWN


class TakePictureActivity : AppCompatActivity() {

    public var mCurrentWhiteBalace=Camera.Parameters.WHITE_BALANCE_AUTO


    //需要申请的权限
    private val permissions = arrayOf(Manifest.permission.CAMERA)
    private val CAMERA_PERMISSION_CODE = 1

    /**
     * 当前打开摄像头类型，当前写的是后置摄像头
     */
    private val currentCameraType = Camera.CameraInfo.CAMERA_FACING_BACK

    private val TAG = this.javaClass.simpleName

    var mCamera: Camera?=null


    private val defaultFile={
        val formatTime=SimpleDateFormat("yyyy_MM_dd_hh_mm_ss")
        val fileRoot=getExternalFilesDir(DIRECTORY_PICTURES)
        File(fileRoot,formatTime.format(Date())+".jpg")
    }

    fun  createFile(root:String?):String{
        val rootFile=File(root)
        if (!rootFile.exists()){
            rootFile.mkdirs()
        }
        val formatTime=SimpleDateFormat("yyyy_MM_dd_hh_mm_ss")
        return File(root,formatTime.format(Date())+".jpg").absolutePath
    }

    private var mPath:String?=null

    private var mCallBack: SurfaceHolder.Callback? = null

    private fun safeCameraOpen(id: Int): Boolean {
        return try {
            //释放摄像头
            releaseCameraAndPreview()
            mCamera = Camera.open(id)
            true
        } catch (e: Exception) {
            Log.e("PreView", "failed to open Camera")
            e.printStackTrace()
            false
        }
    }

    private fun releaseCameraAndPreview() {
        mCamera=null
        mCamera?.also { camera ->
            camera.release()
            mCamera = null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 清除标题栏全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)


        setContentView(R.layout.activity_take_picture)

        mPath=intent?.getStringExtra("path")

        checkAndInitCamera()

        btn_camera.setOnClickListener {
            takePicture()
        }


        sp_white_balance.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when(position){
                    0->{
                        if (mCamera?.parameters?.supportedWhiteBalance?.contains(Camera.Parameters.WHITE_BALANCE_AUTO)==true){
                            mCurrentWhiteBalace=Camera.Parameters.WHITE_BALANCE_AUTO
                        }
                    }
                    1->{
                        if (mCamera?.parameters?.supportedWhiteBalance?.contains(Camera.Parameters.WHITE_BALANCE_INCANDESCENT)==true){
                            mCurrentWhiteBalace=Camera.Parameters.WHITE_BALANCE_INCANDESCENT
                        }
                    }
                    2->{
                        if (mCamera?.parameters?.supportedWhiteBalance?.contains(Camera.Parameters.WHITE_BALANCE_FLUORESCENT)==true){
                            mCurrentWhiteBalace=Camera.Parameters.WHITE_BALANCE_FLUORESCENT
                        }
                    }
                    3->{
                        if (mCamera?.parameters?.supportedWhiteBalance?.contains(Camera.Parameters.WHITE_BALANCE_WARM_FLUORESCENT)==true){
                            mCurrentWhiteBalace=Camera.Parameters.WHITE_BALANCE_WARM_FLUORESCENT
                        }
                    }
                    4->{
                        if (mCamera?.parameters?.supportedWhiteBalance?.contains(Camera.Parameters.WHITE_BALANCE_DAYLIGHT)==true){
                            mCurrentWhiteBalace=Camera.Parameters.WHITE_BALANCE_DAYLIGHT
                        }
                    }
                    5->{
                        if (mCamera?.parameters?.supportedWhiteBalance?.contains(Camera.Parameters.WHITE_BALANCE_CLOUDY_DAYLIGHT)==true){
                            mCurrentWhiteBalace=Camera.Parameters.WHITE_BALANCE_CLOUDY_DAYLIGHT
                        }
                    }
                    6->{
                        if (mCamera?.parameters?.supportedWhiteBalance?.contains(Camera.Parameters.WHITE_BALANCE_TWILIGHT)==true){
                            mCurrentWhiteBalace=Camera.Parameters.WHITE_BALANCE_TWILIGHT
                        }
                    }
                    7->{
                        if (mCamera?.parameters?.supportedWhiteBalance?.contains(Camera.Parameters.WHITE_BALANCE_SHADE)==true){
                            mCurrentWhiteBalace=Camera.Parameters.WHITE_BALANCE_SHADE
                        }
                    }
                }
                initCamera()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }



        }

    }

    private fun takePicture() {


        mCamera?.takePicture(object :Camera.ShutterCallback{
            override fun onShutter() {

            }

        },object :Camera.PictureCallback{
            override fun onPictureTaken(data: ByteArray?, camera: Camera?) {

            }

        },object:Camera.PictureCallback{
            override fun onPictureTaken(data: ByteArray?, camera: Camera?) {
                val bitmap=BitmapFactory.decodeByteArray(data,0,data?.size!!)
                var filePath:String?=null
                if (TextUtils.isEmpty(mPath)){
                    filePath=defaultFile().absolutePath
                }else{
                    filePath=createFile(mPath)
                }
                val path=FileUtils.saveBitmap2File(bitmap,filePath)
                val intent = Intent()
                intent.putExtra("data", path)
                setResult(Activity.RESULT_OK,intent)
                finish()
            }

        })
    }


    private fun checkAndInitCamera() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // 检查该权限是否已经获取
            val i = ContextCompat.checkSelfPermission(this, permissions[0])
            // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
            if (i != PackageManager.PERMISSION_GRANTED) {
                // 如果没有授予该权限，就去提示用户请求
                ActivityCompat.requestPermissions(this, permissions, CAMERA_PERMISSION_CODE)
            } else {
                initCamera()
            }
        } else {
            initCamera()
        }
    }


    private fun initCamera() {
        if (mCamera != null) {
            mCamera?.startPreview()
            setPreviewLight()
        }
        //1. Obtain an instance of Camera from open(int).
        //这里可以根据前后摄像头设置
        if (!safeCameraOpen(currentCameraType)) {
            return
        }
        //2. Get existing (default) settings with getParameters().
        //获得存在的默认配置属性
        val parameters = mCamera?.getParameters()

        //3. If necessary, modify the returned Camera.Parameters object and call setParameters(Camera.Parameters).
        //可以根据需要修改属性，这些属性包括是否自动持续对焦、拍摄的gps信息、图片视频格式及大小、预览的fps、
        // 白平衡和自动曝光补偿、自动对焦区域、闪光灯状态等。
        //具体可以参阅https://developer.android.com/reference/android/hardware/Camera.Parameters.html
        if (parameters?.getSupportedFocusModes()?.contains(
                Camera.Parameters
                    .FOCUS_MODE_CONTINUOUS_PICTURE
            )!=null
        ) {
            //自动持续对焦
            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)
        }
        //116.429565,39.903866
        //设置GPS信息
        parameters?.setGpsLongitude(116.429565)
        parameters?.setGpsLongitude(39.903866)
        parameters?.setGpsAltitude(1000.0)
        parameters?.setGpsTimestamp(Date().time)


        //设置白平衡
        parameters?.whiteBalance=mCurrentWhiteBalace
//        parameters?.colorEffect=Camera.Parameters.EFFECT_NEGATIVE

        //在设置图片和预览的大小时要注意当前摄像头支持的大小，不同手机支持的大小不同，如果你的SurfaceView不是全屏，有可能被拉伸。
        // parameters.getSupportedPreviewSizes(),parameters.getSupportedPictureSizes()
        val picSizes = parameters?.getSupportedPictureSizes()
        val resources = this.resources
        val dm = resources.displayMetrics
        val density = dm.density
        val width = dm.widthPixels
        val height = dm.heightPixels
        val picSize = getPictureSize(picSizes, width, height)
        parameters?.setPictureSize(picSize!!.width, picSize.height)
//        parameters?.setRotation(90)
        onOrientationChanged(mCamera,currentCameraType,90)
        Log.d(TAG,parameters?.flatten())
        mCamera?.setParameters(parameters)
        //4. Call setDisplayOrientation(int) to ensure correct orientation of preview.
        //你可能会遇到画面方向和手机的方向不一致的问题，竖向手机的时候，但是画面是横的，这是由于摄像头默认捕获的画面横向的
        // 通过调用setDisplayOrientation来设置PreviewDisplay的方向，可以解决这个问题。

        setCameraDisplayOrientation(this@TakePictureActivity, currentCameraType, mCamera)

        //5. Important: Pass a fully initialized SurfaceHolder to setPreviewDisplay(SurfaceHolder).
        // Without a surface, the camera will be unable to start the preview.
        //camera必须绑定一个surfaceview才可以正常显示。
        try {
            mCamera?.setPreviewDisplay(surfaceView.holder)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        //6. Important: Call startPreview() to start updating the preview surface.
        // Preview must be started before you can take a picture.
        //在调用拍照之前必须调用startPreview()方法,但是在此时有可能surface还未创建成功。
        // 所以加上SurfaceHolder.Callback()，在回调再次初始化下。
        mCamera?.startPreview()
        setPreviewLight()


        //7. When you want, call
        // takePicture(Camera.ShutterCallback, Camera.PictureCallback, Camera.PictureCallback, Camera.PictureCallback)
        // to capture a photo. Wait for the callbacks to provide the actual image data.
        //当如果想要拍照的时候，调用takePicture方法，这个下面我们会讲到。

        //8. After taking a picture, preview display will have stopped. To take more photos, call startPreview() again first.
        //在拍照结束后相机预览将会关闭，如果要再次拍照需要再次调用startPreview（)

        //9. Call stopPreview() to stop updating the preview surface.
        //通过调用stopPreview方法可以结束预览
        //10. Important: Call release() to release the camera for use by other applications.
        // Applications should release the camera immediately in onPause()(and re-open() it in onResume()).
        //建议在onResume调用open的方法，在onPause的时候执行release方法

        val holder = surfaceView.getHolder()
        if (holder != null) {
            if (mCallBack != null) {
                holder.removeCallback(mCallBack)
            }
            mCallBack = object : SurfaceHolder.Callback {
                override fun surfaceCreated(holder: SurfaceHolder) {
                    Log.e(TAG, "surfaceCreated$holder$this")
                    initCamera()
                }

                override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
                    Log.e(TAG, "surfaceChanged$holder$this")
                }

                override fun surfaceDestroyed(holder: SurfaceHolder) {
                    Log.e(TAG, "surfaceDestroyed$holder$this")
                }
            }
            holder.addCallback(mCallBack)
        }
    }

    fun onOrientationChanged(camera: Camera?,cameraId: Int,orientation: Int) {
        var orientation = orientation
        if (orientation == ORIENTATION_UNKNOWN) return
        val info = android.hardware.Camera.CameraInfo()
        android.hardware.Camera.getCameraInfo(cameraId, info)
        orientation = (orientation + 45) / 90 * 90
        var rotation = 0
        if (info.facing == CameraInfo.CAMERA_FACING_FRONT) {
            rotation = (info.orientation - orientation + 360) % 360
        } else {  // back-facing camera
            rotation = (info.orientation + orientation) % 360
        }
        camera?.parameters?.setRotation(rotation)
    }


    //设置相机的方向
    fun setCameraDisplayOrientation(activity: Activity, cameraId: Int, camera: android.hardware.Camera?): Int {
        if (camera==null){
            0
        }
        val info = android.hardware.Camera.CameraInfo()
        android.hardware.Camera.getCameraInfo(cameraId, info)
        val rotation = activity.windowManager.defaultDisplay.rotation
        var degrees = 0
        when (rotation) {
            Surface.ROTATION_0 -> degrees = 0
            Surface.ROTATION_90 -> degrees = 90
            Surface.ROTATION_180 -> degrees = 180
            Surface.ROTATION_270 -> degrees = 270
            else -> degrees = 0
        }
        var result: Int
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            result = (info.orientation + degrees) % 360
            result = (360 - result) % 360   // compensate the mirror
        } else {
            // back-facing
            result = (info.orientation - degrees + 360) % 360
        }
        camera?.setDisplayOrientation(result)
        return degrees
    }


    private fun setPreviewLight() {
        mCamera?.setPreviewCallback(object : Camera.PreviewCallback {
            override fun onPreviewFrame(data: ByteArray, camera: Camera) {
                Log.d("test","sssss");
            }
        })
    }


    /**
     * 获得最合是的宽高size
     */
    private fun getPictureSize(picSizes: List<Camera.Size>?, width: Int, height: Int): Camera.Size? {
        // 对于存储最适合的最小
        var betterSize: Camera.Size? = null
        // 差值
        var diff = Integer.MAX_VALUE
        if (picSizes != null && picSizes.size > 0) {
            for (size in picSizes) {
                val newDiff = Math.abs(size.width - width) + Math.abs(size.height - height)
                // 如果没有任何差别、说明是最合适的，直接返回，减少遍历
                if (newDiff == 0) {
                    return size
                }
                if (newDiff < diff) {
                    betterSize = size
                    diff = newDiff
                }
            }
        }
        return betterSize
    }

    override fun onDestroy() {
        releaseCameraAndPreview()
        super.onDestroy()
    }

}
