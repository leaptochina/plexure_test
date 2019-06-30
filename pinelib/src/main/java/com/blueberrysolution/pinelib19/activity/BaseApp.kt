package com.blueberrysolution.pinelib19.activity

import androidx.multidex.MultiDexApplication
import com.blueberrysolution.pinelib19.activity.global_exception.ExceptionExt
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher

open class BaseApp: MultiDexApplication() {

    var isDebug: Boolean = true;
    var realtimeLogShow: Boolean = false;
    var releaseSignature: String = "";
    var sRefWatcher: RefWatcher? = null;

    init{
        baseApp = this;
        A.app(this);
    }

    override fun onCreate() {
        super.onCreate()


        ExceptionExt().reg();


        // Access property for Context
        ZSignalCheck();

        installLeakCanary();



    }


    fun installLeakCanary(){
        if (isDebug){
            sRefWatcher = LeakCanary.install(this);

        }

    }


    companion object {
        lateinit var baseApp: BaseApp;

        fun i(): BaseApp{
            return baseApp;
        }


    }



}
