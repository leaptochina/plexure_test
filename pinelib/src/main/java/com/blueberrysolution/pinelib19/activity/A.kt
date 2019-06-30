package com.blueberrysolution.pinelib19.activity

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import com.blueberrysolution.pinelib19.addone.mytimer.MyTimer
import android.view.LayoutInflater
import android.view.View


class A(){

    companion object {

        var activity: Activity? = null;
        var app: BaseApp? = null;

        fun app(app: BaseApp? = null): BaseApp{
            if (app != null){
                this.app = app;
            }
            return this.app!!;
        }


        fun a(activity: Activity? = null): Activity {
            if (activity != null){
                this.activity = activity;
            }
            return this.activity!!;
        }

        fun c(activity: Activity? = null): Context{
            if (activity != null){
                this.activity = activity;
            }
            return this.activity!!.applicationContext;
        }

        fun r(): Resources {
            return this.activity!!.resources;
        }


        //获取String信息
        fun s(id: Int): String{
            var r: String?;
            r = activity?.getString(id);

            return if (r == null) {""} else {r};
        }

        //装配View信息
        fun v(id: Int): View {
            val view = LayoutInflater.from(app!!.applicationContext).inflate(id,null)
            return view;
        }


    }

}