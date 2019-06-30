package com.blueberrysolution.pinelib19.net.entry

import android.graphics.BitmapFactory
import android.widget.ImageView
import com.blueberrysolution.pinelib19.debug.G
import okhttp3.Response
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.lang.Exception

class Req{
    var imageView: ImageView? = null;
    var url: String = "";
    var onResponse: (response: Response) -> Unit = ::onResponseF
    var onHtml: (body: String) -> Unit = ::onHtmlF
    var onError: (e: Exception) -> Unit = ::onErrorF
    var onByte: (b: ByteArray) -> Unit = ::onByteF

    fun onResponseF(response: Response){
        if (onHtml != ::onHtmlF){
            var res = response.body()!!.string()
            doAsync {
                uiThread {
                    onHtml(res)
                }
            }
        }
        else{
            var bt = response.body()!!.bytes();
            doAsync {
                uiThread {
                    if (onByte != ::onByteF){
                        onByte(bt)
                    }


                    if (imageView != null) {
                        var bitmap = BitmapFactory.decodeByteArray(bt, 0, bt.size);
                        //通过imageview，设置图片
                        imageView!!.setImageBitmap(bitmap);
                    }
                }
            }
        }


    }

    fun onByteF(b: ByteArray){

    }

    fun onHtmlF(body: String){

    }


    fun onErrorF(e: Exception){
        G.w("Http Exception: " + e.toString());
    }

}