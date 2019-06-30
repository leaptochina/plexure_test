package com.blueberrysolution.pinelib19.activity.global_exception

import android.util.Log
import android.content.ContentValues.TAG
import com.blueberrysolution.pinelib19.activity.T
import com.blueberrysolution.pinelib19.debug.G
import org.jetbrains.anko.getStackTraceString


class ExceptionExt{
    fun reg(){
        Thread.setDefaultUncaughtExceptionHandler(::handler)

    }

    fun handler(t: Thread, e: Throwable){
        run{

            T.t("Global Error Catched: " + e.toString())
            G.e(e.getStackTraceString())

        }
    }
}
