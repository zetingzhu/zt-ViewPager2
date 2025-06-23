package com.zt.autoheightbanner

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.zt_autoheightbanner.R

class DemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo_banner)

        val bannerView = findViewById<AutoHeightBannerView>(R.id.autoHeightBannerView)
        // 示例图标资源（请替换为你项目中的真实 drawable 资源）
        val page1Icons = listOf(
            R.drawable.icon1, R.drawable.icon2, R.drawable.icon3, R.drawable.icon4,
            R.drawable.icon5, R.drawable.icon6, R.drawable.icon7, R.drawable.icon8,
            R.drawable.icon9, R.drawable.icon10, R.drawable.icon11, R.drawable.icon12,
            R.drawable.icon13, R.drawable.icon14, R.drawable.icon15, R.drawable.icon16
        )
        val page2Icons = listOf(
            R.drawable.icon17, R.drawable.icon18, R.drawable.icon19, R.drawable.icon20
        )
        val page3Icons = listOf(
            R.drawable.icon1, R.drawable.icon2, R.drawable.icon3, R.drawable.icon4,
            R.drawable.icon5, R.drawable.icon6, R.drawable.icon7, R.drawable.icon8,
        )
        val pages = listOf(
            BannerAdapter.BannerPageData(0, page1Icons),
            BannerAdapter.BannerPageData(1, page2Icons),
            BannerAdapter.BannerPageData(2, page3Icons)
        )
        bannerView.setAdapter(BannerAdapter(pages))
    }
}
