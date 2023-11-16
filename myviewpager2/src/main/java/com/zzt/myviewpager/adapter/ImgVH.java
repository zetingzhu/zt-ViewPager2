package com.zzt.myviewpager.adapter;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zzt.viewpager2.R;

/**
 * @author: zeting
 * @date: 2020/1/10
 */
public class ImgVH extends RecyclerView.ViewHolder {
    ImageView iv_banner_img;
    FrameLayout fl_bg;

    public ImgVH(@NonNull View itemView) {
        super(itemView);
        iv_banner_img = itemView.findViewById(R.id.iv_banner_img);
        fl_bg = itemView.findViewById(R.id.fl_bg);
    }
}
