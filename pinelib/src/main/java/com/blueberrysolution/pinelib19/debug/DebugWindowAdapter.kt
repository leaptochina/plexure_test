package com.blueberrysolution.pinelib19.debug

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.blueberrysolution.pinelib19.R
import com.blueberrysolution.pinelib19.activity.A
import com.blueberrysolution.pinelib19.addone.inject_replace.MyViewHolder

class DebugWindowAdapter: BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var v = convertView;
        if (v == null){
            v = A.v(R.layout.debug_window_adapter);
        }

        var log_detail = v.findViewById<TextView>(R.id.log_detail)

        var log = G.logList[G.logList.count() - position - 1]

        log_detail.text = log.detail;
        log_detail.setTextColor( Color.parseColor(log.color) )


        return v;

    }

    override fun getItem(position: Int): Any {
        return "";
    }

    override fun getItemId(position: Int): Long {
        return position.toLong();
    }

    override fun getCount(): Int {
        return G.logList.count();
    }
}