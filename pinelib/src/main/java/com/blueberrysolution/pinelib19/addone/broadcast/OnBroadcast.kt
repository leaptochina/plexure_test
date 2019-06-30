package com.blueberrysolution.pinelib19.addone.broadcast

interface OnBroadcast{
    fun onBroadcast(key: String, withObject: Any?);
}