package com.system.tmhsdl.zp01hxdl_vjflt.ui.dzs;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.aries.ui.view.radius.RadiusTextView;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.library.flowlayout.SpaceItemDecoration;
import com.system.myproject.base.BaseFragment;
import com.system.myproject.utils.GsonUtil;
import com.system.myproject.utils.ToastUtil;
import com.system.myproject.utils.UEMethod;
import com.system.tmhsdl.zp01hxdl_vjflt.HxdlRouterConfig;
import com.system.tmhsdl.zp01hxdl_vjflt.R;
import com.system.tmhsdl.zp01hxdl_vjflt.R2;
import com.system.tmhsdl.zp01hxdl_vjflt.bean.EventCallBackBean;
import com.system.tmhsdl.zp01hxdl_vjflt.ui.dzs.adapter.DYAdapter;
import com.system.tmhsdl.zp01hxdl_vjflt.ui.dzs.bean.DyBean;
import com.system.tmhsdl.zp01hxdl_vjflt.ui.dzs.bean.InssBean;
import com.system.tmhsdl.zp01hxdl_vjflt.ui.dzs.contract.DzsContract;
import com.system.tmhsdl.zp01hxdl_vjflt.ui.dzs.presenter.DzsPresenter;
import com.tenma.ventures.bean.utils.TMPayUtil;
import com.tenma.ventures.bean.utils.TMSharedPUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.RequestBody;

@Route(path = HxdlRouterConfig.HXDL_DY)
public class DYFragment extends BaseFragment<DzsContract.View, DzsPresenter>
        implements DzsContract.View {
    @BindView(R2.id.mlist)
    RecyclerView mlist;
    @BindView(R2.id.iv)
    ImageView iv;
    @BindView(R2.id.tv_price)
    TextView tv_price;
    @BindView(R2.id.tv_bottom_price)
    TextView tv_bottom_price;
    @BindView(R2.id.ret_radius)
    RadiusTextView ret_radius;
    @BindView(R2.id.ll)
    LinearLayout ll;
    @BindView(R2.id.llprice)
    LinearLayout llprice;
    private DYAdapter adapter;
    private boolean is_buy;
    private DyBean dyBean;
    private int paytype;

    @Override
    protected DzsPresenter createPresenter() {
        return new DzsPresenter();
    }

    @Override
    protected void init() {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.hxdl_fragment_dy;
    }

    @Override
    protected void initDatas() {
        mlist.setLayoutManager(new GridLayoutManager(getThisContext(), 2));
        mlist.addItemDecoration(new SpaceItemDecoration(UEMethod.dp2px(getThisContext(), 10)));
        adapter = new DYAdapter(new ArrayList<>());
        mlist.setAdapter(adapter);
        mlist.setNestedScrollingEnabled(false);
        initList();
    }

    private void initList() {
        HashMap<String, Object> parms = new HashMap<>();
        mPresenter.getApiIndex(parms);
        mPresenter.getRecommendList(parms);
    }
    @OnClick({R2.id.iv,R2.id.tv_pay})
    public void onClick(View view) {
        if(view.getId()==R.id.iv||view.getId()==R.id.tv_pay){
            String tmToken = TMSharedPUtil.getTMToken(getContext());
            if(!TextUtils.isEmpty(tmToken)) {
                String price = dyBean.getPrice();
                TMPayUtil.showPayDialog(getThisContext(), Float.valueOf(price), new TMPayUtil.PayTypeSelect() {
                    @Override
                    public void selectPayType(int payType) {
                        if(payType>0){
                            DYFragment.this.paytype = payType;
                            getPay(payType);
                        }

                    }
                });
            }
        }
    }
    private void getPay(int type) {
        HashMap<String, String> parms = new HashMap<>();
        parms.put("type", type+"");
        parms.put("sid", "3");
        String value = new Gson().toJson(parms);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), value);
        mPresenter.getPay(body);
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
                        case "getApiIndex":
                            dyBean = GsonUtil.GsonToBean(object, DyBean.class);
                            is_buy = dyBean.isIs_buy();
                            if (is_buy) {
                                ret_radius.setVisibility(View.VISIBLE);
                                ll.setVisibility(View.VISIBLE);
                                ret_radius.setText(dyBean.getStart_time() + "至" + dyBean.getEnd_time());
                                llprice.setVisibility(View.GONE);
                            } else {
                                tv_bottom_price.setText("小计:￥"+dyBean.getPrice());
                                llprice.setVisibility(View.VISIBLE);
                                ret_radius.setVisibility(View.GONE);
                                ll.setVisibility(View.GONE);
                            }
                            tv_price.setText(dyBean.getPrice());
                            Glide.with(getContext()).load(TMSharedPUtil.getTMBaseConfig(getContext()).getDomain() + dyBean.getImage()).into(iv);
                            break;
                        case "getRecommendList":
                            InssBean inssBean = GsonUtil.GsonToBean(object, InssBean.class);
                            List<InssBean.ListBean> list = inssBean.getList();
                            adapter.setNewData(list);
                            break;
                        case "getPay":
                            try {
                                TMPayUtil.gotoPay(getContext(), paytype, object, new TMPayUtil.PayResult() {
                                    @Override
                                    public void paySuccess() {
                                        ToastUtil.showSnack(getContext(),"支付成功！");
                                        initList();
                                    }

                                    @Override
                                    public void payFailed() {

                                    }

                                    @Override
                                    public void payCancel() {

                                    }
                                });
                            }catch (Exception e){
                                System.out.println();
                            }

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
        //ToastUtil.showSnack(getThisContext(), message);
    }


}
