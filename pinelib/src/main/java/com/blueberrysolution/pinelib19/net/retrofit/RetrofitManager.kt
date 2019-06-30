package com.blueberrysolution.pinelib19.net.retrofit

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import okhttp3.OkHttpClient
import java.lang.Exception


/**
 * 需要在APP中初始化
 * API初始化类
 */
final class RetrofitManager<T> {

    var hostUrl: String = "";

    var request0: T? = null
    var retrofit: Retrofit? = null


    constructor(hostUrl: String,  requests: Class<T>){
        this.hostUrl = hostUrl;
        mInstance = this;

        // 初始化okhttp
        val client = OkHttpClient.Builder()
            .build()

        // 初始化Retrofit
        retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(hostUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        request0 = retrofit!!.create(requests)

    }



     fun req(): T {
        return request0!!
    }


    companion object {

        private var mInstance: RetrofitManager<*>? = null

        fun i():  RetrofitManager<*>{
            if (mInstance == null) {
                throw Exception("Please Run RetrofitManager(??, ??) on APP");
            }
            return mInstance!!

        }



    }
}