package com.system.tianmayunxi.zp02yx_xzmbh.ui.integral;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.aries.ui.view.radius.RadiusTextView;
import com.aries.ui.view.radius.delegate.RadiusTextViewDelegate;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.system.myproject.base.MVPBaseFragment;
import com.system.myproject.base.TMBaseFragment;
import com.system.myproject.utils.GsonUtil;
import com.system.myproject.utils.SPUtils;
import com.system.myproject.utils.ToastUtil;
import com.system.tianmayunxi.zp02yx_xzmbh.R;
import com.system.tianmayunxi.zp02yx_xzmbh.R2;
import com.system.tianmayunxi.zp02yx_xzmbh.Tmyx02RouterConfig;
import com.system.tianmayunxi.zp02yx_xzmbh.bean.EventCallBackBean;
import com.system.tianmayunxi.zp02yx_xzmbh.ui.integral.adapter.QdAdapter;
import com.system.tianmayunxi.zp02yx_xzmbh.ui.officialrecommend.bean.ListSignBean;
import com.system.tianmayunxi.zp02yx_xzmbh.ui.officialrecommend.contract.OfficContract;
import com.system.tianmayunxi.zp02yx_xzmbh.ui.officialrecommend.presenter.OfficPresenter;
import com.system.tianmayunxi.zp02yx_xzmbh.views.SwitchView;
import com.system.uilibrary.views.titlebar.TitleBarView;
import com.tenma.ventures.bean.TMUser;
import com.tenma.ventures.bean.utils.TMSharedPUtil;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.RequestBody;

/**
 * 领取积分
 */
@Route(path = Tmyx02RouterConfig.TMYX02_LQJF)
public class IntegralFragment extends MVPBaseFragment<OfficContract.View, OfficPresenter>
        implements OfficContract.View {
    @BindView(R2.id.titleBar)
    TitleBarView titleBar;
    @BindView(R2.id.mlist)
    RecyclerView mlist;
    @BindView(R2.id.tv_mypoint)
    TextView tv_mypoint;
    @BindView(R2.id.switchview)
    SwitchView switchView;
    @BindView(R2.id.tv2)
    TextView tv2;
    @BindView(R2.id.tv4)
    TextView tv4;
    @BindView(R2.id.tv3)
    TextView tv3;
    @BindView(R2.id.tv_sign)
    RadiusTextView tv_sign;
    @BindView(R2.id.tv_account)
    RadiusTextView tv_account;
    @BindView(R2.id.tv_login)
    RadiusTextView tv_login;
    @BindView(R2.id.tv_userinfo)
    RadiusTextView tv_userinfo;
    @BindView(R2.id.re_theme)
    RelativeLayout re_theme;
    private QdAdapter adapter;
    private boolean is_sign;
    private int score;
    private int day;
    private int themeColor;
    private TMUser tmUser;
    private int textcolor;
    private boolean is_allbind;
    private boolean is_bindlogin;
    private boolean is_bind;

    @Override
    protected OfficPresenter createPresenter() {
        return new OfficPresenter();
    }

    @Override
    protected void init() {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_integral_zp02yx_bwusb;
    }

    @Override
    protected void initDatas() {
        tmUser = TMSharedPUtil.getTMUser(getContext());

        themeColor = Color.parseColor(TMSharedPUtil.getTMThemeColor(getActivity()));
        textcolor = Color.parseColor(TMSharedPUtil.getTMTitleTextColor(getActivity()));
        titleBar.setBackgroundColor(themeColor);
        titleBar.setTitleMainTextColor(textcolor);

        re_theme.setBackgroundColor(themeColor);

        titleBar.setTitleMainText("领取积分")
                .setLeftTextDrawable(R.mipmap.icon_nav_back)
                .setOnLeftTextClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (getPreFragment() != null) {
                            pop();
                        } else {
                            getActivity().finish();
                        }
                    }
                }).setRightText("积分商城").setOnRightTextClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TMBaseFragment fragment = null;
                fragment = (TMBaseFragment) ARouter.getInstance().build(Tmyx02RouterConfig.TMYX02_JFSHOP)
                        .navigation();
                start(fragment);
            }
        });
        boolean isSign = (boolean) SPUtils.get(getContext(), "isSign", false);
        switchView.setOpened(isSign);
        switchView.setOpenColor(Color.GREEN);
        switchView.setOnStateChangedListener(new SwitchView.OnStateChangedListener() {
            @Override
            public void toggleToOn(View view) {
                switchView.setOpened(true);
                SPUtils.put(getContext(), "isSign", true);
                remind("1");
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("");
                builder.setMessage("提醒开启成功, 每日21:00进行提醒请确保手机通知功能处于开启状态！");
                AlertDialog alertDialog = builder.setPositiveButton("知道了", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).create();
                alertDialog.show();
                alertDialog.getButton(-1).setTextColor(getResources().getColor(R.color.blue_primary));
            }

            @Override
            public void toggleToOff(View view) {
                switchView.setOpened(false);
                remind("0");
                SPUtils.put(getContext(), "isSign", false);
            }
        });

        LinearLayoutManager layout = new LinearLayoutManager(getThisContext());
        layout.setOrientation(LinearLayoutManager.HORIZONTAL);
        mlist.setLayoutManager(layout);
        adapter = new QdAdapter(new ArrayList<>());
        mlist.setAdapter(adapter);
        RadiusTextViewDelegate delegate = tv_account.getDelegate();
        if (TextUtils.isEmpty(tmUser.getMobile())) {
            delegate.setBackgroundColor(getResources().getColor(R.color.white));
            delegate.setTextColor(getResources().getColor(R.color.blue_primary));
            tv_account.setText("去绑定");
        } else {
            delegate.setBackgroundColor(getResources().getColor(R.color.textcolor01));
            delegate.setTextColor(getResources().getColor(R.color.white));
            tv_account.setText("已绑定");
        }
        String sex = tmUser.getSex();
        String birthday = tmUser.getBirthday();
        String mobile = tmUser.getMobile();
        String wb = tmUser.getWb();
        String wx = tmUser.getWx();
        String qq = tmUser.getQq();
        RadiusTextViewDelegate delegate1 = tv_userinfo.getDelegate();
        boolean b = !TextUtils.isEmpty(sex) && !TextUtils.isEmpty(birthday) && !TextUtils.isEmpty(mobile) && !TextUtils.isEmpty(wb) && !TextUtils.isEmpty(wx) && !TextUtils.isEmpty(qq);
        if (b) {
            delegate1.setBackgroundColor(getResources().getColor(R.color.textcolor01));
            delegate1.setTextColor(getResources().getColor(R.color.white));
            tv_account.setText("已完成");
        }





    }

    private void isSign() {
        HashMap<String, Object> parms = new HashMap<>();
        String value = new Gson().toJson(parms);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), value);
        mPresenter.isSign(body);
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        initList();
        getMyPoint();
        isSign();
        isRemind();
        AllBindService();
        BindScore();
        loginscore();
    }
    private void loginscore() {
        HashMap<String, Object> parms = new HashMap<>();
        String value = new Gson().toJson(parms);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), value);
        mPresenter.loginscore(body);
    }

    private void BindScore() {
        HashMap<String, Object> parms = new HashMap<>();
        String value = new Gson().toJson(parms);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), value);
        mPresenter.BindScore(body);
    }

    private void AllBindService() {
        HashMap<String, Object> parms = new HashMap<>();
        String value = new Gson().toJson(parms);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), value);
        mPresenter.AllBindService(body);
    }

    private void isRemind() {
        HashMap<String, Object> parms = new HashMap<>();
        String value = new Gson().toJson(parms);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), value);
        mPresenter.isRemind(body);
    }

    private void remind(String statue) {
        HashMap<String, Object> parms = new HashMap<>();
        parms.put("status", statue);
        String value = new Gson().toJson(parms);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), value);
        mPresenter.remind(body);
    }

    private void getMyPoint() {
        HashMap<String, Object> parms = new HashMap<>();
        mPresenter.getMyPoint(parms);
    }

    @OnClick({R2.id.tv_userinfo, R2.id.tv_account, R2.id.tv_signrule, R2.id.tv_sign,R2.id.tv_login})
    public void onClick(View view) {
        TMBaseFragment fragment = null;
        if (view.getId() == R.id.tv_userinfo) {
            String sex = tmUser.getSex();
            String birthday = tmUser.getBirthday();
            String mobile = tmUser.getMobile();
            String wb = tmUser.getWb();
            String wx = tmUser.getWx();
            String qq = tmUser.getQq();
            boolean b = !TextUtils.isEmpty(sex) && !TextUtils.isEmpty(birthday) && !TextUtils.isEmpty(mobile) && !TextUtils.isEmpty(wb) && !TextUtils.isEmpty(wx) && !TextUtils.isEmpty(qq);
            if (is_allbind) {
                Intent intent = new Intent(getActivity().getPackageName() + ".usercenter.UCPersonal");
                startActivity(intent);
            }

        } else if (view.getId() == R.id.tv_account) {
            String mobile = tmUser.getMobile();
            if (is_bind) {
                Intent intent = new Intent(getActivity().getPackageName() + ".usercenter.bindingMobile");
                startActivity(intent);
            }
        } else if (view.getId() == R.id.tv_signrule) {
            fragment = (TMBaseFragment) ARouter.getInstance().build(Tmyx02RouterConfig.TMYX02_SIGNRULE)
                    .navigation();
            start(fragment);
        } else if (view.getId() == R.id.tv_sign) {
            HashMap<String, Object> parms = new HashMap<>();
            mPresenter.Sign(parms);
        }else if(view.getId()==R.id.tv_login){
            if(!is_bindlogin){
                lqJf();
            }
        }

    }
    private void lqJf() {
        HashMap<String, Object> parms = new HashMap<>();
        String value = new Gson().toJson(parms);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), value);
        mPresenter.loginLog(body);
    }

    private void initList() {
        HashMap<String, Object> parms = new HashMap<>();
        mPresenter.getSignList(parms);
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
                        case "getMyPoint":
                            JsonObject jsonObject = GsonUtil.GsonToBean(object, JsonObject.class);
                            int point = jsonObject.get("point").getAsInt();
                            tv_mypoint.setText(String.valueOf(point));
                            break;
                        case "getSignList":

                            ListSignBean listSignBean = GsonUtil.GsonToBean(object, ListSignBean.class);
                            List<ListSignBean.ListBean> list = listSignBean.getList();
                            for (int i = 0; i < list.size(); i++) {
                                boolean is_sign = list.get(i).isIs_sign();
                                if (!is_sign) {
                                    score = list.get(i).getScore();
                                    break;
                                }
                            }
                            adapter.setNewData(list);
                            break;
                        case "isSign":
                            JsonObject jsonObject1 = GsonUtil.GsonToBean(object, JsonObject.class);
                            is_sign = jsonObject1.get("is_sign").getAsBoolean();
                            day = jsonObject1.get("day").getAsInt();
                            if (!is_sign) {
                                tv_sign.setClickable(true);
                            } else {
                                tv_sign.setClickable(false);
                            }

                            break;
                        case "AllBindService":
                            JsonObject jsonObject3 = GsonUtil.GsonToBean(object, JsonObject.class);
                            is_allbind = jsonObject3.get("is_allbind").getAsBoolean();
                            int all_score = jsonObject3.get("all_score").getAsInt();
                            tv2.setText("+"+all_score+"");
                            RadiusTextViewDelegate delegate1 = tv_userinfo.getDelegate();
                            if(is_allbind){
                                delegate1.setBackgroundColor(getResources().getColor(R.color.textcolor01));
                                delegate1.setTextColor(getResources().getColor(R.color.white));
                                tv_userinfo.setText("已领取");
                            }
                            break;
                        case "BindScore":
                            JsonObject jsonObject4 = GsonUtil.GsonToBean(object, JsonObject.class);
                            is_bind = jsonObject4.get("is_bind").getAsBoolean();
                            String scores = jsonObject4.get("score").getAsString();
                            tv3.setText("+"+scores);
                            RadiusTextViewDelegate delegate = tv_account.getDelegate();
                            if(is_bind){
                                delegate.setBackgroundColor(getResources().getColor(R.color.textcolor01));
                                delegate.setTextColor(getResources().getColor(R.color.white));
                                tv_account.setText("已领取");
                            }
                            break;
                        case "loginscore":
                            JsonObject jsonObject5 = GsonUtil.GsonToBean(object, JsonObject.class);
                            is_bindlogin = jsonObject5.get("is_login").getAsBoolean();

                            JsonElement score = jsonObject5.get("score");
                            if(score!=null){
                                int scoreslogin = score.getAsInt();
                                tv4.setText("+"+scoreslogin);
                                RadiusTextViewDelegate delegate2 = tv_login.getDelegate();
                                if(is_bindlogin){
                                    tv_login.setText("已领取");
                                    delegate2.setBackgroundColor(getResources().getColor(R.color.textcolor01));
                                    delegate2.setTextColor(getResources().getColor(R.color.white));
                                }
                            }

                            break;
                        case "loginLog":
                            loginscore();
                            getMyPoint();
                            break;
                        case "Sign":
                            initList();
                            getMyPoint();
                            isSign();

                            View inflate = LayoutInflater.from(getThisContext()).inflate(R.layout.custom_sign_zp02yx_bwusb, null, false);
                            Dialog loadingDialog = new Dialog(getActivity(), com.system.uilibrary.R.style.MyDialogStyle);
                            RelativeLayout layout = (RelativeLayout) inflate.findViewById(R.id.re_main);
                            TextView tv_score = inflate.findViewById(R.id.tv_score);
                            TextView tv_days = inflate.findViewById(R.id.tv_days);
                            tv_score.setText("+" + this.score);
                            tv_days.setText("已连续签到" + (day + 1) + "天");
                            loadingDialog.setCancelable(true);
                            loadingDialog.setCanceledOnTouchOutside(true);
                            loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(-1, -1));
                            Window window = loadingDialog.getWindow();
                            android.view.WindowManager.LayoutParams lp = window.getAttributes();
                            lp.width = -1;
                            lp.height = -2;
                            window.setGravity(17);
                            window.setAttributes(lp);
                            window.setWindowAnimations(com.system.uilibrary.R.style.PopWindowAnimStyle);
                            loadingDialog.show();
                            break;
                        case "isRemind":
                            try {
                                if(object!=null){
                                    JsonObject jsonObject2 = GsonUtil.GsonToBean(object, JsonObject.class);
                                    if (jsonObject2.has("is_remind")) {
                                        boolean isSign = jsonObject2.get("is_remind").getAsBoolean();
                                        SPUtils.put(getContext(), "isSign", isSign);
                                    }
                                }
                            } catch (Exception e) {

                            }
                            break;
                        case "remind":
                            isRemind();
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
