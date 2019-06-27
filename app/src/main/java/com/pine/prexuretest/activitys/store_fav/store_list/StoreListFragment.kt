package com.pine.prexuretest.activitys.store_fav.store_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.blueberrysolution.pinelib19.activity.A
import com.blueberrysolution.pinelib19.activity.I
import com.blueberrysolution.pinelib19.activity.T
import com.blueberrysolution.pinelib19.addone.broadcast.Broadcast
import com.blueberrysolution.pinelib19.addone.broadcast.OnBroadcast
import com.blueberrysolution.pinelib19.addone.broadcast.gps.OnGpsBroadcast
import com.blueberrysolution.pinelib19.addone.inject_replace.MyOnClickListener
import com.blueberrysolution.pinelib19.addone.inject_replace.MyOnItemClickListener
import com.blueberrysolution.pinelib19.net.entry.N
import com.blueberrysolution.pinelib19.view.recycler_view.RecyViewSetup
import com.blueberrysolution.pinelib19.view.recycler_view.RefreshLoadmoreListener
import com.pine.prexuretest.R
import com.pine.prexuretest.activitys.store_fav.StoreFavActivity
import com.pine.prexuretest.activitys.store_fav.store_fav.StoreFavSharePreference
import com.pine.prexuretest.beans.Store
import com.pine.prexuretest.retrofit.Requests
import com.pine.prexuretest.retrofit.RetrofitManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_store_fav.*
import kotlinx.android.synthetic.main.store_list.*


class StoreListFragment(var storeFavActivity: StoreFavActivity) : androidx.fragment.app.Fragment(),
  OnBroadcast {


  lateinit var innerView: View;
  lateinit var storeAdpter: StoreListAdaper;
  var refreshLoadmoreListener: RefreshLoadmoreListener? = null;


  var originStoreInfo: List<Store> = ArrayList<Store>();
  var processedList: List<Store> = ArrayList<Store>();

  var api = RetrofitManager.i().create(Requests::class.java)

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    innerView = A.v(R.layout.store_list)
    storeAdpter = StoreListAdaper(this);
    Broadcast.i.reg("onGpsLocationChanged", this)

    return innerView;
  }

  override fun onBroadcast(key: String, withObject: Any?) {
    if (key.equals("onGpsLocationChanged")){

      refreshData();

    }
  }



  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)






    //解决数据加载不完的问题
    recycler_view_store_list.isNestedScrollingEnabled = false
    recycler_view_store_list.setHasFixedSize(true)
    //解决数据加载完成后, 没有停留在顶部的问题
    recycler_view_store_list.isFocusable = false


    refreshLoadmoreListener = RefreshLoadmoreListener(swipe_refresh_store_list, ::refreshData)
    RecyViewSetup(recycler_view_store_list, storeAdpter).setOnRefreshLoadmoreListener(refreshLoadmoreListener).build()



    refreshData();

  }


  fun refreshData() {
    refreshLoadmoreListener!!.startRefresh()
    if ( OnGpsBroadcast.i().lastLocation == null){
      T.t("Waiting for Locating...")

    }
    else{
      var la = OnGpsBroadcast.i().lastLocation?.latitude!!;
      var lo = OnGpsBroadcast.i().lastLocation?.longitude!!
      api.getStores(la, lo, 100000000, 100)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe { works ->
          originStoreInfo = works;

          sortData()
        }


    }

  }


  private fun sortData() {


    processedList = originStoreInfo;
    refreshUI();
  }

  private fun refreshUI() {
    refreshLoadmoreListener!!.stopRefresh()

    storeAdpter.notifyDataSetChanged();
  }


}
