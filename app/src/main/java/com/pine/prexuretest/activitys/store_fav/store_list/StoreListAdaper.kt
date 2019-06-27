package com.pine.prexuretest.activitys.store_fav.store_list

import android.graphics.Color
import android.location.Location
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.CompoundButton
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
import com.pine.prexuretest.activitys.store_fav.store_fav.StoreFavSharePreference
import com.pine.prexuretest.beans.Store
import com.pine.prexuretest.configs.Configs
import kotlinx.android.synthetic.main.store_list_adapter.view.*


class StoreListAdaper(var upperFregment: StoreListFragment) :
  RecyclerViewBaseAdapter<StoreListViewHolder>() {


  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreListViewHolder {
    return MyRecyViewHolder.i<StoreListViewHolder>(parent, R.layout.store_list_adapter)
  }

  override fun getItemCount(): Int {

    return upperFregment.processedList.count();

  }

  override fun onBindViewHolder(holder: StoreListViewHolder, position: Int) {

    holder.switch_fav!!.setOnCheckedChangeListener(null)

    var data = upperFregment.processedList[position];

    if (upperFregment.isShowAddress) {
      holder.address!!.text = data.address
    } else {
      holder.address!!.text = ""
    }

    holder.distance!!.text = (data.distance / 1000).toString() + " km";
    holder.feature_list!!.text = data.featureList.toString();
    holder.name!!.text = data.name
    holder.switch_fav!!.isChecked = false

    //Change backgrand Gray if distance is too far
    if (data.distance <= Configs.CANNOT_FAV_DISTANCE) {
      holder.switch_fav!!.isEnabled = true;
      holder.itemView.alpha = 1f;
      holder.itemView.setBackgroundColor(Color.TRANSPARENT);

    } else {
      holder.switch_fav!!.isEnabled = false;
      holder.itemView.alpha = 0.75f;
      holder.itemView.setBackgroundColor(Color.GRAY);
    }

    for (favs in StoreFavSharePreference.i().favStores) {
      if (favs.id == data.id) {
        holder.switch_fav!!.isChecked = true;
        break;
      }
    }


    holder.switch_fav!!.tag = data;
    holder.switch_fav!!.setOnCheckedChangeListener(MyOnCheckedChangeListener(::onAddOrRemoveFavClick))

  }

  fun onAddOrRemoveFavClick(buttonView: CompoundButton?, isChecked: Boolean) {
    var store = buttonView!!.tag as Store;

    if (isChecked) {
      StoreFavSharePreference.i().add(store);
    } else {
      StoreFavSharePreference.i().remove(store);
    }


    upperFregment.storeFavActivity.storeFavFragment!!.favAdpter.notifyDataSetChanged();
  }
}
