package com.blueberrysolution.pinelib19.addone.gson

import android.view.View
import com.blueberrysolution.pinelib19.addone.inject_replace.MyViewHolder
import com.blueberrysolution.pinelib19.debug.G
import com.blueberrysolution.pinelib19.net.entry.N
import com.google.gson.Gson
import java.lang.Exception
import java.lang.reflect.Type

/**
 *
fun refreshData(){
    MyGson<BusInfo>().fromJson("http://", ::onBusResult, BusInfo::class.java)
}

fun onBusResult(response: BusInfo){

}
 */
class MyGson<T>{
    var url = "";
    val gson: Gson = Gson();
    lateinit var callback:  (retObj: T) -> Unit;
    var onError: (e: Exception) -> Unit = ::onNoCatchError
    lateinit var type: Type;

    fun  fromJson(url: String, callback: (retObj: T) -> Unit, type: Type){
        fromJson(url, callback, type, ::onNoCatchError)
    }

    fun  fromJson(url: String, callback: (retObj: T) -> Unit, type: Type, onError: (e: Exception) -> Unit = ::onNoCatchError){
        N.i.getSourceCode(url, ::onJsonResult, onError)
        this.url = url;
        this.callback = callback;
        this.type = type;
        this.onError = onError
    }

    fun onJsonResult(response: String){
        var t: T;
        try {
            t = gson.fromJson<T>(response, type)

        }
        catch (e: Exception){
            G.w("JsonConvert Err: " + response)
            onError(e);
            return;
        }
        callback(t)

    }

    fun onNetError(e: Exception){
        G.w("Network Error: " + e.toString())
        onError(e);

    }

    fun onNoCatchError(e: Exception){
        G.w("Error Happened during access " + url)
    }

}