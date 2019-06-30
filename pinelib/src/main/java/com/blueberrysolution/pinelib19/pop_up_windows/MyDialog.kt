package com.blueberrysolution.pinelib19.pop_up_windows

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface

abstract class MyDialog : Dialog {

    constructor (context: Context):  super(context) {

    }

    constructor(context: Context, themeResId: Int): super(context, themeResId) {

    }




}