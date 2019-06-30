package com.blueberrysolution.pinelib19.view.listview

import android.content.Context
import android.util.AttributeSet
import android.view.View.MeasureSpec
import android.widget.ListView


class ListViewForScrollView : ListView {
    constructor(context: Context) : super(context) {}
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}
    constructor(
        context: Context, attrs: AttributeSet,
        defStyle: Int
    ) : super(context, attrs, defStyle) {
    }


            /**
             * 重写该方法，达到使ListView适应ScrollView的效果
             */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val expandSpec = MeasureSpec.makeMeasureSpec(
            Integer.MAX_VALUE shr 2,
            MeasureSpec.AT_MOST
        )
        super.onMeasure(widthMeasureSpec, expandSpec)
    }
}