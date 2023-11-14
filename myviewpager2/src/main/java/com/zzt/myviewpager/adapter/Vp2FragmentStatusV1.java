package com.zzt.myviewpager.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;

import com.zzt.myviewpager.data.ZBaseFragmentStatusItemObj;

/**
 * @author: zeting
 * @date: 2023/8/28
 */
public class Vp2FragmentStatusV1 extends ZBaseFragmentStatusAdapter<ZBaseFragmentStatusItemObj> {
    public Vp2FragmentStatusV1(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public Vp2FragmentStatusV1(@NonNull Fragment fragment) {
        super(fragment);
    }

    public Vp2FragmentStatusV1(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }
}
