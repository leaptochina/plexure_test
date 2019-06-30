package com.blueberrysolution.pinelib19.addone.tablayout

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter

class TabDataBean{
    lateinit var view: androidx.fragment.app.Fragment;
    lateinit var title: String;
    var icon: Int = 0;

    constructor(view: androidx.fragment.app.Fragment, title: String, icon: Int = 0){
        this.view = view;
        this.title = title;
        this.icon = icon;
    }
}