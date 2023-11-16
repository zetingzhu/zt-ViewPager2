package com.zzt.myviewpager.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zzt.myviewpager.data.ImgObj;
import com.zzt.utilcode.util.CollectionUtils;
import com.zzt.utilcode.util.ColorUtils;
import com.zzt.viewpager2.R;

import java.util.List;

/**
 * @author: zeting
 * @date: 2022/1/6
 */
public class MyVP1Adapter extends RecyclerView.Adapter<ImgVH> {
    List<ImgObj> datas;

    public MyVP1Adapter(List<ImgObj> datas) {
        this.datas = datas;
    }

    @NonNull
    @Override
    public ImgVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView;
        if (viewType % 2 == 0) {
            rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vp_img, parent, false);
        } else {
            rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vp_img2, parent, false);
        }
        return new ImgVH(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ImgVH holder, int position) {
        int bindingAdapterPosition = holder.getBindingAdapterPosition();
        if (CollectionUtils.isNotEmpty(datas)) {
            ImgObj realData = datas.get(bindingAdapterPosition);
            holder.iv_banner_img.setImageResource(realData.getImgId());
//            holder.fl_bg.setBackgroundColor(ColorUtils.getRandomColor());
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }
}
