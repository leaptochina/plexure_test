package com.blueberrysolution.pinelib19.addone.mytimer

import android.os.Bundle
import android.os.Handler
import android.os.Message
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.lang.ref.WeakReference
import java.util.*


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class MyTimer {

    var onTimerListener: WeakReference<OnTimerListener>? = null;
    var interval: Long = 1000;
    var timer: Timer? = null;
    var isRunning: Boolean = false;
    var isRunInThread: Boolean? = null;

    var task: TimerTask? = object : TimerTask() {
        override fun run() {

            if (isRunInThread == null){
                onTimerListener?.get()?.onTimer();
            }
            else{
                doAsync {
                    if (isRunInThread!!){
                        onTimerListener?.get()?.onTimer();
                    }
                    else{
                        uiThread {
                            onTimerListener?.get()?.onTimer();
                        }
                    }

                }
            }

        }
    };



    fun stop(){
        isRunning = false;
        timer?.cancel();
    }

    fun runInThread(isRunInThread: Boolean = true): MyTimer{
        this.isRunInThread = isRunInThread;

        return this;
    }



    fun start(): MyTimer{
        isRunning = true;
        timer = Timer(true);
        timer?.schedule(task, interval, interval); // 延时1000ms后执行，1000ms执行一次

        return this;
    }

    fun startOnce(): MyTimer{
        isRunning = true;
        timer = Timer(true);
        timer?.schedule(task, interval); // 延时1000ms后执行，1000ms执行一次

        return this;
    }

    fun setOnTimerListener(onTimerListener: OnTimerListener): MyTimer{
        this.onTimerListener = WeakReference(onTimerListener);

        return  this;
    }

    



    fun setInterval(interval: Long): MyTimer {
        if (interval > 0){
            this.interval = interval;
        }
        return  this;
    }


    companion object {
        fun i(): MyTimer {
            return MyTimer();
        }
    }

}
