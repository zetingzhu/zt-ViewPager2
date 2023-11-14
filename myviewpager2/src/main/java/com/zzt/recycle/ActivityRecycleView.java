package com.zzt.recycle;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.zzt.myviewpager.ItemFragment;
import com.zzt.myviewpager.adapter.Vp2FragmentStatusV1;
import com.zzt.myviewpager.data.ZBaseFragmentStatusItemObj;
import com.zzt.viewpager2.R;

import java.util.ArrayList;
import java.util.List;


/**
 * @author: zeting
 * @date: 2023/8/28
 */
public class ActivityRecycleView extends AppCompatActivity {
    private static final String TAG = ActivityRecycleView.class.getSimpleName();
    private Button btn_fragment_remove, btn_fragment_add, btn_fragment_replace;
    private RecyclerView rv_list;


    public static void start(Context context) {
        Intent starter = new Intent(context, ActivityRecycleView.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_recycle_layout);
        initView();
    }


    private void initView() {
        btn_fragment_replace = findViewById(R.id.btn_fragment_replace);
        btn_fragment_remove = findViewById(R.id.btn_fragment_remove);
        btn_fragment_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn_fragment_add = findViewById(R.id.btn_fragment_add);
        btn_fragment_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        btn_fragment_replace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
