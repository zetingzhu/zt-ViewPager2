package com.zzt.myviewpager.util;

import android.graphics.Color;

import java.util.Random;

/**
 * @author: zeting
 * @date: 2023/4/7
 */
public class ColorUtil {

    /**
     * 随机颜色
     */
    public static int getRandomColor() {
        Random random = new Random();
        int color = Color.argb(255,
                random.nextInt(255),
                random.nextInt(255),
                random.nextInt(255));

        return color;
    }
}
