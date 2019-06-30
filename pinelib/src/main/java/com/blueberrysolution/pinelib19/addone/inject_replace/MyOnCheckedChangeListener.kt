package com.blueberrysolution.pinelib19.addone.inject_replace

import android.view.View
import android.widget.CompoundButton
import android.widget.RadioGroup
import com.blueberrysolution.pinelib19.debug.G

/**
 * 用法
 * holder.switch_fav!!.setOnCheckedChangeListener(MyOnCheckedChangeListener(::onAddOrRemoveFavClick))
 */

class MyOnCheckedChangeListener(onChangeFun: (buttonView: CompoundButton?, isChecked: Boolean) -> Unit):
  CompoundButton.OnCheckedChangeListener {


  val onChangeFun: (buttonView: CompoundButton?, isChecked: Boolean) -> Unit

  override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
    if (buttonView != null) {
      onChangeFun(buttonView, isChecked)
    }else{
      G.e("MyOnClickListener： Null Point Exception! ")
    }
  }




  init{
    this.onChangeFun = onChangeFun;
  }



}