package com.blueberrysolution.pinelib19.pop_up_windows.msgbox.ui


import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface


abstract class AbsAlertDialog : AlertDialog {


    abstract val view: AbsAlertDialog

    protected constructor(context: Context, theme: Int) : super(context, theme) {
        // TODO Auto-generated constructor stub
    }


    protected constructor(context: Context) : super(context) {
        // TODO Auto-generated constructor stub
    }


    protected constructor(
        context: Context, cancelable: Boolean,
        cancelListener: DialogInterface.OnCancelListener
    ) : super(context, cancelable, cancelListener) {
        // TODO Auto-generated constructor stub
    }
}
