package com.pine.prexuretest.activitys.store_fav.store_list

import android.location.Location
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import com.blueberrysolution.pinelib19.activity.I
import com.blueberrysolution.pinelib19.addone.broadcast.Broadcast
import com.blueberrysolution.pinelib19.addone.broadcast.OnBroadcast
import com.blueberrysolution.pinelib19.addone.broadcast.gps.GPS
import com.blueberrysolution.pinelib19.addone.broadcast.gps.OnGpsBroadcast
import com.blueberrysolution.pinelib19.addone.inject_replace.MyOnCheckedChangeListener
import com.blueberrysolution.pinelib19.addone.inject_replace.MyOnClickListener
import com.blueberrysolution.pinelib19.addone.inject_replace.MyOnItemClickListener
import com.blueberrysolution.pinelib19.addone.inject_replace.MyViewHolder
import com.blueberrysolution.pinelib19.view.recycler_view.MyRecyViewHolder
import com.blueberrysolution.pinelib19.view.recycler_view.RecyclerViewBaseAdapter
import com.pine.prexuretest.R
import com.pine.prexuretest.activitys.store_fav.StoreFavActivity
import com.pine.prexuretest.activitys.store_fav.store_fav.StoreFavFragment
import com.pine.prexuretest.activitys.store_fav.store_fav.StoreFavSharePreference
import com.pine.prexuretest.activitys.store_fav.store_fav.StoreFavViewHolder
import com.pine.prexuretest.beans.Store


class StoreFavAdapter(var upperFregment: StoreFavFragment): RecyclerViewBaseAdapter<StoreFavViewHolder>() {


  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreFavViewHolder {
    return MyRecyViewHolder.i<StoreFavViewHolder>(parent, R.layout.store_fav_adapter)
  }

  override fun getItemCount(): Int {

    return StoreFavSharePreference.i().favStores.count()

  }

  override fun onBindViewHolder(holder: StoreFavViewHolder, position: Int) {

    var data = StoreFavSharePreference.i().favStores[position];



    holder.address!!.text = data.address
    holder.featureList!!.text = data.featureList.toString();
    holder.name!!.text = data.name
    holder.delete!!.tag = data;
    holder.delete!!.setOnClickListener(MyOnClickListener(::onRemoveStore))
  }

  fun onRemoveStore(view: View) {
    var store = view!!.tag as Store;

    StoreFavSharePreference.i().remove(store);

    upperFregment.favAdpter.notifyDataSetChanged();
    upperFregment.storeFavActivity.storeListFragment!!.storeAdpter.notifyDataSetChanged();

  }
}

