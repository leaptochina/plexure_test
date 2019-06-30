package com.blueberrysolution.pinelib19.view.recycler_view

import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

import com.blueberrysolution.pinelib19.activity.A

/**
 *         使用方法
adapter = StopDetailAdapter(this);
refreshLoadmoreListener = RefreshLoadmoreListener(swipe_refreshlayout, ::onRefresh)
RecyViewSetup(buses_list, adapter).setOnRefreshLoadmoreListener(refreshLoadmoreListener).build()


 */
class RecyViewSetup : SwipeRefreshLayout.OnRefreshListener {


    var recycleView: RecyclerView;
    var refreshLoadmoreListener: RefreshLoadmoreListener? = null;
    var layoutManager = LinearLayoutManager(A.a())
    var animation = DefaultItemAnimator()
    var divider = DividerItemDecoration(A.a(), LinearLayoutManager.VERTICAL)
    var adapter: RecyclerView.Adapter<*>;


    constructor(recycleView: RecyclerView, adapter: RecyclerView.Adapter<*>){
        this.recycleView = recycleView;
        this.adapter = adapter;
    }

    fun setOnRefreshLoadmoreListener(refreshLoadmoreListener: RefreshLoadmoreListener?): RecyViewSetup {
        this.refreshLoadmoreListener = refreshLoadmoreListener;
        return this
    }

    fun build(): RecyViewSetup {

        //设置布局管理器
        recycleView.setLayoutManager(layoutManager)
        recycleView.adapter = adapter;
        recycleView.setItemAnimator(animation)
        recycleView.addItemDecoration(divider)

        if (refreshLoadmoreListener != null)
            refreshLoadmoreListener!!.swipe_refreshlayout?.setOnRefreshListener (this)


        return this;
    }

    override fun onRefresh() {
        refreshLoadmoreListener!!.onRefresh();
    }

}