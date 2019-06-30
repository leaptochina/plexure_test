package com.blueberrysolution.pinelib19.addone.ui_inject

import java.lang.reflect.AccessibleObject.setAccessible
import android.view.ViewGroup
import android.app.Activity
import android.app.Dialog
import android.view.View
import android.widget.ListView
import com.blueberrysolution.pinelib19.addone.ui_inject.instances.OnClickInf
import com.blueberrysolution.pinelib19.addone.ui_inject.instances.OnItemClickInf
import com.blueberrysolution.pinelib19.addone.ui_inject.interfaces.InjectClick
import com.blueberrysolution.pinelib19.addone.ui_inject.interfaces.InjectItemClick
import com.blueberrysolution.pinelib19.addone.ui_inject.interfaces.InjectView
import com.blueberrysolution.pinelib19.debug.G





/**
 * 界面注入类
 *
 * <pre>
 *
</pre> *
 */
object UiInject {



    /**
     * 对Activity进行控件注入
     */
     fun inject(activity:Activity) {
        UiInject.inject(activity, (activity
            .findViewById<View>(android.R.id.content) as ViewGroup).getChildAt(0))

    }


    /**
     * 对Dialog进行控件注入
     */
	 fun inject(dialog: Dialog) {
        UiInject.inject(dialog, (dialog
        .findViewById(android.R.id.content) as ViewGroup).getChildAt(0))

    }


    /**
     * 对View进行控件注入
     */
    fun inject(view:View) {
        UiInject.inject(view, view)

    }


 // @InjectView(R.id.addToDictionary)
	 fun inject(activity:Any, baseView:View) {
        val fields = activity.javaClass.declaredFields
         // Annotation[] annotations;
        for (field in fields)
        {
            val injectView = field.getAnnotation(InjectView::class.java)

            if (injectView != null)
            {
                val view:View
                try
                {
//                    val m = injectView!!.javaClass.getDeclaredMethod("value")
//                    val id = m.invoke(injectView) as Int
                    val id = injectView.value;
                    view = baseView.findViewById(id)



                    field.isAccessible = true
                    field.set(activity, view)

                    G.i("界面加载 - " + field.name)

                }
                catch (e:Exception) {
                    G.e(field.toGenericString() + "赋值失败 - " + e.toString())
                }

            }

        }

        val methods = activity.javaClass.declaredMethods
        for (method in methods)
        {
            val injectClick = method.getAnnotation(InjectClick::class.java)
            if (injectClick != null)
            {
                val view:View
                try
                {
//                    val m = injectClick!!.javaClass.getDeclaredMethod(
//                    "value")
//                    val id = m.invoke(injectClick) as Int
                    val id = injectClick.value;
                    view = baseView.findViewById(id)

                    val onClickInf = OnClickInf()
                    onClickInf.activity = activity
                    onClickInf.method = method

                    view.setOnClickListener(onClickInf)

                    G.i(
                        "界面加载 - " + method.name
                        + " = setOnClickListener()"
                    )
                }
                catch (e:Exception) {
                    G.e((method.toGenericString() + "设置点击监听器失败 - "
                    + e.toString()))
                }

            }
            val injectItemClick = method
                .getAnnotation(InjectItemClick::class.java)
            if (injectItemClick != null)
            {
                val listView:ListView
                try
                {
//                    val m = injectItemClick!!.javaClass.getDeclaredMethod(
//                    "value")
//                    val id1 = m.invoke(injectItemClick) as Int
                    val id1 = injectItemClick.value;
                    listView = baseView.findViewById(id1) as ListView


                    val onItemClickInf = OnItemClickInf()
                    onItemClickInf.activity = activity
                    onItemClickInf.method = method

                    listView.setOnItemClickListener(onItemClickInf)

                     // listView.setBackgroundColor(Color.RED);
                    G.i(("界面加载 - " + method.name
                    + " = setOnItemClickListener()"))

                }
                catch (e:Exception) {
                    G.e((method.toGenericString() + "设置Item点击监听器失败 - "
                    + e.toString()))
                }

            }

        }
    }
}
