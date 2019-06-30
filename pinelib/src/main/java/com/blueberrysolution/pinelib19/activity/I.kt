package com.blueberrysolution.pinelib19.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import java.lang.Exception
import kotlin.reflect.KClass

class I(cls: KClass<*>){

    var intent: Intent = Intent();

    init{
        intent.setClass(A.a(), cls.java);
    }



    fun show(): I{
        try {
            A.a().startActivity(intent);
        }
        catch (e: Exception){
            T.t("Please Add Activity to Manifests")
        }
        return  this;



    }




    fun putExtra(name: String, value: String): I{
        intent.putExtra(name, value);
        return this
    }
    fun putExtra(name: String, value: Int): I{
        intent.putExtra(name, value);
        return this
    }
    fun putExtra(name: String, value: Double): I{
        intent.putExtra(name, value);
        return this
    }
    fun putExtra(name: String, value: Boolean): I{
        intent.putExtra(name, value);
        return this
    }







    companion object {


        fun i(className: KClass<*>): I {
            return  I(className);
        }


    }

}

