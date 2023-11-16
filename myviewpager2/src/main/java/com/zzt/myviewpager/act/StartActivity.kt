package com.zzt.myviewpager.act

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.zzt.adapter.StartActivityRecyclerAdapter
import com.zzt.entity.StartActivityDao
import com.zzt.myviewpager.ActivityViewPager
import com.zzt.viewpager2.R

class StartActivity : AppCompatActivity() {
    var rv_list: RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        initView()
    }

    private fun initView() {
        rv_list = findViewById(R.id.rv_list)

        val mListDialog: MutableList<StartActivityDao> = ArrayList()
        mListDialog.add(StartActivityDao("之前老的页面合集", " ", "0"))
        mListDialog.add(StartActivityDao("处理自动变高", " ", "1"))


        StartActivityRecyclerAdapter.setAdapterData(
            rv_list,
            RecyclerView.VERTICAL,
            mListDialog
        ) { itemView: View?, position: Int, data: StartActivityDao ->
            when (data.arouter) {
                "0" -> {
                    ActivityViewPager.start(this@StartActivity)
                }

                "1" -> {
                    AutoHeightActivity.start(this@StartActivity)
                }
            }
        }
    }
}