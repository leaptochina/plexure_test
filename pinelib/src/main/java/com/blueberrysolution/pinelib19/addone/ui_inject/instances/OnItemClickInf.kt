package com.blueberrysolution.pinelib19.addone.ui_inject.instances

import android.view.View
import android.widget.AdapterView
import com.blueberrysolution.pinelib19.debug.G
import android.widget.AdapterView.OnItemClickListener
import java.lang.reflect.Method


class OnItemClickInf : OnItemClickListener {
    var method: Method? = null
    var activity: Any? = null


    override fun onItemClick(arg0: AdapterView<*>, arg1: View, arg2: Int, arg3: Long) {
        try {
            method!!.invoke(activity, arg0, arg1, arg2, arg3)
        } catch (e: Exception) {
            G.w("onItemClick不存在或内部有异常，请查看LOG堆栈" + e.toString())

        }

    }

}
