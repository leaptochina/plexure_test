package com.blueberrysolution.pinelib19.pop_up_windows.msgbox

import com.blueberrysolution.pinelib19.debug.G
import java.util.ArrayList


/**
 * 提示框消息队列 保证同一时刻 只有一个消息框在显示
 */
class MessageBoxQueue {
    private var queue: ArrayList<MessageBox> = ArrayList()

    fun getQueue(): ArrayList<MessageBox> {
        return queue
    }

    fun setQueue(queue: ArrayList<MessageBox>) {
        this.queue = queue
    }

    @Synchronized
    fun showNext() {
        if (!MessageBox.isShowing) {
            if (queue.size > 0) {
                val mBox = queue[0]
                try {
                    mBox.showNow(mBox.message, mBox.buttons)
                } catch (e: Exception) {
                    G.d("Msgbox显示错误 - $e")
                }

                queue.remove(mBox)
            }
        }
    }


}
