package com.pine.prexuretest.activitys.store_fav.store_fav

import com.blueberrysolution.pinelib19.addone.share_preferences.Sp
import com.google.gson.Gson
import com.pine.prexuretest.beans.Store

class StoreFavSharePreference() {
  var favStores: ArrayList<Store> = ArrayList<Store>();

  init {

    var favs = Sp.i.get("favs", "");
    if (!favs.equals("")) {
      var list = Gson().fromJson<Array<Store>>(favs, Array<Store>::class.java).toMutableList()


      favStores.clear()
      favStores.addAll(list)

    }
  }

  fun add(store: Store) {
    favStores.add(store);
    save();
  }

  fun remove(store: Store) {

    favStores.remove(store);

    save();
  }

  fun save() {
    var favs = Gson().toJson(favStores);

    Sp.i.put("favs", favs);

  }


  companion object {
    var storeFavSharePreference: StoreFavSharePreference? = null;
    fun i(): StoreFavSharePreference {
      if (storeFavSharePreference == null) {
        storeFavSharePreference = StoreFavSharePreference();
      }
      return storeFavSharePreference!!;
    }
  }
}