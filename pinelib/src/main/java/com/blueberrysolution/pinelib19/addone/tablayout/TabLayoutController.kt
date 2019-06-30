package com.blueberrysolution.pinelib19.addone.tablayout

import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import com.blueberrysolution.pinelib19.activity.A


/*
        pagerArrayList.add(TabDataBean(HomeFragment(), A.s(R.string.main_activity_tab_home), R.drawable.a_location_icon));
        pagerArrayList.add(TabDataBean(HomeFragment(), A.s(R.string.main_activity_tab_search), R.drawable.a_search_icon));
        vpAdapter = VpAdapter(supportFragmentManager);

        tabLayoutController = TabLayoutController(mainTab, mainVp, pagerArrayList, vpAdapter)
 */
class TabLayoutController{
    var tabLayout: TabLayout
    var viewPager: androidx.viewpager.widget.ViewPager
    var data: ArrayList<TabDataBean>
    var vpAdapter: VpAdapter

    constructor(tabLayout: TabLayout, viewPager: androidx.viewpager.widget.ViewPager, data: ArrayList<TabDataBean>, vpAdapter: VpAdapter){

        this. tabLayout = tabLayout;
        this. viewPager = viewPager;
        this. data = data;
        this. vpAdapter = vpAdapter;

        initSetup();
    }

    fun initSetup(){
        tabLayout.setupWithViewPager(viewPager);


        vpAdapter.addFragments(data);
        viewPager.setAdapter(vpAdapter);



        for (i in 0 until tabLayout.getTabCount()) {
            tabLayout.getTabAt(i)!!.setIcon(data.get(i).icon);
        }
    }
}