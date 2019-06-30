package com.blueberrysolution.pinelib19.addone.broadcast.gps

import android.Manifest
import androidx.core.app.ActivityCompat.startActivityForResult
import android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS
import androidx.core.view.accessibility.AccessibilityEventCompat.setAction
import android.content.Intent
import android.widget.Toast
import androidx.core.app.ActivityCompat
import android.Manifest.permission
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.content.Context
import androidx.core.content.ContextCompat
import android.os.Build
import android.location.LocationManager
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.location.Location
import android.provider.Settings
import androidx.core.content.ContextCompat.getSystemService
import com.blueberrysolution.pinelib19.activity.A
import com.blueberrysolution.pinelib19.activity.T
import android.content.Context.LOCATION_SERVICE
import android.location.LocationListener
import android.os.Bundle
import androidx.core.content.ContextCompat.getSystemService
import com.blueberrysolution.pinelib19.addone.broadcast.Broadcast
import com.blueberrysolution.pinelib19.addone.mytimer.MyTimer
import com.blueberrysolution.pinelib19.pop_up_windows.msgbox.MessageBox

/*
* 当前文件必须在不会被卸载上下文的Activity初始化
*
*
* */
class OnGpsBroadcast: LocationListener{
    var lastLocation: Location? = null;



    override fun onLocationChanged(location: Location?) {
        lastLocation = location
        Broadcast.i.send("onGpsLocationChanged", location)
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
        //Broadcast.i.send("onGpsStatusChanged", provider)
    }

    override fun onProviderEnabled(provider: String?) {
        Broadcast.i.send("onGpsProviderEnabled", provider)
    }

    override fun onProviderDisabled(provider: String?) {
        Broadcast.i.send("onGpsProviderDisabled", provider)
    }


    val READ_PHONE_STATE = 100;//定位权限请求
    val PRIVATE_CODE = 1315;//开启GPS权限
    val LOCATIONGPS = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.READ_PHONE_STATE
    )

    init{
        showGPSContacts()

    }


    /**
     * 检测GPS、位置权限是否开启
     */
    fun showGPSContacts() {
        var lm = A.app().getSystemService(LOCATION_SERVICE) as LocationManager
        val ok = lm.isProviderEnabled(LocationManager.GPS_PROVIDER)
        if (ok) {//开了定位服务
            if (Build.VERSION.SDK_INT >= 23) { //判断是否为android6.0系统版本，如果是，需要动态添加权限
                if (ContextCompat.checkSelfPermission(
                        A.a(),
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PERMISSION_GRANTED
                ) {// 没有权限，申请权限。

                    ActivityCompat.requestPermissions(
                        A.a(), LOCATIONGPS,
                        READ_PHONE_STATE
                    )

                } else {
                    setupAutoRefresh()//getLocation为定位方法
                }
            } else {
                setupAutoRefresh()//getLocation为定位方法
            }
        } else {
            val intent = Intent()
            intent.action = Settings.ACTION_LOCATION_SOURCE_SETTINGS
            A.a().startActivityForResult(intent, PRIVATE_CODE)
        }
    }



    @SuppressLint("MissingPermission")
    fun setupAutoRefresh(){
        Broadcast.i.send("LocationPermissions", true)
        var location = Location(LocationManager.GPS_PROVIDER)
        val locationManager = A.a().getSystemService(Context.LOCATION_SERVICE) as LocationManager?

//    第一个参数是LocationProvider对象，第二个参数是刷新的时间差，这里设定为3秒，第三个参数是位置差，这里设定为1米，第四个参数为一个位置监听器对象。
        locationManager!!.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 1f, this)
    }


    /*
    * 当前文件必须在不会被卸载上下文的Activity初始化
    *
    *
    * */
    companion object {
        var onGpsBroadcast: OnGpsBroadcast? = null;
        fun i(): OnGpsBroadcast {
            if (onGpsBroadcast == null){
                onGpsBroadcast = OnGpsBroadcast();
            }
            return onGpsBroadcast!!;
        }
    }
}