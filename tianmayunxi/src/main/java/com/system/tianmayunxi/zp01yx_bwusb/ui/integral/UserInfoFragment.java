package com.system.tianmayunxi.zp01yx_bwusb.ui.integral;

import android.view.LayoutInflater;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.facebook.drawee.view.SimpleDraweeView;
import com.system.myproject.base.MVPBaseFragment;
import com.system.myproject.base.MVPBasePresenter;
import com.system.tianmayunxi.zp01yx_bwusb.R;
import com.system.tianmayunxi.zp01yx_bwusb.R2;
import com.system.tianmayunxi.zp01yx_bwusb.TmyxRouterConfig;
import com.system.uilibrary.dialog.DialogsTools;
import com.system.uilibrary.views.titlebar.TitleBarView;


import butterknife.BindView;
import butterknife.OnClick;

@Route(path = TmyxRouterConfig.LQJF_INFO)
public class UserInfoFragment extends MVPBaseFragment {
    @BindView(R2.id.titleBar)
    TitleBarView titleBar;
    @BindView(R2.id.head)
    SimpleDraweeView head;
    @Override
    protected MVPBasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void init() {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_userinfo_zp01yx_bwusb;
    }

    @Override
    protected void initDatas() {
        titleBar.setTitleMainText("个人资料")
                .setLeftTextDrawable(R.mipmap.icon_nav_back)
                .setOnLeftTextClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pop();
                    }
                });
        head.setImageURI("https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=3348918278,2148149333&fm=173&app=49&f=JPEG?w=218&h=146&s=B6B57C841ACB1F4528971C0E0300E0DC");
    }
    @OnClick({R2.id.re_nickname,R2.id.re_info})
    public void onClick(View view){
        View inflate=null;
        switch (view.getId()){
            case R2.id.re_nickname:
                 inflate = LayoutInflater.from(getThisContext()).inflate(R.layout.custom_nickname_zp01yx_bwusb, null, false);
                DialogsTools.getInstance().CreateCustomViewDialog(inflate, new DialogsTools.onButtonClickListener() {
                    @Override
                    public void onClick(String message) {

                    }
                });
                break;
            case R2.id.re_info:
                 inflate = LayoutInflater.from(getThisContext()).inflate(R.layout.custom_info_zp01yx_bwusb, null, false);
                DialogsTools.getInstance().CreateCustomViewDialog(inflate, new DialogsTools.onButtonClickListener() {
                    @Override
                    public void onClick(String message) {

                    }
                });
                break;
        }
    }
}
