package com.blueberrysolution.pinelib19.net.entry

import android.widget.ImageView
import java.lang.Exception

interface Funcs{
//    N.i.getSourceCode("http://....", ::onBusResult )
    fun setImage(imageView: ImageView, url: String)
    fun addHeader(key: String, value: String): Funcs

    fun getSourceCode(url: String, callback: (body: String) -> Unit)
    fun getSourceCode(url: String, callback: (body: String) -> Unit, errorCallback: (e: Exception) -> Unit)
    fun getSourceCode(url: String): String;

    fun get(req: Req)
}