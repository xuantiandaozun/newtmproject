package com.system.tianmayunxi.zp02yx_xzmbh.ui.integral;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.system.myproject.base.MVPBaseFragment;
import com.system.myproject.base.MVPBasePresenter;
import com.system.tianmayunxi.zp02yx_xzmbh.R;
import com.system.tianmayunxi.zp02yx_xzmbh.R2;
import com.system.tianmayunxi.zp02yx_xzmbh.Tmyx02RouterConfig;
import com.system.tianmayunxi.zp02yx_xzmbh.views.titlebar.TitleBarView;

import butterknife.BindView;
import butterknife.OnClick;
import me.yokeyword.fragmentation.SupportFragment;

@Route(path = Tmyx02RouterConfig.TMYX02_ACCOUNT)
public class AccountFragment extends MVPBaseFragment {
    @BindView(R2.id.titleBar)
    TitleBarView titleBar;
    @Override
    protected MVPBasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void init() {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_account_zp02yx_bwusb;
    }

    @Override
    protected void initDatas() {
        titleBar.setTitleMainText("账号密码")
                .setLeftTextDrawable(R.mipmap.icon_nav_back)
                .setOnLeftTextClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pop();
                    }
                });

    }
    @OnClick({R2.id.re_phone})
    public void onClick(View view){
        SupportFragment fragment=null;
        switch (view.getId()){
            case R2.id.re_phone:
                fragment = (SupportFragment) ARouter.getInstance().build(Tmyx02RouterConfig.TMYX02_BINDPHONE)
                        .navigation();
                start(fragment);
                break;
        }
    }
}
