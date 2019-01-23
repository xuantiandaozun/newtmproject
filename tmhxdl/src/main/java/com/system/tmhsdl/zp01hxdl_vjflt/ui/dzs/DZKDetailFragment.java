package com.system.tmhsdl.zp01hxdl_vjflt.ui.dzs;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.system.myproject.base.MVPBaseFragment;
import com.system.myproject.base.TMBaseFragment;
import com.system.myproject.utils.GsonUtil;
import com.system.myproject.utils.ToastUtil;
import com.system.tmhsdl.zp01hxdl_vjflt.HxdlRouterConfig;
import com.system.tmhsdl.zp01hxdl_vjflt.R;
import com.system.tmhsdl.zp01hxdl_vjflt.R2;
import com.system.tmhsdl.zp01hxdl_vjflt.bean.EventCallBackBean;
import com.system.tmhsdl.zp01hxdl_vjflt.ui.dzs.adapter.Adapter;
import com.system.tmhsdl.zp01hxdl_vjflt.ui.dzs.bean.InssBean;
import com.system.tmhsdl.zp01hxdl_vjflt.ui.dzs.bean.InssDetail;
import com.system.tmhsdl.zp01hxdl_vjflt.ui.dzs.contract.DzsContract;
import com.system.tmhsdl.zp01hxdl_vjflt.ui.dzs.presenter.DzsPresenter;
import com.system.uilibrary.views.titlebar.TitleBarView;
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
import recycler.coverflow.CoverFlowLayoutManger;
import recycler.coverflow.RecyclerCoverFlow;


@Route(path = HxdlRouterConfig.HXDL_DZKDETAIL)
public class DZKDetailFragment extends MVPBaseFragment<DzsContract.View, DzsPresenter>
        implements DzsContract.View {
    @BindView(R2.id.titleBar)
    TitleBarView titleBar;
    @BindView(R2.id.list)
    RecyclerCoverFlow mlist;
    @BindView(R2.id.iv)
    SimpleDraweeView iv;
    @BindView(R2.id.tv_time)
    TextView tv_time;
    @BindView(R2.id.tv_money)
    TextView tv_money;
    @BindView(R2.id.tv_name)
    TextView tv_name;
    @BindView(R2.id.btn_next)
    TextView btn_next;
    @BindView(R2.id.iv_ad)
    ImageView iv_ad;
    @Autowired(name = "params")
    public String params;
    private int width;
    private InssBean.ListBean listBean;
    private Adapter adapter;
    private boolean is_buy;
    private InssDetail detail;
    private int paytype;
    private int themeColor;
    private int textcolor;

    @Override
    protected DzsPresenter createPresenter() {
        return new DzsPresenter();
    }

    @Override
    protected void init() {
        HashMap<String, Object> filter = getHashMapByParams(params);
        if (filter != null && filter.containsKey("detail")) {
            String detail = filter.get("detail").toString();
            listBean = GsonUtil.GsonToBean(detail, InssBean.ListBean.class);
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.hxdl_fragment_dzsdetail;
    }

    @Override
    protected void initDatas() {
        themeColor = Color.parseColor(TMSharedPUtil.getTMThemeColor(getActivity()));
        textcolor = Color.parseColor(TMSharedPUtil.getTMTitleTextColor(getActivity()));
        titleBar.setBackgroundColor(themeColor);
        titleBar.setTitleMainTextColor(textcolor);

        titleBar.setTitleMainText("刊物详情")
                .setLeftTextDrawable(R.mipmap.icon_nav_back)
                .setOnLeftTextClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(getPreFragment()!=null){
                            pop();
                        }else {
                            getActivity().finish();
                        }
                    }
                });

        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        width=dm.widthPixels;



    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();

        getDetail();
    }

    private void getDetail() {
        HashMap<String, Object> parms = new HashMap<>();
        parms.put("id",listBean.getId());
        String tmToken = TMSharedPUtil.getTMToken(getContext());
        if(TextUtils.isEmpty(tmToken)){
            mPresenter.getIssnDetail(parms);

        }else {
            mPresenter.getIssnDetail2(parms);

        }
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);

        adapter = new Adapter(getActivity(),new ArrayList<>());
       mlist.setAdapter(adapter);
        mlist.setOnItemSelectedListener(new CoverFlowLayoutManger.OnSelected() {
            @Override
            public void onItemSelected(int position) {
            }
        });
    }

    @OnClick({R2.id.btn_next,R2.id.iv})
    public void onClick(View view){
        TMBaseFragment fragment=null;
        if(view.getId()==R.id.btn_next){
            String tmToken = TMSharedPUtil.getTMToken(getContext());
            if(TextUtils.isEmpty(tmToken)){
                Intent intent = new Intent(getActivity().getPackageName() + ".usercenter.login");
                getActivity().startActivity(intent);
                return;
            }
            if(!is_buy){
                if(!TextUtils.isEmpty(tmToken)){
                   String price = detail.getPrice();
                    TMPayUtil.showPayDialog(getThisContext(), Float.valueOf(price), new TMPayUtil.PayTypeSelect() {
                        @Override
                        public void selectPayType(int payType) {
                            if(payType!=-1){
                                DZKDetailFragment.this.paytype=payType;
                                getPay(payType);
                            }

                        }
                    });
                 /*   View inflate = LayoutInflater.from(getThisContext()).inflate(R.layout.custom_pay, null, false);
                    Dialog loadingDialog = new Dialog(getContext(), com.system.uilibrary.R.style.MyDialogStyle);
                    RelativeLayout layout = (RelativeLayout)inflate.findViewById(R.id.re_main);
                    ImageView close = (ImageView) inflate.findViewById(R.id.iv_close);
                    ImageView iv = (ImageView) inflate.findViewById(R.id.iv);
                    TextView tv_name = (TextView) inflate.findViewById(R.id.tv_name);
                    TextView tv_price = (TextView) inflate.findViewById(R.id.tv_price);
                    TextView tv_wx = (TextView) inflate.findViewById(R.id.tv_wx);
                    TextView tv_zfb = (TextView) inflate.findViewById(R.id.tv_zfb);
                    close.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            loadingDialog.dismiss();
                        }
                    });
                    tv_wx.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            getPay(2);
                            loadingDialog.dismiss();
                        }
                    });
                    tv_zfb.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            getPay(1);
                            loadingDialog.dismiss();
                        }
                    });

                    tv_name.setText(listBean.getTitle());
                    tv_price.setText("￥"+detail.getPrice());
                    Glide.with(getContext()).load(TMSharedPUtil.getTMBaseConfig(mcontext).getDomain()+listBean.getImage()).into(iv);


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
                    loadingDialog.show();*/
                }

            }else {
                List<InssDetail.ImageListBean> image_list = detail.getImage_list();

                HashMap<String, String> param = new HashMap<>();
                param.put("id",listBean.getId()+"");
                param.put("type","1");

                fragment = (TMBaseFragment) ARouter.getInstance()
                        .build(HxdlRouterConfig.HXDL_IMAGIVE)
                        .withString("params",GsonUtil.GsonString(param))
                        .navigation();
                start(fragment);
            }

        }
    }

    private void getPay(int type) {
        HashMap<String, String> parms = new HashMap<>();
        parms.put("type", type+"");
        parms.put("sid", "1");
        parms.put("id", listBean.getId()+"");
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
                        case "getIssnDetail":
                            detail = GsonUtil.GsonToBean(object, InssDetail.class);
                            is_buy = detail.isIs_buy();
                            if(is_buy){
                                btn_next.setText("查看");
                            }else {
                                String tmToken = TMSharedPUtil.getTMToken(getContext());
                                if(TextUtils.isEmpty(tmToken)){
                                    btn_next.setText("请登录");
                                }else {
                                    btn_next.setText("购买");
                                }
                            }
                            iv.setImageURI(TMSharedPUtil.getTMBaseConfig(getContext()).getDomain()+listBean.getImage());
                            tv_time.setText(detail.getIssn());
                            tv_money.setText("￥"+detail.getPrice());
                            tv_name.setText(detail.getTitle());
                            List<InssDetail.ImageListBean> image_list = detail.getImage_list();
                            if(image_list!=null&&image_list.size()!=0){

                                mlist.setVisibility(View.VISIBLE);
                                adapter.setNewDatas(image_list);

                            }
                            Glide.with(getContext()).load(TMSharedPUtil.getTMBaseConfig(getContext()).getDomain()+ detail.getAd()).into(iv_ad);
                            break;
                        case "getPay":
                            try {
                                TMPayUtil.gotoPay(getContext(), paytype, object, new TMPayUtil.PayResult() {
                                    @Override
                                    public void paySuccess() {
                                        ToastUtil.showSnack(getContext(),"支付成功！");
                                        getDetail();
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
        ToastUtil.showSnack(getThisContext(),message);
    }
}
