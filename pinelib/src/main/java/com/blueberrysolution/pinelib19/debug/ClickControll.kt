package com.blueberrysolution.pinelib19.debug

import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import com.blueberrysolution.pinelib19.R

class ClickControll(view: View) : View.OnTouchListener {


    lateinit var view: View ;
    lateinit var baseLayout: View;



    init{
        this.view = view;

        baseLayout = view.findViewById(R.id.debug_window_baseview)


        view.setOnTouchListener(this)

    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        return false;
    }




}

