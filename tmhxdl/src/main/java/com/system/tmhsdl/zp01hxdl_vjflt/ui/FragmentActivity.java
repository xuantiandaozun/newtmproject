package com.system.tmhsdl.zp01hxdl_vjflt.ui;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.system.myproject.base.MVPBaseActivity;
import com.system.myproject.base.MVPBasePresenter;
import com.system.myproject.base.TMBaseFragment;
import com.system.tmhsdl.zp01hxdl_vjflt.HxdlRouterConfig;
import com.system.tmhsdl.zp01hxdl_vjflt.R;
import com.system.tmhsdl.zp01hxdl_vjflt.R2;

import java.util.HashMap;

import butterknife.BindView;

@Route(path = HxdlRouterConfig.MAIN_FRAGMENT)
public class FragmentActivity extends MVPBaseActivity {
    @BindView(R2.id.main)
    FrameLayout main;
    private String fragment;
    private String data;
    private int systemUiVisibility;

    @Override
    protected MVPBasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getThemeResId() {
        return R.style.BlueTheme;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.hxdl_activity_fragment;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        //透明状态栏
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            window.addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                systemUiVisibility = window.getDecorView().getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
                window.getDecorView().setSystemUiVisibility(systemUiVisibility);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
            }
        }
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);


        Bundle bundle = getIntent().getExtras();
        String params = bundle.getString("params");
        HashMap hashMapByParams = getHashMapByParams(params);
        if(hashMapByParams.containsKey("fragment")){
            fragment = hashMapByParams.get("fragment").toString();
        }
        if(hashMapByParams.containsKey("params")){
            data = hashMapByParams.get("params").toString();
        }
        if(!TextUtils.isEmpty(fragment)){
            TMBaseFragment navigation = (TMBaseFragment) ARouter.getInstance()
                    .build(fragment)
                    .withString("params",data)
                    .navigation();
            loadRootFragment(R.id.main,navigation);
        }
    }

    @Override
    protected void initDatas() {


    }
}
