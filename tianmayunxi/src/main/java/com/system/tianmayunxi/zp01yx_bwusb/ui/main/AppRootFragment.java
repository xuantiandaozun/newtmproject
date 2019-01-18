package com.system.tianmayunxi.zp01yx_bwusb.ui.main;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import com.system.myproject.base.MVPBaseFragment;
import com.system.myproject.base.MVPBasePresenter;
import com.system.myproject.base.TMBaseFragment;
import com.system.tianmayunxi.zp01yx_bwusb.R;
import com.system.tianmayunxi.zp01yx_bwusb.R2;
import com.system.tianmayunxi.zp01yx_bwusb.TmyxRouterConfig;
import com.system.tianmayunxi.zp01yx_bwusb.adapter.ViewPagerAdapter;
import com.system.tianmayunxi.zp01yx_bwusb.views.ScaleTransitionPagerTitleView;


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

/**
 * 创建人： zhoudingwen
 * 创建时间：2018/4/3
 */
public class AppRootFragment extends MVPBaseFragment {
    @BindView(R2.id.magic_indicator5)
    MagicIndicator magicIndicator;
    @BindView(R2.id.viewpager)
    ViewPager mViewpager;
    private ViewPagerAdapter adapter;
    private static final String[] CHANNELS = new String[]{"官方推荐", "我的订阅","全部主题","我的发布"};
    private List<String> mDataList = Arrays.asList(CHANNELS);

    public static AppRootFragment f = null;

    public static AppRootFragment newInstance() {
        if (f == null) {
            f = new AppRootFragment();
        }
        return f;
    }

    @Override
    protected MVPBasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void init() {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_root_zp01yx_bwusb;
    }

    @Override
    protected void initDatas() {
        TMBaseFragment fragment01 = (TMBaseFragment) ARouter.getInstance().build(TmyxRouterConfig.TMYX_GFTJ)
                .navigation();
        TMBaseFragment fragment02 = (TMBaseFragment) ARouter.getInstance().build(TmyxRouterConfig.TMYX_WDDY)
                .navigation();
        TMBaseFragment fragment03 = (TMBaseFragment) ARouter.getInstance().build(TmyxRouterConfig.TMYX_QBZT)
                .navigation();
        TMBaseFragment fragment04 = (TMBaseFragment) ARouter.getInstance().build(TmyxRouterConfig.TMYX_WDFB)
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
    private void initMagicIndicator5() {
        magicIndicator.setBackgroundResource(R.color.blue_primary);
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
                simplePagerTitleView.setNormalColor(Color.parseColor("#CCCCCC"));
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
