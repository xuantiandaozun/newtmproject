package com.system.tianmayunxi.zp02yx_xzmbh.ui.myissue;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.system.myproject.base.BaseFragment;
import com.system.myproject.base.MVPBasePresenter;
import com.system.tianmayunxi.zp02yx_xzmbh.R;
import com.system.tianmayunxi.zp02yx_xzmbh.R2;
import com.system.tianmayunxi.zp02yx_xzmbh.TmyxRouterConfig;
import com.system.tianmayunxi.zp02yx_xzmbh.adapter.ViewPagerAdapter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的发布
 */
@Route(path = TmyxRouterConfig.TMYX02_WDFB)
public class MyIssueFragment extends BaseFragment {
    @BindView(R2.id.viewpager)
    ViewPager mViewpager;
    @BindView(R2.id.tv_dynamic)
    TextView tv_dynamic;
    @BindView(R2.id.tv_message)
    TextView tv_message;
    private ViewPagerAdapter adapter;
    private BaseFragment fragment01;
    private BaseFragment fragment02;

    @Override
    protected MVPBasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void init() {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_myissue_zp01yx_bwusb2;
    }

    @OnClick({R2.id.tv_dynamic,R2.id.tv_message})
    public void onClick(View view) {
        int id = view.getId();
        if(id==R.id.tv_dynamic){
            tv_dynamic.setBackgroundResource(R.color.background03_zp01yx_bwusb);
            tv_dynamic.setTextColor(getResources().getColor(R.color.blue_primary_zp01yx_bwusb));


            tv_message.setBackgroundResource(R.color.white);
            tv_message.setTextColor(getResources().getColor(R.color.textcolor02));
            mViewpager.setCurrentItem(0);

        }else if(id==R.id.tv_message){
            tv_dynamic.setBackgroundResource(R.color.white);
            tv_dynamic.setTextColor(getResources().getColor(R.color.textcolor02));

            tv_message.setBackgroundResource(R.color.background03_zp01yx_bwusb);
            tv_message.setTextColor(getResources().getColor(R.color.blue_primary_zp01yx_bwusb));
            mViewpager.setCurrentItem(1);

        }
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            DynamicFragment fragment01 = (DynamicFragment) this.fragment01;
            fragment01.onRefresh(null);
            MessageFragment fragment02 = (MessageFragment) this.fragment02;
            fragment02.onRefresh(null);
        }
    }

    @Override
    protected void initDatas() {
        adapter = new ViewPagerAdapter(getChildFragmentManager());

        fragment01 = (BaseFragment) ARouter
                .getInstance()
                .build(TmyxRouterConfig.WDFB02_DYNAMIC)
                .navigation();
        fragment02 = (BaseFragment) ARouter
                .getInstance()
                .build(TmyxRouterConfig.WDFB02_MESSAGE).navigation();

        adapter.addFrag(fragment01, "动态");
        adapter.addFrag(fragment02, "消息");
        mViewpager.setAdapter(adapter);
        mViewpager.setOffscreenPageLimit(3);

        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        tv_dynamic.setBackgroundResource(R.color.background03_zp01yx_bwusb);
                        tv_dynamic.setTextColor(getResources().getColor(R.color.blue_primary_zp01yx_bwusb));


                        tv_message.setBackgroundResource(R.color.white);
                        tv_message.setTextColor(getResources().getColor(R.color.textcolor02));
                        break;
                    case 1:
                        tv_dynamic.setBackgroundResource(R.color.white);
                        tv_dynamic.setTextColor(getResources().getColor(R.color.textcolor02));

                        tv_message.setBackgroundResource(R.color.background03_zp01yx_bwusb);
                        tv_message.setTextColor(getResources().getColor(R.color.blue_primary_zp01yx_bwusb));
                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}
