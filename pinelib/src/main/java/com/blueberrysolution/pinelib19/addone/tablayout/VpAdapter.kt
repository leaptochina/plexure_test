package com.blueberrysolution.pinelib19.addone.tablayout

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.blueberrysolution.pinelib19.activity.A
import com.blueberrysolution.pinelib19.addone.tablayout.TabDataBean

class VpAdapter: androidx.fragment.app.FragmentPagerAdapter {

    var pagerArrayList: ArrayList<TabDataBean> = ArrayList();

    constructor(fm: androidx.fragment.app.FragmentManager) : super(fm) {
    }

    override fun getItem(position: Int): androidx.fragment.app.Fragment {

        return pagerArrayList[position].view;
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
    }


    override fun getPageTitle(position: Int): CharSequence? {
        return pagerArrayList[position].title;
    }



    override fun getCount(): Int {
        return pagerArrayList.size;
    }



    fun addFragments(pagerArrayList: ArrayList<TabDataBean>) {
        this.pagerArrayList = pagerArrayList;

    }

}