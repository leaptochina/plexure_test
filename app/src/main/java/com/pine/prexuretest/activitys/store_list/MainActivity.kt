package com.pine.prexuretest.activitys.store_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.blueberrysolution.pinelib19.activity.PineActivity
import com.blueberrysolution.pinelib19.debug.G
import com.pine.prexuretest.R
import com.pine.prexuretest.retrofit.Requests
import com.pine.prexuretest.retrofit.RetrofitManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : PineActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    var api = RetrofitManager.i().create(Requests::class.java)

    api.getStores(26.333351598841787, 127.79896146273005, 100000000, 100)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({ works ->
          G.d(works.toString())

        })


  }
}
