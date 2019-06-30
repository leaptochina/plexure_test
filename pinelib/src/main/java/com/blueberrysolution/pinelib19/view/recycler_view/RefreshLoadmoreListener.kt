package com.blueberrysolution.pinelib19.view.recycler_view

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.blueberrysolution.pinelib19.debug.G

class RefreshLoadmoreListener {
    var swipe_refreshlayout: SwipeRefreshLayout? = null;
    var onRefresh: () -> Unit = ::onRefreshInner;
    var onLoadmore: () -> Unit = ::onLoadmoreInner;

    constructor(swipe_refreshlayout: SwipeRefreshLayout? = null){
        this.swipe_refreshlayout = swipe_refreshlayout;
    }

    constructor(swipe_refreshlayout: SwipeRefreshLayout? = null, onRefresh: () -> Unit){
        this.swipe_refreshlayout = swipe_refreshlayout;
        this.onRefresh = onRefresh;
    }

    constructor(swipe_refreshlayout: SwipeRefreshLayout? = null, onRefresh: () -> Unit, onLoadmore: () -> Unit){
        this.swipe_refreshlayout = swipe_refreshlayout;
        this.onRefresh = onRefresh;
        this.onLoadmore = onLoadmore;

    }

    fun startRefresh(){
        swipe_refreshlayout?.isRefreshing = true
    }

    fun stopRefresh(){
        swipe_refreshlayout?.isRefreshing = false
    }

    fun onRefreshInner() {
        G.d("Not Set On Refresh Function")
    }

    fun onLoadmoreInner(){
        G.d("Not Set On Loadmore Function")
    }
}