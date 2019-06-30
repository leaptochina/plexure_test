package com.blueberrysolution.pinelib19.addone.inject_replace

import android.view.View
import android.widget.AdapterView


/**
 * 用法
 * search_history.setOnItemClickListener(MyOnItemClickListener(::onSearchHistoryItemClick));
 */

class MyOnItemClickListener(onItemClickFun: (parent: AdapterView<*>?, view: View?, position: Int, id: Long) -> Unit): AdapterView.OnItemClickListener{
    val onItemClickFun: (parent: AdapterView<*>?, view: View?, position: Int, id: Long) -> Unit


    init{
        this.onItemClickFun = onItemClickFun;
    }


    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        onItemClickFun(parent, view, position, id);
    }

}