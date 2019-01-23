package com.system.tianmayunxi.zp01yx_bwusb.ui.integral;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.google.gson.Gson;
import com.system.myproject.base.MVPBaseFragment;
import com.system.myproject.base.TMBaseFragment;
import com.system.myproject.utils.GsonUtil;
import com.system.myproject.utils.ToastUtil;
import com.system.tianmayunxi.zp01yx_bwusb.R;
import com.system.tianmayunxi.zp01yx_bwusb.R2;
import com.system.tianmayunxi.zp01yx_bwusb.TmyxRouterConfig;
import com.system.tianmayunxi.zp01yx_bwusb.bean.EventCallBackBean;
import com.system.tianmayunxi.zp01yx_bwusb.ui.officialrecommend.bean.GoodsListBean;
import com.system.tianmayunxi.zp01yx_bwusb.ui.officialrecommend.contract.OfficContract;
import com.system.tianmayunxi.zp01yx_bwusb.ui.officialrecommend.presenter.OfficPresenter;
import com.system.uilibrary.views.titlebar.TitleBarView;
import com.tenma.ventures.bean.utils.TMSharedPUtil;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import butterknife.BindView;
import okhttp3.RequestBody;

@Route(path = TmyxRouterConfig.LQJF_ADDADDRESS)
public class AddAddressFragment extends MVPBaseFragment<OfficContract.View, OfficPresenter>
        implements OfficContract.View {
    @BindView(R2.id.titleBar)
    TitleBarView titleBar;
    @BindView(R2.id.ed_nickname)
    EditText ed_nickname;
    @BindView(R2.id.ed_phone)
    EditText ed_phone;
    @BindView(R2.id.ed_city)
    EditText ed_city;
    @BindView(R2.id.ed_detail)
    EditText ed_detail;
    @Autowired(name = "params")
    public String params;
    private GoodsListBean.ListBean beans;
    private int themeColor;
    private int textcolor;

    @Override
    protected OfficPresenter createPresenter() {
        return new OfficPresenter();
    }

    @Override
    protected void init() {
        HashMap<String, Object> filter = getHashMapByParams(params);
        if (filter != null && filter.containsKey("detail")) {
            String detail = filter.get("detail").toString();
            beans = GsonUtil.GsonToBean(detail, GoodsListBean.ListBean.class);
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_addaddress_zp01yx_bwusb;
    }

    @Override
    protected void initDatas() {
        themeColor = Color.parseColor(TMSharedPUtil.getTMThemeColor(getActivity()));
        textcolor = Color.parseColor(TMSharedPUtil.getTMTitleTextColor(getActivity()));
        titleBar.setBackgroundColor(themeColor);
        titleBar.setTitleMainTextColor(textcolor);

        titleBar.setTitleMainText("填写地址")
                .setLeftTextDrawable(R.mipmap.icon_nav_back)
                .setOnLeftTextClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pop();
                    }
                }).setRightText("完成").setOnRightTextClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exchangeGoods();

            }
        });
    }

    private void exchangeGoods() {
        String nickname = ed_nickname.getText().toString();
        String phone = ed_phone.getText().toString();
        String city = ed_city.getText().toString();
        String detail = ed_detail.getText().toString();
        if (TextUtils.isEmpty(nickname)) {
            ToastUtil.showSnack(getContext(), "姓名不能为空！");
            return;
        }
        if (TextUtils.isEmpty(phone)) {
            ToastUtil.showSnack(getContext(), "电话不能为空！");
            return;
        }
        if (TextUtils.isEmpty(city)) {
            ToastUtil.showSnack(getContext(), "地区不能为空！");
            return;
        }
        if (TextUtils.isEmpty(detail)) {
            ToastUtil.showSnack(getContext(), "详细地址不能为空！");
            return;
        }
        HashMap<String, String> parms = new HashMap<>();
        parms.put("id", beans.getId() + "");
        parms.put("nickname", nickname);
        parms.put("phone", phone);
        parms.put("city", city);
        parms.put("addr",detail);
        String value = new Gson().toJson(parms);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), value);
        mPresenter.exchangeGoods(body);
    }

    @Override
    public void onFaild() {

    }

    @Override
    public void callBack(EventCallBackBean bean) {
        int eventNumber = bean.getEventNumber();
        HashMap<String, Object> eventData = bean.getEventData();
        Set<String> keySet = eventData.keySet();
        Iterator<String> iterator = keySet.iterator();
        switch (eventNumber) {
            case EventCallBackBean.REFRESH:
                while (iterator.hasNext()) {
                    String next = iterator.next();
                    Object object = eventData.get(next);
                    switch (next) {
                        case "":
                            break;
                    }
                }
                break;
            case EventCallBackBean.CLOSE:
                break;
            case EventCallBackBean.WHITEDATA:
                while (iterator.hasNext()) {
                    String next = iterator.next();
                    Object object = eventData.get(next);
                    switch (next) {
                        case "exchangeGoods":
                            TMBaseFragment fragment = (TMBaseFragment) ARouter.getInstance().build(TmyxRouterConfig.TMYX_DHJG)
                                    .navigation();
                            start(fragment);
                            break;
                        default:
                            break;
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void showMessage(int type, String message) {
        ToastUtil.showSnack(getThisContext(), message);
    }
}
