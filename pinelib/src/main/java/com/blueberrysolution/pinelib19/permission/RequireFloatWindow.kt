package com.blueberrysolution.pinelib19.permission


import android.content.Intent
import android.os.Build
import android.provider.Settings
import com.blueberrysolution.pinelib19.R
import com.blueberrysolution.pinelib19.activity.A
import com.blueberrysolution.pinelib19.activity.T
import com.blueberrysolution.pinelib19.addone.mytimer.MyTimer
import com.blueberrysolution.pinelib19.addone.mytimer.OnTimerListener

object RequireFloatWindow : OnTimerListener {
    override fun onTimer() {

        T.t(A.s(R.string.float_windows_allow));

        var intent:Intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        A.a().startActivityForResult(intent, 1);
    }

    fun require(): Boolean{
        if (Build.VERSION.SDK_INT >= 23) {
            if (!Settings.canDrawOverlays(A.a())) {

                T.t(A.s(R.string.float_windows_allow));

                MyTimer.i().setInterval(4000).setOnTimerListener(this).runInThread(false).startOnce();



                return false;
            } else {
                return true;
            }
        }

        return true;

    }
}