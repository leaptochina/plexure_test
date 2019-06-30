package com.blueberrysolution.pinelib19.pop_up_windows.msgbox

import java.util.ArrayList


import android.content.Context
import android.content.DialogInterface
import android.content.DialogInterface.OnDismissListener
import android.content.DialogInterface.OnKeyListener
import android.view.KeyEvent
import android.view.View
import android.view.View.OnClickListener
import android.widget.TextView
import com.blueberrysolution.pinelib19.R
import com.blueberrysolution.pinelib19.activity.A
import com.blueberrysolution.pinelib19.debug.G
import com.blueberrysolution.pinelib19.pop_up_windows.msgbox.ui.AbsAlertDialog
import com.blueberrysolution.pinelib19.pop_up_windows.msgbox.ui.LargeMsgDialog
import com.blueberrysolution.pinelib19.pop_up_windows.msgbox.ui.SelectDialog


/**
 * 消息框
 *
 * <pre>
 *
</pre> *
 */
class MessageBox private constructor(private val context: Context?) : OnClickListener, OnKeyListener,
    OnDismissListener {
    private var listener: OnMessageClickListener? = null

    private var mainMassage: TextView? = null
    private var btn1: TextView? = null
    private var btn2: TextView? = null
    private var btn3: TextView? = null
    private var btn4: TextView? = null
    private var dialog: AbsAlertDialog? = null
    private var cancelable: Boolean = false
    private var style = WindowsStyle.BlackNoFrame
    var message: String? = null
    var buttons: List<String>? = null
    /**
     * 状态说明 1 ： 允许添加一个 2 ： 阻止所有添加请求 3 ： 自由添加
     */
    private var enableAddLock = 3


    /**
     * 设置对话框是否能够被取消
     *
     * @param cancelable
     * @return
     */
    fun setCancelable(cancelable: Boolean): MessageBox {
        this.cancelable = cancelable
        if (dialog != null) {
            dialog!!.setCancelable(cancelable)
        }
        return this
    }


    init {

    }


    /**
     * 通过这里显示提示框
     *
     * @param message
     * @param buttons
     */
    @JvmOverloads
    fun show(message: String, btn: String = "确定") {
        val list = ArrayList<String>()
        list.add(btn)
        show(message, list)
    }


    /**
     * 通过这里显示提示框
     *
     * @param message
     * @param buttons
     */
    fun show(message: String, btn1: String, btn2: String) {
        val list = ArrayList<String>()
        list.add(btn1)
        list.add(btn2)
        show(message, list)
    }


    /**
     * 通过这里显示提示框
     *
     * @param message
     * @param buttons
     */
    fun show(message: String, btn1: String, btn2: String, btn3: String) {
        val list = ArrayList<String>()
        list.add(btn1)
        list.add(btn2)
        list.add(btn3)
        show(message, list)
    }


    /**
     * 通过这里显示提示框
     *
     * @param message
     * @param buttons
     */
    fun show(
        message: String, btn1: String, btn2: String, btn3: String,
        btn4: String
    ) {
        val list = ArrayList<String>()
        list.add(btn1)
        list.add(btn2)
        list.add(btn3)
        list.add(btn4)
        show(message, list)
    }


    fun getStyle(): WindowsStyle {
        return style
    }


    fun setStyle(style: WindowsStyle): MessageBox {
        this.style = style
        return this
    }


    // 清理前端窗口
    fun dismiss() {
        isShowing = false
        queue.showNext()
        enableAddLock = 3

        if (dialog != null) {
            dialog!!.cancel()
            dialog = null
        }
    }


    /**
     * 通过这里显示提示框
     *
     * @param message
     * @param buttons
     */
    fun show(message: String, buttons: List<String>) {
        this.message = message
        this.buttons = buttons
        /**
         * 状态说明 1 ： 允许添加一个 2 ： 阻止所有添加请求 3 ： 自由添加
         */
        if (enableAddLock == 3) {
            queue.getQueue().add(this)
            queue.showNext()
        } else if (enableAddLock == 1) {
            enableAddLock = 2
            queue.getQueue().add(this)
            queue.showNext()
        }
    }


    /**
     * 本函数调用后，将会清理前端所有消息窗口，并且锁定MessageBox队列 直到本窗口结束，任何添加消息窗体操作将会直接被拒绝
     */
    fun cleanMessageQueueAndLock(): MessageBox {
        // 清空消息队列
        queue.getQueue().clear()
        // 清理前端窗口
        dismiss()
        // 锁定后续添加
        enableAddLock = 1

        return this
    }


    /**
     * 本函数运行后，如果最上层有其他弹出窗口，本次MessageBox将不会被压入消息队列，不会显示
     *
     * <pre>
     * 函数 cleanMessageQueueAndLock() 与 hideIfShowing() 互斥
     * 只能调用一个 请注意
    </pre> *
     *
     * @return
     */
    fun hideIfShowing(): MessageBox {
        // 锁定后续添加
        if (enableAddLock != 2) {
            enableAddLock = 4
        }
        return this
    }


    /**
     * 显示对话框
     *
     * <pre>
     * 立刻显示对话框
     * 本操作将会跳过消息队列 直接将对话框显示在屏幕上
     * 本操作会阻止消息队列中其他对话框的创建，在本对话框消失后其他对话框才会显示
     * 但是正在显示的对话框不会消失
    </pre> *
     *
     * @param message
     * @param buttons
     */
    fun showNow(message: String?, buttons: List<String>?) {
        if (message == null) {
            G.e("提示信息不能为空")
            return
        }
        if (buttons == null) {
            G.e("按钮信息不能为null")
            return
        }
        if (buttons.size == 0) {
            G.e("按钮信息不能为0项")
            return
        }
        if (context == null) {
            G.e("消息框无法显示 - 上下文失效，将要显示的消息: $message")
            return
        }

        // 窗体风格设置
        if (style === WindowsStyle.BlackNoFrame) {
            dialog = SelectDialog(context, R.style.dialog)
        } else if (style === WindowsStyle.LargeMsgBox) {
            dialog = LargeMsgDialog(context, R.style.dialog)
        }

        dialog!!.setCanceledOnTouchOutside(cancelable)// 设置点击Dialog外部任意区域关闭Dialog
        dialog!!.show()
        dialog!!.setOnDismissListener(this)
        isShowing = true

        dialog!!.setOnKeyListener(this)

        val view = dialog!!.view

        mainMassage = view.findViewById(R.id.mainMassage)
        btn1 = view.findViewById(R.id.btn1)
        btn2 = view.findViewById(R.id.btn2)
        btn3 = view.findViewById(R.id.btn3)
        btn4 = view.findViewById(R.id.btn4)

        mainMassage!!.text = message

        btn1!!.visibility = View.VISIBLE
        btn2!!.visibility = View.GONE
        btn3!!.visibility = View.GONE
        btn4!!.visibility = View.GONE

        btn1!!.text = buttons[0]
        if (buttons.size >= 2) {
            btn2!!.text = buttons[1]
            btn2!!.visibility = View.VISIBLE
        }
        if (buttons.size >= 3) {
            btn3!!.text = buttons[2]
            btn3!!.visibility = View.VISIBLE
        }
        if (buttons.size >= 4) {
            btn4!!.text = buttons[3]
            btn4!!.visibility = View.VISIBLE
        }

        btn1!!.setOnClickListener(this)
        btn2!!.setOnClickListener(this)
        btn3!!.setOnClickListener(this)
        btn4!!.setOnClickListener(this)
    }


    fun getListener(): OnMessageClickListener? {
        return listener
    }


    fun setListener(listener: OnMessageClickListener): MessageBox {
        this.listener = listener
        return this
    }


    override fun onClick(v: View) {
        dismiss()
        if (listener != null) {
            if (v.id == R.id.btn1) {
                listener!!.messageBoxChoose(1)
            } else if (v.id == R.id.btn2) {
                listener!!.messageBoxChoose(2)
            } else if (v.id == R.id.btn3) {
                listener!!.messageBoxChoose(3)
            } else if (v.id == R.id.btn4) {
                listener!!.messageBoxChoose(4)
            }
        }
    }


    override fun onKey(arg0: DialogInterface, keyCode: Int, arg2: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (cancelable == false) {
                return true
            } else {
                dismiss()
            }

        }

        return false
    }


    /**
     * 窗口消失事件 - 主动回调
     */
    override fun onDismiss(dialog: DialogInterface) {
        dismiss()

    }

    companion object {
        private val queue = MessageBoxQueue()
        var isShowing: Boolean = false


        /**
         * 初始化
         *
         * <pre>
         * 必须将基础activity继承QFActivity/QFFragmentActivity
         * 才能用这个初始化
         * 否则 请传入上下文
        </pre> *
         *
         * @return
         */
        fun i(): MessageBox {
            return MessageBox(A.a())
        }


        fun i(context: Context): MessageBox {
            return MessageBox(context)
        }
    }

}
