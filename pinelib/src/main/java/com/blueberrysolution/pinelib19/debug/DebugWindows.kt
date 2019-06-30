package com.blueberrysolution.pinelib19.debug

import android.content.Context
import android.view.WindowManager
import com.blueberrysolution.pinelib19.activity.A
import android.view.Gravity
import android.graphics.PixelFormat
import android.os.Build
import android.text.Html
import android.view.View
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.ListView
import android.widget.TextView
import com.blueberrysolution.pinelib19.R
import com.blueberrysolution.pinelib19.activity.BaseApp
import com.blueberrysolution.pinelib19.addone.broadcast.Broadcast
import com.blueberrysolution.pinelib19.addone.broadcast.OnBroadcast
import com.blueberrysolution.pinelib19.addone.mytimer.MyTimer
import com.blueberrysolution.pinelib19.addone.mytimer.OnTimerListener
import com.blueberrysolution.pinelib19.addone.share_preferences.Sp
import com.blueberrysolution.pinelib19.permission.RequireFloatWindow
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.lang.Exception

object DebugWindows : OnTimerListener, OnBroadcast {



    var isInit = false;
    var isShowing = false;


    var mWindowManager: WindowManager? = null;
    val params = WindowManager.LayoutParams()

    var adapter: DebugWindowAdapter? = null
    lateinit var mView: View;
    lateinit var baseLayout: View;
    lateinit var log_list: ListView;


    override fun onTimer() {
        checkAndUpdateLogs();
        //logsTextView.setText(Html.fromHtml(G.logs.toString()))
    }

    fun checkAndUpdateLogs(){
        doAsync {
            uiThread {
                if (G.needUpdate) {
                    G.needUpdate = false;
                    adapter?.notifyDataSetChanged();
                }
            }
        }

    }

    init {
        if (BaseApp.i().isDebug){
            Broadcast.i.reg("key_up_down", this);
            show(Sp.i.get("is_debug_showing", true));
        }

    }

    fun doInit() {
        if (isInit) return;

        if (RequireFloatWindow.require()) {

            // 获取WindowManager
            mWindowManager = A.c().getSystemService(Context.WINDOW_SERVICE) as WindowManager

            adapter = DebugWindowAdapter();



            //Setup View
            mView = A.v(R.layout.debug_window);
            baseLayout = mView.findViewById(R.id.debug_window_baseview)
            log_list = mView.findViewById(R.id.log_list) as ListView
            log_list.adapter = adapter;





            // 类型
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){//6.0
                params.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
            }else {
                params.type =  WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
            }

            // 设置flag
            var flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
            flags = flags or WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE


            // | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
            // 如果设置了WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE，弹出的View收不到Back键的事件
            params.flags = flags
            // 不设置这个弹出框的透明遮罩显示为黑色
            params.format = PixelFormat.TRANSLUCENT
            // FLAG_NOT_TOUCH_MODAL不阻塞事件传递到后面的窗口
            // 设置 FLAG_NOT_FOCUSABLE 悬浮窗口较小时，后面的应用图标由不可长按变为可长按
            // 不设置这个flag的话，home页的划屏会有问题
            params.width = WindowManager.LayoutParams.MATCH_PARENT
            params.height = WindowManager.LayoutParams.MATCH_PARENT
            params.gravity = Gravity.CENTER



            MyTimer.i().setInterval(500).setOnTimerListener(this).runInThread(false).start();



            isInit = true;
        }
    }

    override fun onBroadcast(key: String, withObject: Any?) {
        if (key.equals("key_up_down")){
            if (isShowing){
                hide()
            }
            else{
                show()
            }
        }
    }


    fun show(isShow: Boolean = true){
        try
        {
            doInit();
            if (isShow)
            {
                if (!isShowing)
                    mWindowManager!!.addView(mView, params);
            }
            else
            {
                if (isShowing)
                    mWindowManager!!.removeView(mView);
            }
            Sp.i.put("is_debug_showing", isShow)
            isShowing = isShow;
        }
        catch(e: Exception){
            G.e("悬浮窗创建失败" + e)
        }

    }



    fun hide(){
        show(false);
    }






}

