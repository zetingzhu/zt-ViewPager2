package com.zzt.myviewpager.act

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.zzt.myviewpager.adapter.MyVP1Adapter
import com.zzt.myviewpager.data.ImgObj
import com.zzt.viewpager2.R
import kotlin.math.log

/**
 * 高度自适应
 * @constructor
 */
class AutoHeightActivity : AppCompatActivity() {
    companion object {
        val TAG = AutoHeightActivity::class.java.simpleName

        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, AutoHeightActivity::class.java)
            context.startActivity(starter)
        }

        var arrayIds = arrayOf(
            R.drawable.i0, R.drawable.i1, R.drawable.i3, R.drawable.i4
        )

    }

    var vp2_auto: ViewPager2? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auto_height)
        initView()
    }

    private fun initView() {
        vp2_auto = findViewById(R.id.vp2_auto)
        val vpAdapter3 = MyVP1Adapter(listTestData())
        vp2_auto?.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL)

        vp2_auto?.setAdapter(vpAdapter3)

        var heightManager = ViewPager2MinHeightManager(vp2_auto)
        heightManager.attach()


//        vp2_auto?.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
//            override fun onPageSelected(position: Int) {
//                super.onPageSelected(position)
//                vp2_auto?.let { vp2 ->
//                    val findViewByPosition =
//                        (vp2[0] as RecyclerView).layoutManager?.findViewByPosition(position)
//                    Log.d(TAG, ">>> view:$findViewByPosition")
//                    updatePagerHeightForChild(findViewByPosition, vp2)
//                }
//            }
//        })
    }

    //计算fragment的高度并设置给viewPager
    private fun updatePagerHeightForChild(view: View?, pager: ViewPager2?) {
        if (view != null && pager != null) {
            view?.post {
                val wMeasureSpec =
                    View.MeasureSpec.makeMeasureSpec(view.width, View.MeasureSpec.EXACTLY)
                val hMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
                view.measure(wMeasureSpec, hMeasureSpec)

                Log.d(TAG, ">>> view height 2 :${view.measuredHeight} ")
                Log.d(TAG, ">>> view height 3 :${pager.layoutParams.height} ")

                if (pager.layoutParams.height != view.measuredHeight) {
                    pager.layoutParams = pager.layoutParams.also { lp ->
                        lp.height = view.measuredHeight
                    }
                }
            }
        }
    }

    fun listTestData(): List<ImgObj>? {
        val ints: MutableList<ImgObj> = ArrayList<ImgObj>()
        for (i in arrayIds.indices) {
            ints.add(ImgObj(arrayIds.get(i), "第" + (i + 1) + "个"))
        }
        return ints
    }
}