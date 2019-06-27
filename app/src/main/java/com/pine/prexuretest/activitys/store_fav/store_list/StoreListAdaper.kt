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
import com.blueberrysolution.pinelib19.addone.inject_replace.MyOnClickListener
import com.blueberrysolution.pinelib19.addone.inject_replace.MyOnItemClickListener
import com.blueberrysolution.pinelib19.addone.inject_replace.MyViewHolder
import com.blueberrysolution.pinelib19.view.recycler_view.MyRecyViewHolder
import com.blueberrysolution.pinelib19.view.recycler_view.RecyclerViewBaseAdapter
import com.pine.prexuretest.R
import com.pine.prexuretest.activitys.store_fav.StoreFavActivity


class StoreListAdaper(var upperActivity: StoreListFragment): RecyclerViewBaseAdapter<StoreListViewHolder>() {



  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreListViewHolder {
    return MyRecyViewHolder.i<StoreListViewHolder>(parent, R.layout.store_list_adapter)
  }

  override fun getItemCount(): Int {

    return upperActivity.processedList.count();

  }

  override fun onBindViewHolder(holder: StoreListViewHolder, position: Int) {

    var data = upperActivity.processedList[position];


    holder.address!!.text = data.address
    holder.distance!!.text = (data.distance / 1000).toString() + " km";
    holder.featureList!!.text = data.featureList.toString();
    holder.name!!.text =  data.name



    //holder.itemView.tag = fav;
    //holder.itemView.setOnClickListener(MyOnClickListener(::onFavListClick))

  }


//  fun onFavListClick(v: View){
//
//    var fav = v.tag as Favorites
//
//    if (fav.isBus){
//      var routes_id = fav.route_id;
//      var stops_id = fav.stop_id
//      I.i(BusLocation::class).putExtra("routes_id", routes_id).putExtra("stop_sequence", stops_id).show();
//
//
//    }
//    else{
//
//      var stops_id = fav.stop_id
//      I.i(StopActivity::class).putExtra("stops_id", stops_id).show();
//    }
//  }
}