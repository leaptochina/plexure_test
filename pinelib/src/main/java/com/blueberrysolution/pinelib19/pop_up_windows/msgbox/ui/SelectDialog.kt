package com.blueberrysolution.pinelib19.pop_up_windows.msgbox.ui


import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import com.blueberrysolution.pinelib19.R


class SelectDialog : AbsAlertDialog {

    override val view: SelectDialog
        get() = this

    constructor(context: Context, theme: Int) : super(context, theme) {}


    constructor(context: Context) : super(context) {}


    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.msgbox_def)


    }

}
