package com.zzt.myviewpager.adapter;

import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author: zeting
 * @date: 2023/8/28
 */
public class BaseFragmentStatusAdapterV2<T extends Fragment> extends FragmentStateAdapter {
    private static final String TAG = BaseFragmentStatusAdapterV2.class.getSimpleName();
    private List<T> mFragments = new ArrayList<>();
    private HashSet<Long> createIds = new HashSet<>();//得用hashset防重，用于存储adapter内的顺序
    private HashMap<String, Long> tagMapIds = new HashMap<>();

    private AtomicLong idGen = new AtomicLong(0);

    public long getNextId() {
        return idGen.incrementAndGet();
    }

    public BaseFragmentStatusAdapterV2(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public BaseFragmentStatusAdapterV2(@NonNull Fragment fragment) {
        super(fragment);
    }

    public BaseFragmentStatusAdapterV2(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    public void setFragments(List<T> mFragments) {
        this.mFragments = mFragments;
        if (mFragments != null && mFragments.size() > 0) {
            for (T mFragment : mFragments) {
                addIds(mFragment);
            }
        }
    }


    // 插入
    public void insertFragments(int position, T item) {
        mFragments.add(position, item);
        addIds(item);
        notifyItemInserted(position);

        Log.d(TAG, "insertFragments> add:" + position + " > " + mFragments.toString());
    }

    // 删除
    public void removeFragments(int position) {
        if (isNotEmptyDataForList(mFragments, position)) {
            mFragments.remove(position);
            notifyItemRemoved(position);
            Log.d(TAG, "removeFragments> remove:" + position + " > " + mFragments.toString());
        }
    }

    /**
     * 添加id
     *
     * @param mFragment
     */
    public void addIds(T mFragment) {
        if (mFragment != null) {
            if (mFragment instanceof FragmentStatusTagListener) {
                String simpleName = ((FragmentStatusTagListener) mFragment).fragmentTag();
                if (!TextUtils.isEmpty(simpleName)) {
                    long nextId = getNextId();
                    tagMapIds.put(simpleName, nextId);
                    createIds.add(nextId);
                } else {
                    throw new RuntimeException(" 必须实现 BaseFragmentStatusAdapter.FragmentStatusTagListener 设置不同的 fragmentTag ");
                }
            } else {
                throw new RuntimeException(" 必须实现 BaseFragmentStatusAdapter.FragmentStatusTagListener 设置不同的 fragmentTag ");
            }
        }
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (isNotListEmpty(mFragments)) {
            return mFragments.get(position);
        }
        return null;
    }


    @Override
    public int getItemCount() {
        return isNotListEmpty(mFragments) ? mFragments.size() : 0;
    }


    @Override
    public boolean containsItem(long itemId) {
        Collection<Long> values = tagMapIds.values();
        Log.d(TAG, "containsItem :" + values.toString());
        return values.contains(itemId);
    }


    @Override
    public long getItemId(int position) {
        if (isNotListEmpty(mFragments)) {
            T t = mFragments.get(position);
            if (t != null) {
                String simpleName = t.getClass().getSimpleName();
                try {
                    Log.d(TAG, "getItemId :" + tagMapIds.toString());
                    return tagMapIds.get(simpleName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return super.getItemId(position);
    }


    public boolean isNotListEmpty(Collection<?> list) {
        return list != null && list.size() > 0;
    }

    public boolean isNotEmptyDataForList(Collection<?> coll, int index) {
        if (isNotListEmpty(coll)) {
            int size = coll.size();
            if (index >= 0 && index < size) {
                return true;
            }
        }
        return false;
    }


    public interface FragmentStatusTagListener {
        String fragmentTag();
    }


}
