package com.blueberrysolution.pinelib19.addone.inject_replace

import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View


class OnTextChangeListener(onTextChange: (s: CharSequence?, start: Int, before: Int, count: Int) -> Unit): TextWatcher{

    val onTextChange: (s: CharSequence?, start: Int, before: Int, count: Int) -> Unit


    override fun afterTextChanged(s: Editable?) {

    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        onTextChange(s, start, before, count)
    }







    init{
        this.onTextChange = onTextChange;
    }



}