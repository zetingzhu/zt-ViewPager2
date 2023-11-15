package com.zzt.banvp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zzt.banvp.entiy.ImgObj;
import com.zzt.utilcode.util.CollectionUtils;
import com.zzt.utilcode.util.ColorUtils;
import com.zzt.utilcode.util.Utils;

import java.util.Collections;
import java.util.List;

/**
 * @author: zeting
 * @date: 2022/1/6
 */
public class MyVP1Adapter extends RecyclerView.Adapter<MyVH> {
    List<ImgObj> datas;

    public MyVP1Adapter(List<ImgObj> datas) {
        this.datas = datas;
    }

    @NonNull
    @Override
    public MyVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vp_img, parent, false);
        return new MyVH(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyVH holder, int position) {
        int bindingAdapterPosition = holder.getBindingAdapterPosition();
        if (CollectionUtils.isNotEmpty(datas)) {
            ImgObj realData = datas.get(bindingAdapterPosition);
            holder.iv_banner_img.setImageResource(realData.getImgId());
            holder.tv_body.setText(realData.getBody());
            holder.fl_bg.setBackgroundColor(ColorUtils.getRandomColor());
        }
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }
}
