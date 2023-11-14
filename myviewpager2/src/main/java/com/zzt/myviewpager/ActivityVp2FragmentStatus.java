package com.zzt.myviewpager;

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
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.zzt.myviewpager.adapter.Vp2FragmentStatusV1;
import com.zzt.myviewpager.data.ZBaseFragmentStatusItemObj;
import com.zzt.viewpager2.R;

import java.util.ArrayList;
import java.util.List;


/**
 * @author: zeting
 * @date: 2023/8/28
 */
public class ActivityVp2FragmentStatus extends AppCompatActivity {
    private static final String TAG = ActivityVp2FragmentStatus.class.getSimpleName();
    private Button btn_fragment_remove, btn_fragment_add, btn_fragment_replace;
    private TabLayout tab_layout;
    private ViewPager2 viewpager2;
    private Vp2FragmentStatusV1 vp2Adapter2;
    private List<ZBaseFragmentStatusItemObj> mFragments;
    ZBaseFragmentStatusItemObj itemFrag0;
    ZBaseFragmentStatusItemObj itemFrag1;
    ZBaseFragmentStatusItemObj itemFrag2;
    ZBaseFragmentStatusItemObj itemFrag3;


    public static void start(Context context) {
        Intent starter = new Intent(context, ActivityVp2FragmentStatus.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_vp2_status);
        initView();
        initData();
    }

    private void initData() {
        SparseArray<String> aa = new SparseArray<>();
        aa.append(0, "a:" + 0);
        aa.append(1, "a:" + 1);
        aa.append(2, "a:" + 2);
        aa.append(3, "a:" + 3);

        Log.d("zzz", "数据：" + aa.toString());

        aa.append(1, "a:" + 4);

        Log.w("zzz", "数据：" + aa.toString());
        aa.put(2, "a:" + 5);

        Log.e("zzz", "数据：" + aa.toString());

    }

    private void initView() {
        tab_layout = findViewById(R.id.tab_layout);
        viewpager2 = findViewById(R.id.viewpager2);
        btn_fragment_replace = findViewById(R.id.btn_fragment_replace);

        btn_fragment_remove = findViewById(R.id.btn_fragment_remove);
        btn_fragment_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentItem = viewpager2.getCurrentItem();

                vp2Adapter2.removeFragments(currentItem);
            }
        });

        btn_fragment_add = findViewById(R.id.btn_fragment_add);
        btn_fragment_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentItem = viewpager2.getCurrentItem();

                long nextId = vp2Adapter2.getNextId();
                vp2Adapter2.insertFragments(currentItem, new ZBaseFragmentStatusItemObj(ItemFragment.newFragment(nextId + " ", 9), nextId));
            }
        });
        btn_fragment_replace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFragments == null) {
                    mFragments = new ArrayList<>();
                } else {
                    mFragments.clear();
                }
                mFragments.add(itemFrag0);
                mFragments.add(itemFrag1);
                mFragments.add(itemFrag2);
                mFragments.add(itemFrag3);
                vp2Adapter2.replaceFragments(mFragments);
            }
        });

        vp2Adapter2 = new Vp2FragmentStatusV1(ActivityVp2FragmentStatus.this);

        mFragments = new ArrayList<>();

        itemFrag0 = new ZBaseFragmentStatusItemObj(ItemFragment.newFragment("0", 9), vp2Adapter2.getNextId());
        itemFrag1 = new ZBaseFragmentStatusItemObj(ItemFragment.newFragment("1", 9), vp2Adapter2.getNextId());
        itemFrag2 = new ZBaseFragmentStatusItemObj(ItemFragment.newFragment("2", 9), vp2Adapter2.getNextId());
        itemFrag3 = new ZBaseFragmentStatusItemObj(ItemFragment.newFragment("3", 9), vp2Adapter2.getNextId());

        mFragments.add(itemFrag0);
        mFragments.add(itemFrag1);
        mFragments.add(itemFrag2);
        mFragments.add(itemFrag3);

        mFragments.add(new ZBaseFragmentStatusItemObj(ItemFragment.newFragment("4", 9), vp2Adapter2.getNextId()));
        mFragments.add(new ZBaseFragmentStatusItemObj(ItemFragment.newFragment("5", 9), vp2Adapter2.getNextId()));

        vp2Adapter2.setFragments(mFragments);

        viewpager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                Log.d(TAG, ">1> viewpager2 onPageSelected position:" + position);
            }
        });

        // 设置滚动方向
        viewpager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        viewpager2.setAdapter(vp2Adapter2);

        viewpager2.setPageTransformer(new MarginPageTransformer(30));

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tab_layout, viewpager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                ZBaseFragmentStatusItemObj baseStatusFragmentItemObj = mFragments.get(position);
                if (baseStatusFragmentItemObj != null) {
                    tab.setText("Tab:" + baseStatusFragmentItemObj.getPage());
                } else {
                    tab.setText("Tab:-");
                }
            }
        });
        tabLayoutMediator.attach();
    }


}
