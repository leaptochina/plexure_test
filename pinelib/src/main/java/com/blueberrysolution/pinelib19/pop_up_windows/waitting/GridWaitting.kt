package com.blueberrysolution.pinelib19.pop_up_windows.waitting

import android.content.Context
import android.os.Bundle
import com.blueberrysolution.pinelib19.R
import com.blueberrysolution.pinelib19.activity.A
import com.blueberrysolution.pinelib19.addone.broadcast.Broadcast
import com.blueberrysolution.pinelib19.addone.inject_replace.MyOnItemClickListener
import com.blueberrysolution.pinelib19.pop_up_windows.MyDialog
import kotlinx.android.synthetic.main.waitting_dialog.*

/**
 * 这个需要将API级别提升到 19
 * 不想提升 所以注释掉了
 *
 */
class GridWaitting : Waitting {
    constructor ():  super() {
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.waitting_dialog)
        setCanceledOnTouchOutside(false)


        //loadingView.startMoving();


    }

    override fun hide() {
        if (GridWaitting.waitting != null){
            GridWaitting.waitting = null;
            //loadingView.stopMoving()
            super.hide()
        }

    }

    companion object {
        var waitting: GridWaitting? = null;
        fun i(): GridWaitting{
            if (waitting == null){
                waitting = GridWaitting();
            }
            return waitting!!;
        }
    }
}