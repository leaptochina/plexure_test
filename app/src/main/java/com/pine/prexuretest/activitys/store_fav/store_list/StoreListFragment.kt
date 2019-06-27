package com.pine.prexuretest.activitys.store_fav.store_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.blueberrysolution.pinelib19.activity.A
import com.blueberrysolution.pinelib19.activity.T
import com.blueberrysolution.pinelib19.addone.broadcast.Broadcast
import com.blueberrysolution.pinelib19.addone.broadcast.OnBroadcast
import com.blueberrysolution.pinelib19.addone.broadcast.gps.OnGpsBroadcast
import com.blueberrysolution.pinelib19.addone.inject_replace.MyOnClickListener
import com.blueberrysolution.pinelib19.view.recycler_view.RecyViewSetup
import com.blueberrysolution.pinelib19.view.recycler_view.RefreshLoadmoreListener
import com.pine.prexuretest.R
import com.pine.prexuretest.activitys.store_fav.StoreFavActivity
import com.pine.prexuretest.beans.Store
import com.pine.prexuretest.retrofit.Requests
import com.pine.prexuretest.retrofit.RetrofitManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.store_list.*
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.Observer
import kotlinx.android.synthetic.main.store_list_title.*
import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList


class StoreListFragment(var storeFavActivity: StoreFavActivity) : androidx.fragment.app.Fragment(),
  OnBroadcast {



  lateinit var innerView: View;
  lateinit var storeAdpter: StoreListAdaper;
  var refreshLoadmoreListener: RefreshLoadmoreListener? = null;


  var originStoreInfo: List<Store> = ArrayList<Store>();
  var processedList: List<Store> = ArrayList<Store>();

  var isShowAddress = false;
  var sortMethod = 0;

  var api = RetrofitManager.i().create(Requests::class.java)

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    innerView = A.v(R.layout.store_list)
    storeAdpter = StoreListAdaper(this)
    Broadcast.i.reg("onGpsLocationChanged", this)



    return innerView;
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    layout_filter_btn.setOnClickListener(MyOnClickListener(::onFilterClick))
    layout_sort_btn.setOnClickListener(MyOnClickListener(::onSortClick))
  }

  fun onFilterClick(view: View) {

  }

  fun onSortClick(view: View) {
    if (sortMethod == 0){
      sortMethod = 1;
      sort_btn.setImageResource(R.drawable.xiangshangzhanhang)
      T.t("Sort by distance (Increase)")
      //increase
    }
    else if (sortMethod == 1){
      sortMethod = 2;
      sort_btn.setImageResource(R.drawable.xiangxiazhanhang)
      T.t("Sort by distance (Decrease)")
      //Decrease
    }
    else{
      sortMethod = 0;
      sort_btn.setImageResource(R.drawable.kuaisubianpai)
      T.t("Default Sort")
      //Default
    }
    sortData()
  }

  override fun onBroadcast(key: String, withObject: Any?) {
    if (key.equals("onGpsLocationChanged")){

      refreshData();

    }
  }



  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

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
          isShowAddress = false;

          sortData()
        }


    }

  }


  private fun sortData() {
    processedList = originStoreInfo;

    if (sortMethod > 0){
      Collections.sort(processedList, object : Comparator<Store>{
        override fun compare(o1: Store, o2: Store): Int {
          var i = o1.distance - o2.distance
          if (sortMethod == 1) {
            i = -i
          }
          return i
        }
      })
    }



    refreshUI();
  }



  private fun refreshUI() {
    refreshLoadmoreListener!!.stopRefresh()

    storeAdpter.notifyDataSetChanged();

    setUp2SecsDelay()
  }

  private fun setUp2SecsDelay() {
    val observable = Observable.create(ObservableOnSubscribe<Int> { emitter ->
      try {
        Thread.sleep(2000)
      } catch (e: Exception) {

      }

      emitter.onNext(1)
      emitter.onComplete()
    })

    val observer = object : Observer<Int> {
      override fun onSubscribe(d: Disposable?) {

      }

      override fun onNext(value: Int?) {

      }

      override fun onError(e: Throwable?) {

      }

      override fun onComplete() {
        isShowAddress = true;
        storeAdpter.notifyDataSetChanged();
      }
    }

    observable.subscribeOn(Schedulers.newThread())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe(observer);

  }



}
