package com.blueberrysolution.pinelib19.pop_up_windows.waitting

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import com.blueberrysolution.pinelib19.R
import com.blueberrysolution.pinelib19.activity.A
import com.blueberrysolution.pinelib19.addone.broadcast.Broadcast
import com.blueberrysolution.pinelib19.addone.inject_replace.MyOnItemClickListener
import com.blueberrysolution.pinelib19.pop_up_windows.MyDialog
import kotlinx.android.synthetic.main.waitting_dialog.*

open class Waitting: MyDialog {


    constructor ():  super(A.a()) {
    }

    constructor(context: Context, themeResId: Int): super(context, themeResId) {
    }

}