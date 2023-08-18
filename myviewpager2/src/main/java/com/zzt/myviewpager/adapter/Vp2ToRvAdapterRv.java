package com.zzt.myviewpager.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zzt.myviewpager.util.ColorUtil;
import com.zzt.viewpager2.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zeting
 * @date: 2023/4/7
 */
public class Vp2ToRvAdapterRv extends RecyclerView.Adapter<Vp2ToRvAdapterRv.RvViewHolder> {
    List<String> mList = new ArrayList<>();

    public Vp2ToRvAdapterRv() {
        for (int i = 0; i < 30; i++) {
            mList.add("rv Title:" + i);
        }
    }

    @NonNull
    @Override
    public RvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv, parent, false);
        return new RvViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull RvViewHolder holder, int position) {
        holder.tv_title.setText(mList.get(position));
        holder.tv_bottom.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class RvViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title, tv_bottom;

        public RvViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_bottom = itemView.findViewById(R.id.tv_bottom);
            itemView.setBackgroundColor(ColorUtil.getRandomColor());
        }
    }
}
