package com.zzt.banvp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;


import com.zzt.banvp.entiy.ImgObj;
import com.zzt.banvp.util.BannerAdapterBase;
import com.zzt.utilcode.util.ColorUtils;

import java.util.List;

/**
 * @author: zeting
 * @date: 2022/1/5
 */
public class ImgAdapter extends BannerAdapterBase<ImgObj, MyVH> {
    private static final String TAG = ImgAdapter.class.getSimpleName();

    public ImgAdapter(List<ImgObj> datas) {
        super(datas);
    }

    @Override
    public void setDatas(List<ImgObj> datas) {
        super.setDatas(datas);
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
        int realPosition = getRealPosition(bindingAdapterPosition);
        ImgObj realData = getRealData(bindingAdapterPosition);
        holder.iv_banner_img.setImageResource(realData.getImgId());
        holder.tv_body.setText(realData.getBody());
        holder.fl_bg.setBackgroundColor(ColorUtils.getRandomColor());
    }

}
