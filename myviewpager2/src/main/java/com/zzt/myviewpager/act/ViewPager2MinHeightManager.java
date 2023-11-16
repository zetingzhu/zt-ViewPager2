package com.zzt.myviewpager.act;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

/**
 * @author: zeting
 * @date: 2023/11/15
 * Viewpager2 设置高度
 * var heightManager = ViewPager2MinHeightManager(vp2_auto)
 * heightManager.attach()
 */
public class ViewPager2MinHeightManager {
    private static final String TAG = ViewPager2MinHeightManager.class.getSimpleName();
    private ViewPager2 mViewPager2;
    @Nullable
    private RecyclerView.Adapter<?> mAdapter;
    private ViewPager2OnPageChangeCallback mPageChangeCallback;

    private int viewState = -1;

    public ViewPager2MinHeightManager(ViewPager2 viewPager2) {
        this.mViewPager2 = viewPager2;
    }

    public void attach() {
        if (mViewPager2 == null) {
            throw new IllegalStateException("indicator or ViewPager2 Can not be empty");
        }
        mAdapter = mViewPager2.getAdapter();
        if (mAdapter == null) {
            throw new IllegalStateException("attached before ViewPager2 has an " + "adapter");
        }
        mPageChangeCallback = new ViewPager2OnPageChangeCallback();
        mViewPager2.registerOnPageChangeCallback(mPageChangeCallback);

        if (mAdapter != null) {
            mAdapter.registerAdapterDataObserver(mAdapterDataObserver);
        }
    }

    /**
     * 计算子 View 的高度并设置给 viewPager2
     *
     * @param view
     */
    private void updatePagerHeightForChild(View view) {
        if (view != null && mViewPager2 != null) {
            int wMeasureSpec = View.MeasureSpec.makeMeasureSpec(view.getWidth(), View.MeasureSpec.EXACTLY);
            int hMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            view.measure(wMeasureSpec, hMeasureSpec);

            Log.d(TAG, ">>> 高度 4：" + (mViewPager2.getLayoutParams().height) + " b:" + (view.getMeasuredHeight()));
            if (mViewPager2.getLayoutParams().height != view.getMeasuredHeight()) {
                ViewGroup.LayoutParams layoutParams = mViewPager2.getLayoutParams();
                layoutParams.height = view.getMeasuredHeight();
                mViewPager2.setLayoutParams(layoutParams);
            }
        }
    }


    /**
     * viewpager2 切换监听
     */
    class ViewPager2OnPageChangeCallback extends ViewPager2.OnPageChangeCallback {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            Log.d(TAG, ">>> 高度 onPageScrolled：" + position + " positionOffset:" + positionOffset + " positionOffsetPixels:" + positionOffsetPixels);
            if (position == 0 && viewState == -1 && positionOffset == 0 && positionOffsetPixels == 0) {
                mViewPager2.post(new Runnable() {
                    @Override
                    public void run() {
                        findViewByPos(0);
                    }
                });

            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            super.onPageScrollStateChanged(state);
            Log.d(TAG, ">>> 高度 onPageScrollStateChanged：" + state);
            viewState = state;
        }


        @Override
        public void onPageSelected(int position) {
            Log.d(TAG, ">>> 高度 onPageSelected：" + position);

            findViewByPos(position);
        }
    }


    public void findViewByPos(int position) {
        if (mViewPager2 != null) {
            Log.d(TAG, ">>> 高度 1：");
            if (mViewPager2.getChildAt(0) instanceof RecyclerView) {
                RecyclerView.LayoutManager layoutManager = ((RecyclerView) mViewPager2.getChildAt(0)).getLayoutManager();
                Log.d(TAG, ">>> 高度 2：");
                if (layoutManager != null) {
                    Log.d(TAG, ">>> 高度 3：");
                    View findViewByPosition = layoutManager.findViewByPosition(position);
                    updatePagerHeightForChild(findViewByPosition);
                }
            }
        }
    }

    /**
     * 视图发生变更
     */
    public void changeView() {
        if (mViewPager2 != null) {
            int currentItem = mViewPager2.getCurrentItem();
            findViewByPos(currentItem);
        }
    }

    /**
     * 监听viewpager2 变化
     */
    private final RecyclerView.AdapterDataObserver mAdapterDataObserver = new RecyclerView.AdapterDataObserver() {
        @Override
        public void onChanged() {
            changeView();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            changeView();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount, @Nullable Object payload) {
            changeView();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            changeView();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            changeView();
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            changeView();
        }
    };

}
