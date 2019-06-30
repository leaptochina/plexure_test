package com.blueberrysolution.pinelib19.view.recycler_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.blueberrysolution.pinelib19.debug.G
import com.blueberrysolution.pinelib19.activity.A


/*
用法

初始化调用

return MyRecyViewHolder.i<BusLocationViewHolder>(parent, R.layout.bus_location_list_item)

类

class StopDetailAdapterViewHolder(v: View): MyRecyViewHolder(v) {
    val route_number: TextView? = null;
}

holder.route_number!!.text = "123"
 */
open class MyRecyViewHolder(v: View): RecyclerView.ViewHolder(v){


    init{

        reflect()

    }

    fun reflect(){
        var fields = this.javaClass.declaredFields;
        for (field in fields) {
            if (field.name == "itemView") continue;
            if (field.name.contains("serial") && field.name.contains("ersion")) continue; //version
            if (field.name[0] == '$') continue;

            try {
                val this_resource_id = A.a().getResources().getIdentifier(field.name, "id", A.a().getPackageName())
                var view = itemView.findViewById(this_resource_id) as View

                field.isAccessible = true
                field.set(this, view)

                //G.d("Adapter加载 - " + field.name)

            } catch (e: Exception) {
                G.e(field.toGenericString() + "赋值失败 - " + e.toString())
            }

        }

    }



    companion object {
        public inline fun<reified T: MyRecyViewHolder> i(parent:ViewGroup, r_layout_id: Int): T {
            var holder: T;
            var v = LayoutInflater.from(parent.context).inflate(r_layout_id, parent, false)
            val c1 = T::class.java.getConstructor(View::class.java)
            c1.setAccessible(true)
            holder = c1.newInstance(v)

            return holder
        }
    }
}