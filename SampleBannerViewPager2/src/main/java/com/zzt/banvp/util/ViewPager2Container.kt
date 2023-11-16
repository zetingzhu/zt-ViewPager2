package com.zzt.banvp.util

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout
import android.widget.RelativeLayout
import androidx.viewpager2.widget.ViewPager2

/**
 * @author: zeting
 * @date: 2020/12/30
 * RecycleView 嵌套 Viewpager2
 */
class ViewPager2Container : FrameLayout {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(
        context, attrs, defStyleAttr
    )

    private var mViewPager2: ViewPager2? = null
    private var startX = 0
    private var startY = 0

    override fun onFinishInflate() {
        super.onFinishInflate()
        for (i in 0 until childCount) {
            val childView = getChildAt(i)
            if (childView is ViewPager2) {
                mViewPager2 = childView
                break
            }
        }
        if (mViewPager2 == null) {
            throw IllegalStateException("The root child of ViewPager2Container must contains a ViewPager2")
        }
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        val doNotNeedIntercept =
            ((mViewPager2?.isUserInputEnabled == false || (mViewPager2?.adapter != null && (mViewPager2?.adapter?.itemCount
                ?: 0) <= 1)))
        if (doNotNeedIntercept) {
            return super.onInterceptTouchEvent(ev)
        }
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                startX = ev.x.toInt()
                startY = ev.y.toInt()
                // 禁止父类滚动
                parent.requestDisallowInterceptTouchEvent(true)
            }

            MotionEvent.ACTION_MOVE -> {
                val endX = ev.x.toInt()
                val endY = ev.y.toInt()
                val disX = Math.abs(endX - startX)
                val disY = Math.abs(endY - startY)
                if (mViewPager2?.orientation == ViewPager2.ORIENTATION_VERTICAL) {
                    onVerticalActionMove(endY, disX, disY)
                } else if (mViewPager2?.orientation == ViewPager2.ORIENTATION_HORIZONTAL) {
                    onHorizontalActionMove(endX, disX, disY)
                }
            }

            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                // 允许所有父类拦截
                parent.requestDisallowInterceptTouchEvent(false)
            }
        }
        return super.onInterceptTouchEvent(ev)
    }

    private fun onHorizontalActionMove(endX: Int, disX: Int, disY: Int) {
        if (mViewPager2?.adapter == null) {
            return
        }
        if (disX > disY) {
            val currentItem = mViewPager2?.currentItem
            val itemCount = mViewPager2?.adapter?.itemCount ?: 0
            if (currentItem == 0 && endX - startX > 0) {
                parent.requestDisallowInterceptTouchEvent(false)
            } else {
                parent.requestDisallowInterceptTouchEvent(
                    currentItem != itemCount - 1 || endX - startX >= 0
                )
            }
        } else if (disY > disX) {
            parent.requestDisallowInterceptTouchEvent(false)
        }
    }

    private fun onVerticalActionMove(endY: Int, disX: Int, disY: Int) {
        if (mViewPager2?.adapter == null) {
            return
        }
        val currentItem = mViewPager2?.currentItem
        val itemCount = mViewPager2?.adapter?.itemCount ?: 0
        if (disY > disX) {
            if (currentItem == 0 && endY - startY > 0) {
                parent.requestDisallowInterceptTouchEvent(false)
            } else {
                parent.requestDisallowInterceptTouchEvent(
                    currentItem != itemCount - 1 || endY - startY >= 0
                )
            }
        } else if (disX > disY) {
            parent.requestDisallowInterceptTouchEvent(false)
        }
    }

}
