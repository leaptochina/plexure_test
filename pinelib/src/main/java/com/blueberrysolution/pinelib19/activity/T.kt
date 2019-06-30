package com.blueberrysolution.pinelib19.activity

import android.app.Activity
import android.os.Message
import android.widget.Toast
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


fun Activity.t(message : String) {
    T.t(message);
}

class T(){

    companion object  {


        //强制主线程
        fun t(message: String) {
            doAsync {
                uiThread {
                    Toast.makeText(A.a(), message, Toast.LENGTH_SHORT).show();
                }
            }
        }



    }
}