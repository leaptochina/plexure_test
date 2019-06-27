package com.pine.prexuretest.retrofit

import com.pine.prexuretest.beans.Store
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Requests {

  @GET("/store/v2/stores")
  fun getStores(@Query("latitude") latitude: Double, @Query("longitude") longitude: Double, @Query("radius") radius: Int, @Query("size") size: Int): Observable<ArrayList<Store>>


}