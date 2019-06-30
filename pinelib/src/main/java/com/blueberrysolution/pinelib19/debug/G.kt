package com.blueberrysolution.pinelib19.debug

import android.util.Log
import com.blueberrysolution.pinelib19.activity.A
import com.blueberrysolution.pinelib19.activity.BaseApp
import com.blueberrysolution.pinelib19.activity.T
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.FormatStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import java.lang.StringBuilder
import kotlin.math.log

object G{

    var tag = "Pine";
    var logList: ArrayList<LogObject> = ArrayList();
    var needUpdate = false;
    //var logs: StringBuilder = StringBuilder("");



    init{

    }

    fun add(type: String, detail: String): String{
        var logObject: LogObject;
        if (logList.count() >= 80){
            logObject = logList.removeAt(0)
        }
        else{
            logObject = LogObject();
        }



        logObject.color = when {
            type.equals("d") -> "#000000" //Black
            type.equals("i") -> "#006400"//green
            type.equals("w") -> "#9400D3"//Blue
            type.equals("e") -> "#FF0000"//Red
            else -> "#000000"
        }
        logObject.detail = detail;
        logList.add(logObject);

        if (!needUpdate)
            needUpdate = true;

        if(BaseApp.i().realtimeLogShow){
            if (A.activity != null){
                DebugWindows.checkAndUpdateLogs();
            }

        }
//        logs.insert(0, "<font color='${color}'>${detail}</font><br>");

        return  detail;
    }

    fun d(s: String){
        if (BaseApp.i().isDebug){

            var l = add("d", s);
            Log.d(tag, l);
        }


    }

    fun i(s: String){
        if (BaseApp.i().isDebug) {
            var l = add("i", s);
            Log.i(tag, l);
        }
    }

    fun w(s: String){
        var l =  add("w", s);
        Log.w(tag, l);

    }

    fun e(s: String){
        var l =  add("e", s);
        Log.e(tag, l);
        T.t(s)
    }
}