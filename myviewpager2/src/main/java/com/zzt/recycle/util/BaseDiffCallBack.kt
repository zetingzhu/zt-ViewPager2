package com.zzt.recycle.util

import android.text.TextUtils
import androidx.recyclerview.widget.DiffUtil

class BaseDiffCallBack(private val oldData: List<*>?, private val newData: List<*>?) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldData?.size ?: 0
    }

    override fun getNewListSize(): Int {
        return newData?.size ?: 0
    }

    override fun areItemsTheSame(p0: Int, p1: Int): Boolean {
        val object1 = oldData?.get(p0)
        val object2 = newData?.get(p1)
        if (object1 == null || object2 == null)
            return false
        return if (object1 is IDifference && object2 is IDifference) {
            TextUtils.equals(object1.uniqueId, object2.uniqueId)
        } else {
            object1 == object2
        }
    }


    override fun areContentsTheSame(p0: Int, p1: Int): Boolean {
        val object1 = oldData?.get(p0)
        val object2 = newData?.get(p1)
        return if (object1 is IEqualsAdapter && object2 is IEqualsAdapter) {
            object1 == object2
        } else {
            true
        }
    }

}