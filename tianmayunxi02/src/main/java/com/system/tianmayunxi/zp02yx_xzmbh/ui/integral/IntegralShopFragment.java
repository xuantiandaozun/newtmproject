package com.system.tianmayunxi.zp02yx_xzmbh.ui.integral;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
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
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.system.myproject.base.MVPBaseFragment;
import com.system.myproject.base.TMBaseFragment;
import com.system.myproject.utils.GsonUtil;
import com.system.myproject.utils.ToastUtil;
import com.system.myproject.utils.UEMethod;
import com.system.tianmayunxi.zp02yx_xzmbh.R;
import com.system.tianmayunxi.zp02yx_xzmbh.R2;
import com.system.tianmayunxi.zp02yx_xzmbh.Tmyx02RouterConfig;
import com.system.tianmayunxi.zp02yx_xzmbh.bean.EventCallBackBean;
import com.system.tianmayunxi.zp02yx_xzmbh.ui.integral.adapter.IntegralShopAdapter;
import com.system.tianmayunxi.zp02yx_xzmbh.ui.officialrecommend.bean.GoodsListBean;
import com.system.tianmayunxi.zp02yx_xzmbh.ui.officialrecommend.contract.OfficContract;
import com.system.tianmayunxi.zp02yx_xzmbh.ui.officialrecommend.presenter.OfficPresenter;
import com.system.uilibrary.dialog.DialogsTools;
import com.system.uilibrary.views.recyclerview.decoration.SpaceItemDecoration;
import com.system.uilibrary.views.titlebar.TitleBarView;
import com.tenma.ventures.bean.TMUser;
import com.tenma.ventures.bean.utils.TMSharedPUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import okhttp3.RequestBody;

@Route(path = Tmyx02RouterConfig.TMYX02_JFSHOP)
public class IntegralShopFragment extends MVPBaseFragment <OfficContract.View, OfficPresenter>
        implements OfficContract.View{
    @BindView(R2.id.titleBar)
    TitleBarView titleBar;
    @BindView(R2.id.mlist)
    RecyclerView mlist;
    @BindView(R2.id.tv_mypoint)
    TextView tv_mypoint;
    private IntegralShopAdapter adapter;
    private int themeColor;
    private int textcolor;

    @Override
    protected OfficPresenter createPresenter() {
        return new OfficPresenter();
    }

    @Override
    protected void init() {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_integralshop_zp01yx_bwusb2;
    }

    @Override
    protected void initDatas() {
        themeColor = Color.parseColor(TMSharedPUtil.getTMThemeColor(getActivity()));
        textcolor = Color.parseColor(TMSharedPUtil.getTMTitleTextColor(getActivity()));
        titleBar.setBackgroundColor(themeColor);
        titleBar.setTitleMainTextColor(textcolor);

        titleBar.setTitleMainText("积分商城")
                .setLeftTextDrawable(R.mipmap.icon_nav_back)
                .setOnLeftTextClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pop();
                    }
                });
        mlist.setLayoutManager(new GridLayoutManager(getThisContext(),2));
        mlist.addItemDecoration(new SpaceItemDecoration(UEMethod.dp2px(getThisContext(),8)));
        adapter = new IntegralShopAdapter(new ArrayList<>());
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int i = view.getId();
                GoodsListBean.ListBean beans = (GoodsListBean.ListBean) adapter.getItem(position);

                if (i == R.id.btn_yuehuan) {
                    View inflate = LayoutInflater.from(getContext()).inflate(R.layout.custom_info_zp02yx_bwusb, null, false);
                    Dialog loadingDialog = new Dialog(getContext(), R.style.MyDialogStyle);
                    RelativeLayout layout = (RelativeLayout)inflate.findViewById(R.id.re_main);
                    TextView tv_next = (TextView)inflate.findViewById(R.id.tv_next);
                    tv_next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            TMUser tmUser = TMSharedPUtil.getTMUser(getContext());

                            String mobile = tmUser.getMobile();
                            if (TextUtils.isEmpty(mobile)) {
                                Intent intent = new Intent(getActivity().getPackageName() + ".usercenter.bindingMobile");
                                startActivity(intent);
                                return;
                            }

                            int is_virtual = beans.getIs_virtual();
                            if(is_virtual==0){
                                HashMap<String, String> param = new HashMap<>();
                                param.put("detail",GsonUtil.GsonString(beans));
                                TMBaseFragment fragment = (TMBaseFragment) ARouter.getInstance().build(Tmyx02RouterConfig.TMYX02_ADDADDRESS)
                                        .withString("params", GsonUtil.GsonString(param))
                                        .navigation();
                                start(fragment);
                            }else {
                                exchangeShop(beans);
                            }

                            loadingDialog.dismiss();
                        }
                    });
                    TextView tv_cancel = (TextView)inflate.findViewById(R.id.tv_cancel);
                    tv_cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            loadingDialog.dismiss();
                        }
                    });
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
                }
            }
        });
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View views, int position) {
                GoodsListBean.ListBean item = (GoodsListBean.ListBean) adapter.getItem(position);
                TMBaseFragment fragment=null;
                HashMap<String, String> param = new HashMap<>();
                param.put("detail",GsonUtil.GsonString(item));
                fragment = (TMBaseFragment) ARouter.getInstance().build(Tmyx02RouterConfig.TMYX02_SHOPDETAIL)
                        .withString("params",GsonUtil.GsonString(param))
                        .navigation();
                start(fragment);
            }
        });
        mlist.setAdapter(adapter);
        initList();
        getMyPoint();
    }
    private void exchangeShop(GoodsListBean.ListBean beans) {
        HashMap<String, String> parms = new HashMap<>();
        parms.put("id", beans.getId() + "");
        String value = new Gson().toJson(parms);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), value);
        mPresenter.exchangeGoods(body);
    }
    private void getMyPoint() {
        HashMap<String, Object> parms = new HashMap<>();
        mPresenter.getMyPoint(parms);
    }

    private void initList() {
        HashMap<String, Object> parms = new HashMap<>();
        mPresenter.getGoodsList(parms);
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
                        case "getGoodsList":
                            GoodsListBean goodsListBean = GsonUtil.GsonToBean(object, GoodsListBean.class);
                            List<GoodsListBean.ListBean> list = goodsListBean.getList();
                            adapter.setNewData(list);
                            break;
                        case "getMyPoint":
                            JsonObject jsonObject = GsonUtil.GsonToBean(object, JsonObject.class);
                            int point = jsonObject.get("point").getAsInt();
                            tv_mypoint.setText("我的积分: "+String.valueOf(point));
                            break;
                        case "exchangeGoods":
                            TMBaseFragment fragment = (TMBaseFragment) ARouter.getInstance().build(Tmyx02RouterConfig.TMYX02_DHJG)
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
        ToastUtil.showSnack(getThisContext(),message);
    }
}
