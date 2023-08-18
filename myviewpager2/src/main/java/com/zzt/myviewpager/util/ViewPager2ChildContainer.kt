package com.zzt.myviewpager.util

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.RelativeLayout
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs

/**
 * @author: zeting
 * @date: 2021/11/24
 * 写一个在 viewpager2 下面处理左右滑动灵敏
 */
class ViewPager2ChildContainer : RelativeLayout {
    val TAG: String = ViewPager2ChildContainer::class.java.simpleName

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(context, attrs, defStyleAttr)

    private var startX = 0
    private var startY = 0

    //循环遍历找到viewPager2
    private val parentViewPager: ViewPager2?
        get() {
            var v: View? = parent as? View
            while (v != null && v !is ViewPager2) {
                v = v.parent as? View
            }
            v?.let {
                Log.d(TAG, "确定已经找到了父类的viewpager:${v::class.java.simpleName} ")
            }
            return v as? ViewPager2
        }

    override fun onFinishInflate() {
        super.onFinishInflate()
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                startX = ev.x.toInt()
                startY = ev.y.toInt()
                parentViewPager?.isUserInputEnabled = false
            }
            MotionEvent.ACTION_MOVE -> {
                val endX = ev.x.toInt()
                val endY = ev.y.toInt()
                val disX = abs(endX - startX)
                val disY = abs(endY - startY)
                onActionMove(disX, disY)
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                parentViewPager?.isUserInputEnabled = true
            }
        }
        return super.onInterceptTouchEvent(ev)
    }

    private fun onActionMove(disX: Int, disY: Int) {
        Log.d(TAG, "滑动距离 $disY , $disX   ")
        if (disY > disX) {
            parentViewPager?.isUserInputEnabled = false
        } else if (disX > (disY * 5).coerceAtLeast(50)) {
            parentViewPager?.isUserInputEnabled = true
        }
    }

}
