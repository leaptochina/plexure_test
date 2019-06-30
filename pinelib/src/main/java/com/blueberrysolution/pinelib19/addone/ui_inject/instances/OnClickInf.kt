package com.blueberrysolution.pinelib19.addone.ui_inject.instances

import android.view.View
import com.blueberrysolution.pinelib19.debug.G
import java.lang.reflect.Method


class OnClickInf : View.OnClickListener {
    var method: Method? = null
    var activity: Any? = null


    override fun onClick(v: View) {
        try {
            method!!.invoke(activity, v)
        } catch (e: Exception) {
            G.w("OnClick不存在或内部有异常，请查看LOG堆栈")

        }

    }



}
