package com.blueberrysolution.pinelib19.addone.inject_replace

import android.view.KeyEvent
import android.view.View
import com.blueberrysolution.pinelib19.debug.G

/**
 * 用法
 * search_text_input.setOnKeyListener(MyOnKeyListener(::onSearchTextChange))
 */

class MyOnKeyListener(onKeyFunction: (v: View?, keyCode: Int, event: KeyEvent?) -> Boolean ): View.OnKeyListener{
    val onKeyFunction: (v: View?, keyCode: Int, event: KeyEvent?) -> Boolean


    override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
        return onKeyFunction(v, keyCode, event)
    }




    init{
        this.onKeyFunction = onKeyFunction;
    }



}