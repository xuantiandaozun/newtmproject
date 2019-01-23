package com.system.tmhsdl.zp01hxdl_vjflt.ui.main;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.system.myproject.base.BaseFragment;
import com.system.tmhsdl.zp01hxdl_vjflt.HxdlRouterConfig;
import com.system.tmhsdl.zp01hxdl_vjflt.R;
import com.system.tmhsdl.zp01hxdl_vjflt.R2;
import com.system.tmhsdl.zp01hxdl_vjflt.adapter.ViewPagerAdapter;
import com.system.tmhsdl.zp01hxdl_vjflt.views.ScaleTransitionPagerTitleView;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 创建人： zhoudingwen
 * 创建时间：2018/4/3
 */
public class RootFragment extends TMFragment {
    @BindView(R2.id.magic_indicator5)
    MagicIndicator magicIndicator;
    @BindView(R2.id.viewpager)
    ViewPager mViewpager;
    @BindView(R2.id.view1)
    View view1;
    public View rootView;
    private Unbinder unbinder;
    private ViewPagerAdapter adapter;
    private static final String[] CHANNELS = new String[]{"电子刊", "电子书","订阅"};
    private List<String> mDataList = Arrays.asList(CHANNELS);

    public static RootFragment f = null;
    private List<BaseFragment> fragments=null;
    private int currentTabIndex;
    private int themeColor;
    private int titleColor;

    public static RootFragment newInstance() {
        if (f == null) {
            f = new RootFragment();
        }
        return f;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.hxdl_fragment_root, container, false);
        this.unbinder = ButterKnife.bind(this, this.rootView);

        return this.rootView;

    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        themeColor = Color.parseColor(TMSharedPUtil.getTMThemeColor(getActivity()));
        titleColor = Color.parseColor(TMSharedPUtil.getTMTitleTextColor(getActivity()));
        magicIndicator.setBackgroundColor(themeColor );
        view1.setBackgroundColor(themeColor);


        fragments=new ArrayList<>();

        BaseFragment fragment01 = (BaseFragment) ARouter.getInstance().build(HxdlRouterConfig.HXDL_DZK)
                .navigation();
        fragments.add(fragment01);
        BaseFragment fragment02 = (BaseFragment) ARouter.getInstance().build(HxdlRouterConfig.HXDL_DZS)
                .navigation();
        fragments.add(fragment02);
        BaseFragment fragment03 = (BaseFragment) ARouter.getInstance().build(HxdlRouterConfig.HXDL_DY)
                .navigation();
        fragments.add(fragment03);

        adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFrag(fragment01, "电子刊");
        adapter.addFrag(fragment02, "电子书");
        adapter.addFrag(fragment03, "订阅");
        mViewpager.setAdapter(adapter);
        mViewpager.setOffscreenPageLimit(3);
        initMagicIndicator5();
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
                simplePagerTitleView.setNormalColor(titleColor);
                simplePagerTitleView.setSelectedColor(Color.parseColor("#FED403"));
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
