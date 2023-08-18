package com.zzt.myviewpager.util;

import android.content.Context;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

/**
 * Viewpager2 和 indicator 管理器
 */
public class ViewpagerIndicatorManager {
    ViewPager2 mViewPager2;
    CircleIndicator mIndicator;
    @Nullable
    private RecyclerView.Adapter<?> mAdapter;

    private BannerOnPageChangeCallback mPageChangeCallback;

    public ViewpagerIndicatorManager(ViewPager2 viewPager2, CircleIndicator indicator) {
        this.mViewPager2 = viewPager2;
        this.mIndicator = indicator;
    }

    public void attach() {
        if (mViewPager2 == null || mIndicator == null) {
            throw new IllegalStateException(
                    "indicator or ViewPager2 Can not be empty");
        }
        mAdapter = mViewPager2.getAdapter();
        if (mAdapter == null) {
            throw new IllegalStateException(
                    "attached before ViewPager2 has an " + "adapter");
        }
        mPageChangeCallback = new BannerOnPageChangeCallback();
        mViewPager2.registerOnPageChangeCallback(mPageChangeCallback);
        setIndicatorPageChange();
        getAdapter().registerAdapterDataObserver(mAdapterDataObserver);
    }

    public IndicatorListener getIndicator() {
        return mIndicator;
    }

    public ViewPager2 getViewPager2() {
        return mViewPager2;
    }

    public Context getContext() {
        return mViewPager2.getContext();
    }

    public int getCurrentItem() {
        return getViewPager2().getCurrentItem();
    }

    public RecyclerView.Adapter<?> getAdapter() {
        return mAdapter;
    }

    public IndicatorConfig getIndicatorConfig() {
        if (getIndicator() != null) {
            return getIndicator().getIndicatorConfig();
        }
        return null;
    }

    public int getRealCount() {
        if (getAdapter() != null) {
            return getAdapter().getItemCount();
        }
        return 0;

    }

    public void setIndicatorPageChange() {
        if (getIndicator() != null) {
            getIndicator().onPageChanged(getRealCount(), 0);
        }
    }

    public void setIndicatorSelectedColor(@ColorInt int color) {
        if (getIndicatorConfig() != null) {
            getIndicatorConfig().setSelectedColor(color);
        }
    }

    public void setIndicatorSelectedColorRes(@ColorRes int color) {
        setIndicatorSelectedColor(ContextCompat.getColor(getContext(), color));
    }

    public void setIndicatorNormalColor(@ColorInt int color) {
        if (getIndicatorConfig() != null) {
            getIndicatorConfig().setNormalColor(color);
        }
    }

    public void setIndicatorNormalColorRes(@ColorRes int color) {
        setIndicatorNormalColor(ContextCompat.getColor(getContext(), color));
    }

    public void setIndicatorSpace(int indicatorSpace) {
        if (getIndicatorConfig() != null) {
            getIndicatorConfig().setIndicatorSpace(indicatorSpace);
        }
    }

    public void setIndicatorWidth(int normalWidth, int selectedWidth) {
        if (getIndicatorConfig() != null) {
            getIndicatorConfig().setNormalWidth(normalWidth);
            getIndicatorConfig().setSelectedWidth(selectedWidth);
        }
    }

    public void setIndicatorNormalWidth(int normalWidth) {
        if (getIndicatorConfig() != null) {
            getIndicatorConfig().setNormalWidth(normalWidth);
        }
    }

    public void setIndicatorSelectedWidth(int selectedWidth) {
        if (getIndicatorConfig() != null) {
            getIndicatorConfig().setSelectedWidth(selectedWidth);
        }
    }

    /**
     * viewpager2 切换监听
     */
    class BannerOnPageChangeCallback extends ViewPager2.OnPageChangeCallback {
        private boolean isScrolled;

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (getIndicator() != null && position == getCurrentItem() - 1) {
                getIndicator().onPageScrolled(position, positionOffset, positionOffsetPixels);
            }
        }

        @Override
        public void onPageSelected(int position) {
            if (isScrolled) {
                if (getIndicator() != null) {
                    getIndicator().onPageSelected(position);
                }
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            //手势滑动中,代码执行滑动中
            if (state == ViewPager2.SCROLL_STATE_DRAGGING || state == ViewPager2.SCROLL_STATE_SETTLING) {
                isScrolled = true;
            } else if (state == ViewPager2.SCROLL_STATE_IDLE) {
                //滑动闲置或滑动结束
                isScrolled = false;
            }
        }
    }

    /**
     * 监听viewpager2 变化
     */
    private final RecyclerView.AdapterDataObserver mAdapterDataObserver = new RecyclerView.AdapterDataObserver() {
        @Override
        public void onChanged() {
            setIndicatorPageChange();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            setIndicatorPageChange();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount, @Nullable Object payload) {
            setIndicatorPageChange();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            setIndicatorPageChange();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            setIndicatorPageChange();
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            setIndicatorPageChange();
        }
    };

}
