package com.zzt.myviewpager.data;

import androidx.fragment.app.Fragment;

import java.io.Serializable;

/**
 * @author: zeting
 * @date: 2021/7/7
 * fragment 切换状态记录
 */
public class ZBaseFragmentStatusItemObj implements Serializable {

    private Fragment fragment;
    private Long page; // 下标
    private String tagTitle; // tab 标题

    public ZBaseFragmentStatusItemObj() {
    }

    public ZBaseFragmentStatusItemObj(Fragment fragment, Long page) {
        this.fragment = fragment;
        this.page = page;
    }

    public ZBaseFragmentStatusItemObj(Fragment fragment, Long page, String tagTitle) {
        this.fragment = fragment;
        this.page = page;
        this.tagTitle = tagTitle;
    }

    public String getTagTitle() {
        return tagTitle;
    }

    public void setTagTitle(String tagTitle) {
        this.tagTitle = tagTitle;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "BaseStatusFragmentItemObj{" +
                "fragment=" + fragment +
                ", page=" + page +
                '}';
    }
}
