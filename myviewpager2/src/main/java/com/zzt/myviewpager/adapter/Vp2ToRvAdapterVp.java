package com.zzt.myviewpager.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zzt.viewpager2.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zeting
 * @date: 2023/4/7
 */
public class Vp2ToRvAdapterVp extends RecyclerView.Adapter<Vp2ToRvAdapterVp.VpVh> {
    List<String> mList = new ArrayList<>();

    public Vp2ToRvAdapterVp() {
        for (int i = 0; i < 10; i++) {
            mList.add("vp2 标题:" + i);
        }
    }

    @NonNull
    @Override
    public VpVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vp2_layout_2, parent, false);
        return new VpVh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VpVh holder, int position) {
        Vp2ToRvAdapterRv rvAdapterRv = new Vp2ToRvAdapterRv();
        holder.rv_vp2_item.setLayoutManager(new LinearLayoutManager(holder.rv_vp2_item.getContext(), RecyclerView.HORIZONTAL, false));
        holder.rv_vp2_item.setAdapter(rvAdapterRv);

        holder.tv_vp_title.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class VpVh extends RecyclerView.ViewHolder {
        RecyclerView rv_vp2_item;
        TextView tv_vp_title;

        public VpVh(@NonNull View itemView) {
            super(itemView);
            rv_vp2_item = itemView.findViewById(R.id.rv_vp2_item);
            tv_vp_title = itemView.findViewById(R.id.tv_vp_title);
        }
    }


}
