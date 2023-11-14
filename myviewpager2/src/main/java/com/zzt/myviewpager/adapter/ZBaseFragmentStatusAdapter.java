package com.zzt.myviewpager.adapter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.zzt.myviewpager.data.ZBaseFragmentStatusItemObj;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author: zeting
 * @date: 2023/8/28
 * ViewPager2 + Fragment 适配器
 */
public class ZBaseFragmentStatusAdapter<T extends ZBaseFragmentStatusItemObj> extends FragmentStateAdapter {
    private static final String TAG = ZBaseFragmentStatusAdapter.class.getSimpleName();
    private List<T> mFragments = new ArrayList<>();
    private final HashSet<Long> createIds = new HashSet<>();
    // 得到所有的下标
    private AtomicLong hasIds = new AtomicLong(0);

    /**
     * 获取唯一的下标 id
     *
     * @return
     */
    public long getNextId() {
        return hasIds.incrementAndGet();
    }

    public ZBaseFragmentStatusAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public ZBaseFragmentStatusAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public ZBaseFragmentStatusAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    public void setFragments(List<T> mFragments) {
        this.mFragments = mFragments;
        createIds.clear();
        if (mFragments != null && mFragments.size() > 0) {
            for (T mFragment : mFragments) {
                addFragmentIds(mFragment);
            }
        }
    }

    public void replaceFragments(List<T> mFragments) {
        this.mFragments = mFragments;
        createIds.clear();
        if (mFragments != null && mFragments.size() > 0) {
            for (T mFragment : mFragments) {
                addFragmentIds(mFragment);
            }
        }
        notifyDataSetChanged();
    }

    /**
     * 插入
     *
     * @param position
     * @param item
     */
    public void insertFragments(int position, T item) {
        addFragmentIds(item);
        mFragments.add(position, item);
        notifyItemInserted(position);

        Log.d(TAG, "insertFragments> add:" + position + " > " + mFragments.toString());
    }

    /**
     * 删除
     */
    public void removeFragments(int position) {
        if (isNotEmptyDataForList(mFragments, position)) {
            ListIterator<T> tListIterator = mFragments.listIterator();
            while (tListIterator.hasNext()) {
                T next = tListIterator.next();
                int index = tListIterator.previousIndex();
                if (index == position) {
                    removeFragmentIds(next);
                    tListIterator.remove();
                    break;
                }
            }
            notifyItemRemoved(position);

            Log.d(TAG, "removeFragments> remove:" + position + " > " + mFragments.toString());
        }
    }

    /**
     * 添加 id
     *
     * @param mFragment
     */
    public void addFragmentIds(T mFragment) {
        if (mFragment != null) {
            Long page = mFragment.getPage();
            if (!createIds.contains(page)) {
                createIds.add(page);
            } else {
                throw new RuntimeException("不要添加相同的下标。");
            }
        }
    }

    /**
     * 移除 添加id
     *
     * @param mFragment
     */
    public void removeFragmentIds(T mFragment) {
        if (mFragment != null) {
            Long page = mFragment.getPage();
            createIds.remove(page);
        }
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (isNotListEmpty(mFragments)) {
            T t = mFragments.get(position);
            return t.getFragment();
        }
        return null;
    }


    @Override
    public int getItemCount() {
        return isNotListEmpty(mFragments) ? mFragments.size() : 0;
    }


    @Override
    public boolean containsItem(long itemId) {
        return createIds.contains(itemId);
    }


    @Override
    public long getItemId(int position) {
        if (isNotListEmpty(mFragments)) {
            T t = mFragments.get(position);
            if (t != null) {
                return t.getPage();
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
}
