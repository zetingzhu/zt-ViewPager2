package com.zzt.banvp;

import static com.zzt.utilcode.util.SizeUtils.dp2px;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.zzt.adapter.BtnHorizontalRecyclerAdapter;
import com.zzt.banvp.entiy.ImgObj;
import com.zzt.banvp.trans.BackgroundToForegroundTransformer;
import com.zzt.banvp.trans.CubeInTransformer;
import com.zzt.banvp.trans.CubeOutTransformer;
import com.zzt.banvp.trans.DepthPageTransformer;
import com.zzt.banvp.trans.DepthTransformer;
import com.zzt.banvp.trans.FlipHorizontalTransformer;
import com.zzt.banvp.trans.FlipVerticalTransformer;
import com.zzt.banvp.trans.ForegroundToBackgroundTransformer;
import com.zzt.banvp.trans.RotateDownTransformer;
import com.zzt.banvp.trans.RotateUpTransformer;
import com.zzt.banvp.trans.TabletTransformer;
import com.zzt.banvp.trans.ZoomInTransformer;
import com.zzt.banvp.trans.ZoomOutPageTransformer;
import com.zzt.banvp.trans.ZoomOutTransformer;
import com.zzt.banvp.util.CircleIndicator;
import com.zzt.banvp.util.RoundIndicator;
import com.zzt.banvp.util.RoundRectIndicator;
import com.zzt.banvp.util.RoundSlidingIndicator;
import com.zzt.banvp.util.ViewPager2BannerManager;
import com.zzt.banvp.util.ViewPager2IndicatorManager;
import com.zzt.utilcode.util.ColorUtils;
import com.zzt.utilcode.util.SizeUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    ViewPager2 vp_banner1, vp_banner2, vp_banner3, vp_banner4;
    CircleIndicator ci_banner1, ci_banner2, ci_banner4;
    RoundIndicator ri_banner1;
    RoundSlidingIndicator rsi_banner1;

    RecyclerView rv_h;

    Integer[] arrayIds = {R.drawable.sign_blue_peach_big, R.drawable.sign_red_peach_big,
            R.drawable.sign_yellow_peach_small, R.drawable.sign_blue_peach_big_expire,
            R.drawable.sign_red_peach_big_expire, R.drawable.sign_yellow_peach_small_expire};
    List<ImgObj> ints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ints = listTestData();
        initView();
        initBanner2();
        initBanner3();
        initBanner4();
    }

    private void initBanner2() {
        rv_h = findViewById(R.id.rv_h);
        vp_banner2 = findViewById(R.id.vp_banner2);
        ci_banner2 = findViewById(R.id.ci_banner2);

        ImgAdapter imgAdapter = new ImgAdapter(null);
        ViewPager2BannerManager vpbm = new ViewPager2BannerManager(vp_banner2, imgAdapter);
        vpbm.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                Log.e(TAG, "滑动 -onPageSelected- ：" + position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
        vpbm.attach();

        ViewPager2IndicatorManager vim2 = new ViewPager2IndicatorManager(vp_banner2, ci_banner2);
        vim2.setBannerManager(vpbm);
        vim2.setIndicatorNormalColor(Color.parseColor("#DFDFDE"));
        vim2.setIndicatorSelectedColor(Color.parseColor("#FF0000"));
        vim2.attach();

        vp_banner2.postDelayed(new Runnable() {
            @Override
            public void run() {
                imgAdapter.setDatas(ints);
                vpbm.setDataAfter();

            }
        }, 1000 * 3);
    }

    private void initBanner4() {
        vp_banner4 = findViewById(R.id.vp_banner4);
        ci_banner4 = findViewById(R.id.ci_banner4);
        vp_banner4.setPageTransformer(new ZoomOutPageTransformer());

        /**设置显示viewpager 显示位置大小*/
        int padding = (int) dp2px(50);
        RecyclerView recyclerView = (RecyclerView) vp_banner4.getChildAt(0);
        recyclerView.setPadding(padding, 0, padding, 0);
        // 设置裁剪，并且有其他子类填充
        recyclerView.setClipToPadding(false);

        ImgAdapter imgAdapter = new ImgAdapter(ints);
        ViewPager2BannerManager vpbm = new ViewPager2BannerManager(vp_banner4, imgAdapter);
        vpbm.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                Log.e(TAG, "滑动 -onPageSelected- ：" + position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
        vpbm.attach();

        ViewPager2IndicatorManager vim2 = new ViewPager2IndicatorManager(vp_banner4, ci_banner4);
        vim2.setBannerManager(vpbm);
        vim2.setIndicatorNormalColor(Color.parseColor("#DFDFDE"));
        vim2.setIndicatorSelectedColor(Color.parseColor("#FF0000"));
        vim2.attach();

    }

    private void initBanner3() {
        vp_banner3 = findViewById(R.id.vp_banner3);

        MyVP1Adapter vpAdapter3 = new MyVP1Adapter(ints);
        vp_banner3.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        vp_banner3.setAdapter(vpAdapter3);
        vp_banner3.setUserInputEnabled(true);

        List<String> btnList = new ArrayList<>();
        btnList.add(0, "缩放");
        btnList.add(1, "缩放2");
        btnList.add(2, "深度");
        btnList.add(3, "深度2");
        btnList.add(4, "前一个不变，后一个缩小");
        btnList.add(5, "立方体中间小两边大左右衔接翻转");
        btnList.add(6, "立方体中间大两边小左右衔接翻转");
        btnList.add(7, "竖向左右翻转");
        btnList.add(8, "横向上下翻转");
        btnList.add(9, "前一个缩小，后一个不变");
        btnList.add(10, "向下弧度旋转");
        btnList.add(11, "向上弧度旋转");
        btnList.add(12, "TTf");
        btnList.add(13, "视图缩小进入和缩小退出");
        BtnHorizontalRecyclerAdapter.setAdapterData(rv_h, btnList, new BtnHorizontalRecyclerAdapter.OnItemClickListener<String>() {
            @Override
            public void onItemClick(View itemView, int position, String data) {
                switch (position) {
                    case 0:
                        vp_banner3.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
                        vp_banner3.setPageTransformer(new ZoomOutPageTransformer());
                        break;
                    case 1:
                        vp_banner3.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
                        vp_banner3.setPageTransformer(new ZoomOutTransformer());
                        break;
                    case 2:
                        vp_banner3.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
                        vp_banner3.setPageTransformer(new DepthPageTransformer());
                        break;
                    case 3:
                        vp_banner3.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
                        vp_banner3.setPageTransformer(new DepthTransformer());
                        break;
                    case 4:
                        vp_banner3.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
                        vp_banner3.setPageTransformer(new BackgroundToForegroundTransformer());
                        break;
                    case 5:
                        vp_banner3.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
                        vp_banner3.setPageTransformer(new CubeInTransformer());
                        break;
                    case 6:
                        vp_banner3.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
                        vp_banner3.setPageTransformer(new CubeOutTransformer());
                        break;
                    case 7:
                        vp_banner3.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
                        vp_banner3.setPageTransformer(new FlipHorizontalTransformer());
                        break;
                    case 8:
                        vp_banner3.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
                        vp_banner3.setPageTransformer(new FlipVerticalTransformer());
                        break;
                    case 9:
                        vp_banner3.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
                        vp_banner3.setPageTransformer(new ForegroundToBackgroundTransformer());
                        break;
                    case 10:
                        vp_banner3.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
                        vp_banner3.setPageTransformer(new RotateDownTransformer());
                        break;
                    case 11:
                        vp_banner3.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
                        vp_banner3.setPageTransformer(new RotateUpTransformer());
                        break;
                    case 12:
                        vp_banner3.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
                        vp_banner3.setPageTransformer(new TabletTransformer());
                        break;
                    case 13:
                        vp_banner3.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
                        vp_banner3.setPageTransformer(new ZoomInTransformer());
                        break;

                }
            }
        });

    }

    private void initView() {
        vp_banner1 = findViewById(R.id.vp_banner1);
        ci_banner1 = findViewById(R.id.ci_banner1);
        ri_banner1 = findViewById(R.id.ri_banner1);
        rsi_banner1 = findViewById(R.id.rsi_banner1);

        ri_banner1.getIndicatorConfig().setNormalWidth(dp2px(24));

        RecyclerView.Adapter adapter2 = new RecyclerView.Adapter<MyVH>() {
            @NonNull
            @Override
            public MyVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vp_img, parent, false);
                MyVH myVH = new MyVH(view);
                return myVH;
            }

            @Override
            public void onBindViewHolder(@NonNull MyVH holder, int position) {
                holder.iv_banner_img.setImageResource(arrayIds[position]);
                holder.fl_bg.setBackgroundColor(ColorUtils.getRandomColor());
                holder.tv_body.setText("这是第几个：" + position);
            }

            @Override
            public int getItemCount() {
                return arrayIds.length;
            }
        };
        vp_banner1.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        vp_banner1.setAdapter(adapter2);

        // 绑定指示器
        ViewPager2IndicatorManager vim1 = new ViewPager2IndicatorManager(vp_banner1, ci_banner1);
        vim1.setIndicatorNormalColor(Color.parseColor("#222222"));
        vim1.setIndicatorSelectedColor(Color.parseColor("#FF0000"));
        vim1.attach();

        ViewPager2IndicatorManager vim2 = new ViewPager2IndicatorManager(vp_banner1, ri_banner1);
        vim2.setIndicatorNormalColor(Color.parseColor("#222222"));
        vim2.setIndicatorSelectedColor(Color.parseColor("#FF0000"));
        vim2.setIndicatorNormalWidth(dp2px(24));
        vim2.attach();

        ViewPager2IndicatorManager vim3 = new ViewPager2IndicatorManager(vp_banner1, rsi_banner1);
        vim3.setIndicatorNormalColor(Color.parseColor("#222222"));
        vim3.setIndicatorSelectedColor(Color.parseColor("#FF0000"));
        vim3.setIndicatorNormalWidth(dp2px(24));
        vim3.attach();
    }

    public List<ImgObj> listTestData() {
        List<ImgObj> ints = new ArrayList<>();
        for (int i = 0; i < arrayIds.length; i++) {
            ints.add(new ImgObj(arrayIds[i], "第" + (i + 1) + "个"));
        }
        return ints;
    }
}