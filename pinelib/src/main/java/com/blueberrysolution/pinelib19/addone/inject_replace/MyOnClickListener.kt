package com.blueberrysolution.pinelib19.addone.inject_replace

import android.view.View
import com.blueberrysolution.pinelib19.debug.G

/**
 * 用法
 * search_history.setOnClickListener(MyOnClickListener(::onSearchHistoryItemClick));
 */

class MyOnClickListener(onClickFun: (v: View) -> Unit): View.OnClickListener{
    val onClickFun: (v: View) -> Unit

    override fun onClick(v: View?) {
        if (v != null)
            onClickFun(v)
        else
            G.e("MyOnClickListener： Null Point Exception! ")
    }



    init{
        this.onClickFun = onClickFun;
    }



}
