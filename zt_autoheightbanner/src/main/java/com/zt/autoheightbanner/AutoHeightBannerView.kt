package com.zt.autoheightbanner

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView // 修复：导入 RecyclerView
import androidx.viewpager2.widget.ViewPager2

class AutoHeightBannerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private val viewPager2: ViewPager2 = ViewPager2(context)

    init {
        addView(viewPager2, LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT))
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            private var lastPosition = 0
            private var lastState = ViewPager2.SCROLL_STATE_IDLE
            private var lastOffset = 0f
            override fun onPageSelected(position: Int) {
                lastPosition = position
            }
            override fun onPageScrollStateChanged(state: Int) {
                if (state == ViewPager2.SCROLL_STATE_IDLE) {
                    post { updateHeight(lastPosition) }
                }
                lastState = state
            }
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                // 平滑高度插值
                val recyclerView = viewPager2.getChildAt(0) as? RecyclerView
                val vh1 = recyclerView?.findViewHolderForAdapterPosition(position)
                val vh2 = recyclerView?.findViewHolderForAdapterPosition(position + 1)
                val h1 = vh1?.itemView?.let {
                    it.measure(
                        MeasureSpec.makeMeasureSpec(recyclerView.width, MeasureSpec.EXACTLY),
                        MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
                    )
                    it.measuredHeight
                } ?: 0
                val h2 = vh2?.itemView?.let {
                    it.measure(
                        MeasureSpec.makeMeasureSpec(recyclerView.width, MeasureSpec.EXACTLY),
                        MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
                    )
                    it.measuredHeight
                } ?: h1
                val interpHeight = (h1 * (1 - positionOffset) + h2 * positionOffset).toInt()
                if (interpHeight > 0) {
                    val lp = viewPager2.layoutParams
                    lp.height = interpHeight
                    viewPager2.layoutParams = lp
                }
            }
        })
    }

    fun setAdapter(adapter: BannerAdapter) {
        viewPager2.adapter = adapter
        post { updateHeight(viewPager2.currentItem) }
    }

    private fun updateHeight(position: Int) {
        val recyclerView = viewPager2.getChildAt(0) as? RecyclerView
        val viewHolder = recyclerView?.findViewHolderForAdapterPosition(position)
        val itemView = viewHolder?.itemView
        if (itemView != null) {
            // 强制测量新页面内容高度
            itemView.measure(
                MeasureSpec.makeMeasureSpec(recyclerView.width, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
            )
            val targetHeight = itemView.measuredHeight
            if (targetHeight > 0) {
                val lp = viewPager2.layoutParams
                lp.height = targetHeight
                viewPager2.layoutParams = lp
                return
            }
        }
        // fallback: measure after layout
        viewPager2.post {
            val rv = viewPager2.getChildAt(0) as? RecyclerView
            val vh = rv?.findViewHolderForAdapterPosition(position)
            val iv = vh?.itemView
            if (iv != null) {
                iv.measure(
                    MeasureSpec.makeMeasureSpec(rv.width, MeasureSpec.EXACTLY),
                    MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
                )
                val h = iv.measuredHeight
                if (h > 0) {
                    val lp = viewPager2.layoutParams
                    lp.height = h
                    viewPager2.layoutParams = lp
                }
            }
        }
    }

    fun setCurrentItem(item: Int, smoothScroll: Boolean = true) {
        viewPager2.setCurrentItem(item, smoothScroll)
    }

    fun getViewPager2(): ViewPager2 = viewPager2
}
