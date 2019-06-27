package com.pine.prexuretest.retrofit


import com.pine.prexuretest.configs.Configs
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitManager {
  var retrofit: Retrofit? = null;


  private constructor() {
    retrofit = Retrofit.Builder()
      .baseUrl(Configs.SERVER_URL)
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .build();
  }


  companion object {
    var retrofitManager: RetrofitManager? = null;

    fun i(): Retrofit {
      if (retrofitManager == null) {
        retrofitManager = RetrofitManager()
      }
      return retrofitManager!!.retrofit!!;
    }
  }

}