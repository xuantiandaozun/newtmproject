package com.system.tianmayunxi.zp01yx_bwusb.ui.main;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.system.myproject.base.BaseFragment;
import com.system.tianmayunxi.zp01yx_bwusb.R;
import com.system.tianmayunxi.zp01yx_bwusb.R2;
import com.system.tianmayunxi.zp01yx_bwusb.TmyxRouterConfig;
import com.system.tianmayunxi.zp01yx_bwusb.adapter.ViewPagerAdapter;
import com.system.tianmayunxi.zp01yx_bwusb.views.ScaleTransitionPagerTitleView;
import com.tenma.ventures.base.TMFragment;
import com.tenma.ventures.bean.utils.TMSharedPUtil;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainFragment extends TMFragment {
    public View rootView;
    private Unbinder unbinder;
    @BindView(R2.id.magic_indicator5)
    MagicIndicator magicIndicator;
    @BindView(R2.id.viewpager)
    ViewPager mViewpager;
    @BindView(R2.id.view)
    View view;
    private ViewPagerAdapter adapter;
    private static final String[] CHANNELS = new String[]{"官方推荐", "我的订阅","全部主题","我的发布"};
    private List<String> mDataList = Arrays.asList(CHANNELS);
    private int themeColor;
    private int textcolor;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.fragment_root_zp01yx_bwusb, container, false);
        this.unbinder = ButterKnife.bind(this, this.rootView);

        return this.rootView;

    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        themeColor = Color.parseColor(TMSharedPUtil.getTMThemeColor(getActivity()));
        textcolor = Color.parseColor(TMSharedPUtil.getTMTitleTextColor(getActivity()));
        view.setBackgroundColor(themeColor);
        magicIndicator.setBackgroundColor(themeColor);


        BaseFragment fragment01 = (BaseFragment) ARouter.getInstance().build(TmyxRouterConfig.TMYX_GFTJ)
                .navigation();
        BaseFragment fragment02 = (BaseFragment) ARouter.getInstance().build(TmyxRouterConfig.TMYX_WDDY)
                .navigation();
        BaseFragment fragment03 = (BaseFragment) ARouter.getInstance().build(TmyxRouterConfig.TMYX_QBZT)
                .navigation();
        BaseFragment fragment04 = (BaseFragment) ARouter.getInstance().build(TmyxRouterConfig.TMYX_WDFB)
                .navigation();

        adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFrag(fragment01, "官方推荐");
        adapter.addFrag(fragment02, "我的订阅");
        adapter.addFrag(fragment03, "全部主题");
        adapter.addFrag(fragment04, "我的发布");
        mViewpager.setAdapter(adapter);
        mViewpager.setOffscreenPageLimit(3);
        initMagicIndicator5();
    }
    public void onDestroyView() {
        super.onDestroyView();
        this.unbinder.unbind();
    }
    private void initMagicIndicator5() {
        CommonNavigator commonNavigator = new CommonNavigator(getContext());
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mDataList == null ? 0 : mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ScaleTransitionPagerTitleView(context);
                simplePagerTitleView.setText(mDataList.get(index));
                simplePagerTitleView.setTextSize(15);
                simplePagerTitleView.setPadding(UIUtil.dip2px(context, 5),0,UIUtil.dip2px(context, 5),0);
                simplePagerTitleView.setNormalColor(textcolor);
                simplePagerTitleView.setSelectedColor(Color.parseColor("#FFFFFF"));
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mViewpager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setLineWidth(UIUtil.dip2px(context, 30));
                indicator.setLineHeight(UIUtil.dip2px(context, 2));
                indicator.setColors(Color.parseColor("#F0302F"));
                return null;
            }

            @Override
            public float getTitleWeight(Context context, int index) {
                return 1.0f;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, mViewpager);
    }
}
