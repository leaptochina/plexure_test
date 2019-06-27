package com.pine.prexuretest.activitys.store_fav.store_fav

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.blueberrysolution.pinelib19.activity.A
import com.blueberrysolution.pinelib19.view.recycler_view.RecyViewSetup
import com.blueberrysolution.pinelib19.view.recycler_view.RefreshLoadmoreListener
import com.pine.prexuretest.R
import com.pine.prexuretest.activitys.store_fav.store_list.StoreFavAdapter
import com.pine.prexuretest.beans.Store
import com.pine.prexuretest.retrofit.Requests
import com.pine.prexuretest.retrofit.RetrofitManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.store_list.*


class StoreFavFragment : androidx.fragment.app.Fragment() {

  lateinit var innerView: View;
  lateinit var favAdpter: StoreFavAdapter;
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
    favAdpter = StoreFavAdapter(this);


    return innerView;
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)



    RecyViewSetup(recycler_view_store_list, favAdpter).build()




    refreshLoadmoreListener = RefreshLoadmoreListener(swipe_refresh_store_list, ::refreshData)




    refreshData();

  }


  fun refreshData() {

    api.getStores(26.333351598841787, 127.79896146273005, 100000000, 100)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe { works ->
        originStoreInfo = works;
        sortData();
      }

  }

  private fun sortData() {


    processedList = originStoreInfo;
    refreshUI();
  }

  private fun refreshUI() {
    refreshLoadmoreListener!!.stopRefresh()
    favAdpter.notifyDataSetChanged();
  }


}
