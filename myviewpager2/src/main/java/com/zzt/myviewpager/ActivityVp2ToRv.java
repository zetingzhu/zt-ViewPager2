package com.zzt.myviewpager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.zzt.myviewpager.adapter.Vp2ToRvAdapterVp;
import com.zzt.viewpager2.R;

/**
 * viewpaget2 嵌套 recycleview
 */
public class ActivityVp2ToRv extends AppCompatActivity {
    public static void start(Context context) {
        Intent starter = new Intent(context, ActivityVp2ToRv.class);
        context.startActivity(starter);
    }

    private ViewPager2 vptorv_vp;
    private Vp2ToRvAdapterVp mVp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vp2_to_rv);
        initView();
    }

    private void initView() {
        vptorv_vp = findViewById(R.id.vptorv_vp);
        mVp = new Vp2ToRvAdapterVp();
        vptorv_vp.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        vptorv_vp.setAdapter(mVp);
    }
}