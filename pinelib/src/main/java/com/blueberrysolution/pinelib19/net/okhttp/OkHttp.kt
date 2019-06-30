package com.blueberrysolution.pinelib19.net.okhttp

import android.view.View
import com.blueberrysolution.pinelib19.debug.G
import com.blueberrysolution.pinelib19.net.entry.Funcs
import java.lang.Exception
import okhttp3.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.io.IOException
import android.R.attr.keySet
import android.graphics.BitmapFactory
import android.widget.ImageView
import com.blueberrysolution.pinelib19.net.entry.Req


class OkHttp: Funcs {

    var client: OkHttpClient = OkHttpClient();
    var header:HashMap<String, String> = HashMap();

    override fun addHeader(key: String, value: String): Funcs{
        header.put(key, value)
        return  this;
    }

    override fun setImage(imageView: ImageView, url: String) {
        var req = Req();
        req.url = url;
        req.imageView = imageView;

        get(req);
    }

    override fun get(req: Req) {
        try {
            G.d(req.url)
            var requestDetail = Request.Builder().url(req.url)
            header.forEach{(key, value) -> requestDetail = requestDetail.addHeader(key, value) }
            var request = requestDetail.build();

            var call = client.newCall(request)
            call.enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    req.onError(e)
                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
                        req.onResponse(response)

                    }
                    else{
                        var e = Exception("HTTP Load Error" + req.url);
                        req.onError(e)
                    }
                }
            })

        }
        catch (exception: Exception){
            req.onError(exception);
        }
    }

    override fun getSourceCode(url: String, callback: (body: String) -> Unit, errorCallback: (e: Exception) -> Unit) {
        var req = Req();
        req.url = url;
        req.onHtml = callback;
        req.onError = errorCallback;

        get(req);
    }
    override fun getSourceCode(url: String, callback: (body: String) -> Unit) {
        getSourceCode(url, callback, ::onException)
    }

    override fun getSourceCode(url: String): String {

        try {
            var request = Request.Builder().url(url).build();
            var response = client.newCall(request).execute()
            return response.body().toString();
        }
        catch (exception: Exception){
            onException(exception);
            return "";
        }


    }

    fun onException(e: Exception){
        G.w("Http Exception: " + e.toString());
    }
}