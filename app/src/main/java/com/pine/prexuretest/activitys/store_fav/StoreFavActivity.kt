package com.pine.prexuretest.activitys.store_fav

import android.os.Bundle
import com.blueberrysolution.pinelib19.activity.A
import com.blueberrysolution.pinelib19.activity.PineActivity
import com.blueberrysolution.pinelib19.addone.broadcast.Broadcast
import com.blueberrysolution.pinelib19.addone.broadcast.OnBroadcast
import com.blueberrysolution.pinelib19.addone.broadcast.gps.OnGpsBroadcast
import com.blueberrysolution.pinelib19.addone.tablayout.TabDataBean
import com.blueberrysolution.pinelib19.addone.tablayout.TabLayoutController
import com.blueberrysolution.pinelib19.addone.tablayout.VpAdapter
import com.blueberrysolution.pinelib19.debug.G
import com.blueberrysolution.pinelib19.pop_up_windows.msgbox.MessageBox
import com.blueberrysolution.pinelib19.pop_up_windows.msgbox.OnMessageClickListener
import com.blueberrysolution.pinelib19.view.recycler_view.RefreshLoadmoreListener
import com.pine.prexuretest.R
import com.pine.prexuretest.activitys.store_fav.store_fav.StoreFavFragment
import com.pine.prexuretest.activitys.store_fav.store_list.StoreListFragment
import com.pine.prexuretest.retrofit.Requests
import com.pine.prexuretest.retrofit.RetrofitManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_store_fav.*

class StoreFavActivity : PineActivity(), OnBroadcast, OnMessageClickListener {


  var vpAdapter: VpAdapter? = null;
  var tabLayoutController: TabLayoutController? = null;


  var pagerArrayList: ArrayList<TabDataBean> = ArrayList();

  var storeListFragment: StoreListFragment? = null;
  var storeFavFragment: StoreFavFragment? = null;

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_store_fav)

    initViewPage();

    Broadcast.i.reg("LocationPermissions", this)

    OnGpsBroadcast.i();
  }

  private fun initViewPage() {
    storeListFragment = StoreListFragment(this);
    storeFavFragment = StoreFavFragment(this);

    pagerArrayList.add(
      TabDataBean(
        storeListFragment!!,
        "Stores",
        R.drawable.abc_ratingbar_material
      )
    );
    pagerArrayList.add(
      TabDataBean(
        storeFavFragment!!,
        "Favs",
        R.drawable.abc_ratingbar_material
      )
    );
    vpAdapter = VpAdapter(supportFragmentManager);

    tabLayoutController =
      TabLayoutController(tab_stort_fav, viewpage_store_fav, pagerArrayList, vpAdapter!!)



  }

  override fun onBroadcast(key: String, withObject: Any?) {
    if (key.equals("LocationPermissions")){
      var isGranted = withObject as Boolean
      if (!isGranted){
        MessageBox.i().setListener(this).show("Premission Error", "Try Again", "Ignore");
      }

    }
  }
  override fun messageBoxChoose(id: Int) {
    if (id == 1){
      OnGpsBroadcast.i().showGPSContacts()
    }
  }


}
