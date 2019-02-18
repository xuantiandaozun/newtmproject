package com.system.tianmayunxi.zp02yx_xzmbh.ui.integral;

import android.graphics.Color;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import com.aries.ui.view.radius.RadiusTextView;
import com.system.myproject.base.MVPBaseFragment;
import com.system.myproject.base.TMBaseFragment;
import com.system.myproject.utils.GsonUtil;
import com.system.myproject.utils.ToastUtil;
import com.system.tianmayunxi.zp02yx_xzmbh.R;
import com.system.tianmayunxi.zp02yx_xzmbh.R2;
import com.system.tianmayunxi.zp02yx_xzmbh.Tmyx02RouterConfig;
import com.system.tianmayunxi.zp02yx_xzmbh.bean.EventCallBackBean;
import com.system.tianmayunxi.zp02yx_xzmbh.ui.integral.adapter.ShopDetailAdapter;
import com.system.tianmayunxi.zp02yx_xzmbh.ui.officialrecommend.bean.GoodsDetailBean;
import com.system.tianmayunxi.zp02yx_xzmbh.ui.officialrecommend.bean.GoodsListBean;
import com.system.tianmayunxi.zp02yx_xzmbh.ui.officialrecommend.contract.OfficContract;
import com.system.tianmayunxi.zp02yx_xzmbh.ui.officialrecommend.presenter.OfficPresenter;
import com.system.tianmayunxi.zp02yx_xzmbh.utils.GlideImageLoader;
import com.system.uilibrary.views.titlebar.TitleBarView;
import com.tenma.ventures.bean.utils.TMSharedPUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = Tmyx02RouterConfig.TMYX02_SHOPDETAIL)
public class ShopDetailFragment extends MVPBaseFragment<OfficContract.View, OfficPresenter>
        implements OfficContract.View {
    @BindView(R2.id.titleBar)
    TitleBarView titleBar;
    @BindView(R2.id.convenientBanner)
    Banner convenientBanner;
    @BindView(R2.id.mlist)
    WebView mlist;
    @BindView(R2.id.tv_account)
    RadiusTextView tv_account;
    @BindView(R2.id.tv_shopname)
    TextView tv_shopname;
    @Autowired(name = "params")
    public String params;
    private ShopDetailAdapter adapter;
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
        return R.layout.fragment_shopdetail_zp01yx_bwusb2;
    }

    @Override
    protected void initDatas() {
        themeColor = Color.parseColor(TMSharedPUtil.getTMThemeColor(getActivity()));
        textcolor = Color.parseColor(TMSharedPUtil.getTMTitleTextColor(getActivity()));
        titleBar.setBackgroundColor(themeColor);
        titleBar.setTitleMainTextColor(textcolor);
        titleBar.setTitleMainText("商品详情")
                .setLeftTextDrawable(R.mipmap.icon_nav_back)
                .setOnLeftTextClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pop();
                    }
                });
        //设置图片加载器
        convenientBanner.setImageLoader(new GlideImageLoader());
        mlist.setWebViewClient(new WebViewClient());// 设置 WebViewClient
        mlist.setWebChromeClient(new WebChromeClient());// 设置 WebChromeClient
        WebSettings webSettings = mlist.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
        initList();
        initList();
    }
    @OnClick({R2.id.tv_next})
    public void onClick(View view){
        TMBaseFragment fragment=null;
        int id = view.getId();
        if(id==R.id.tv_next){
            HashMap<String, String> param = new HashMap<>();
            param.put("detail",GsonUtil.GsonString(beans));
            fragment = (TMBaseFragment) ARouter.getInstance().build(Tmyx02RouterConfig.TMYX02_ADDADDRESS)
                    .withString("params",GsonUtil.GsonString(param))
                    .navigation();
            start(fragment);
        }
    }

    private void initList() {
        HashMap<String, Object> parms = new HashMap<>();
        parms.put("id",beans.getId());
        mPresenter.GoodsDetail(parms);
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
                        case "GoodsDetail":
                            GoodsDetailBean goodsDetailBean = GsonUtil.GsonToBean(object, GoodsDetailBean.class);
                            tv_shopname.setText(goodsDetailBean.getGoods_name());
                            tv_account.setText(goodsDetailBean.getScore()+"积分");
                            mlist.loadDataWithBaseURL( null, goodsDetailBean.getGoods_content() , "text/html", "UTF-8", null ) ;
                            List<GoodsDetailBean.ImageListBean> image_list = goodsDetailBean.getImage_list();
                            int size=0;
                            List<String> images = null;
                            if (images == null&&image_list!=null&&image_list.size()!=0) {
                                images = new ArrayList<>();
                                for (int i=0;i<image_list.size();i++){
                                    String img = image_list.get(i).getImage();
                                    images.add(img);
                                }
                            }
                            //设置图片集合
                            convenientBanner.setImages(images);
                            convenientBanner.setBannerStyle(BannerConfig.NUM_INDICATOR);
                            convenientBanner.isAutoPlay(false);
                            //banner设置方法全部调用完毕时最后调用
                            convenientBanner.start();

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
