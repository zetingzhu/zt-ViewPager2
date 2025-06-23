package com.zt.autoheightbanner

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.zt_autoheightbanner.R

class BannerAdapter(private val pages: List<BannerPageData>) :
    RecyclerView.Adapter<BannerAdapter.BannerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val layoutId = when (viewType) {
            0 -> R.layout.item_banner_grid
            1 -> R.layout.item_banner_row
            else -> R.layout.item_banner_grid
        }
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return BannerViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        return pages[position].type
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        val data = pages[position]
        val recyclerView = holder.itemView.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView?.apply {
            layoutManager = GridLayoutManager(context, 4)
            adapter = IconAdapter(data.icons)
        }
    }

    override fun getItemCount(): Int = pages.size

    class BannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    data class BannerPageData(val type: Int, val icons: List<Int>)

    class IconAdapter(private val icons: List<Int>) :
        RecyclerView.Adapter<IconAdapter.IconViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IconViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_icon, parent, false)
            return IconViewHolder(view)
        }

        override fun onBindViewHolder(holder: IconViewHolder, position: Int) {
            holder.icon.setImageResource(icons[position])
        }

        override fun getItemCount(): Int = icons.size
        class IconViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val icon: ImageView = itemView.findViewById(R.id.icon)
        }
    }
}
