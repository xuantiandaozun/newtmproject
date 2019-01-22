package com.system.tianmayunxi.zp01yx_bwusb.ui.officialrecommend;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;
import com.system.myproject.base.MVPBaseFragment;
import com.system.myproject.base.TMBaseFragment;
import com.system.myproject.utils.GsonUtil;
import com.system.myproject.utils.ToastUtil;
import com.system.tianmayunxi.zp01yx_bwusb.BuildConfig;
import com.system.tianmayunxi.zp01yx_bwusb.R;
import com.system.tianmayunxi.zp01yx_bwusb.R2;
import com.system.tianmayunxi.zp01yx_bwusb.TmyxConstant;
import com.system.tianmayunxi.zp01yx_bwusb.TmyxRouterConfig;
import com.system.tianmayunxi.zp01yx_bwusb.bean.EventCallBackBean;
import com.system.tianmayunxi.zp01yx_bwusb.ui.officialrecommend.adapter.UltraPagerAdapter;
import com.system.tianmayunxi.zp01yx_bwusb.ui.officialrecommend.bean.ArticleDetail;
import com.system.tianmayunxi.zp01yx_bwusb.ui.officialrecommend.bean.TieZiBean;
import com.system.tianmayunxi.zp01yx_bwusb.ui.officialrecommend.contract.OfficContract;
import com.system.tianmayunxi.zp01yx_bwusb.ui.officialrecommend.presenter.OfficPresenter;
import com.tenma.ventures.bean.TMUser;
import com.tenma.ventures.bean.utils.TMSharedPUtil;
import com.tenma.ventures.share.bean.TMLinkShare;
import com.tenma.ventures.share.util.TMShareUtil;
import com.tmall.ultraviewpager.UltraViewPager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.RequestBody;

@Route(path = TmyxRouterConfig.TMYX_CKIMAGE)
public class CheckImageFrgment extends MVPBaseFragment <OfficContract.View, OfficPresenter>
        implements OfficContract.View{
    @BindView(R2.id.ultra_viewpager)
    UltraViewPager ultraViewPager;
    @BindView(R2.id.head)
    SimpleDraweeView head;
    @BindView(R2.id.tv_title)
    TextView tv_title;
    @BindView(R2.id.tv_indicator)
    TextView tv_indicator;
    @BindView(R2.id.tv_dy)
    TextView tv_dy;
    @BindView(R2.id.titleBar)
    LinearLayout titleBar;
    @Autowired(name = "params")
    public String params;
    private UltraPagerAdapter adapter;
    private TieZiBean beans;
    private int tid;
    private int themeColor;
    private ArticleDetail articleDetail;
    private int textcolor;
    private double star_id;

    @Override
    protected OfficPresenter createPresenter() {
        return new OfficPresenter();
    }

    @Override
    protected void init() {
        HashMap<String, Object> filter = getHashMapByParams(params);
        if (filter != null && filter.containsKey("detail")) {
            String detail = filter.get("detail").toString();
            beans = GsonUtil.GsonToBean(detail, TieZiBean.class);
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_checkimage_zp01yx_bwusb;
    }

    @Override
    protected void initDatas() {
        themeColor = Color.parseColor(TMSharedPUtil.getTMThemeColor(getActivity()));
        textcolor = Color.parseColor(TMSharedPUtil.getTMTitleTextColor(getActivity()));
        titleBar.setBackgroundColor(themeColor);

        titleBar.setBackgroundColor(themeColor);
        tv_title.setTextColor(textcolor);

        head.setImageURI(TMSharedPUtil.getTMBaseConfig(getContext()).getDomain()+beans.getTheme_image());
        tv_title.setText(beans.getTheme_title());
        ultraViewPager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);
        List<String> image = beans.getImage();
        adapter = new UltraPagerAdapter(true, image);
        adapter.setListener(new UltraPagerAdapter.onImageViewClickListener() {
            @Override
            public void onListener(int position) {

            }
        });
        tv_indicator.setText("1/"+image.size());
        ultraViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tv_indicator.setText((position+1)+"/"+image.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        ultraViewPager.setAdapter(adapter);
        getDetail();
    }

    private void getDetail() {
        HashMap<String, Object> parms2 = new HashMap<>();
        parms2.put("aid",beans.getId());
        mPresenter.getArticle(parms2);
    }

    @OnClick({R2.id.iv_pl, R2.id.tv_share,R2.id.tv_content,R2.id.iv_back,R2.id.tv_dy,R2.id.tv_addstar})
    public void onClick(View view) {
        TMBaseFragment fragment = null;
        int id = view.getId();
        if(id==R.id.iv_pl||id==R.id.tv_content){
            HashMap<String, String> param = new HashMap<>();
            param.put("id",beans.getId()+"");
            fragment = (TMBaseFragment) ARouter.getInstance().build(TmyxRouterConfig.TMYX_PLLIST)
                    .withString("params",GsonUtil.GsonString(param))
                    .navigation();
            start(fragment);
        }else if(id==R.id.iv_back){
            pop();
        }else if(id==R.id.tv_share){
            TMLinkShare linkShare = new TMLinkShare();
            linkShare.setDescription(articleDetail.getContent());
            linkShare.setThumb(TMSharedPUtil.getTMBaseConfig(getContext()).getDomain()+articleDetail.getTheme_image());
            linkShare.setTitle(articleDetail.getTitle());
            String domain = TMSharedPUtil.getTMBaseConfig(getContext()).getDomain();

            linkShare.setUrl(domain + TmyxConstant.shareUrl+id);
            TMShareUtil.getInstance(getContext()).shareLink(linkShare);

        }else if(view.getId()==R.id.tv_dy){
            addSubscription(tid);
        }else if(view.getId()==R.id.iv_back){
            if(getPreFragment()!=null){
                pop();
            }else {
                getActivity().finish();
            }
        }else if(view.getId()==R.id.tv_addstar){
            if(star_id<0){
                addStar();
            }else {
                deleteStar();
            }
        }
    }
    private void addStar() {
        TMUser tmUser = TMSharedPUtil.getTMUser(getContext());
        HashMap<String, String> parms = new HashMap<>();
        JSONObject jsonObject = new JSONObject();
        try {
            JSONObject value = new JSONObject();
            value.put("native",true);
            value.put("src","com.system.tianmayunxi.zp01yx_bwusb.ui.officialrecommend.TiDetailFragment");
            value.put("paramStr",beans.getId());
            value.put("wwwFolder","");
            jsonObject.put("androidInfo", value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        parms.put("member_code", tmUser.getMember_code());
        parms.put("title", articleDetail.getTitle());
        parms.put("intro", articleDetail.getContent());
        parms.put("app_id", "zp01yx_bwusb");
        parms.put("article_id", articleDetail.getId()+"");
        parms.put("extend", new Gson().toJson(jsonObject));
        parms.put("type", "1");
        String value = new Gson().toJson(parms);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), value);
        mPresenter.addStar(body);
    }
    private void checkIsStar() {
        TMUser tmUser = TMSharedPUtil.getTMUser(getContext());

        HashMap<String, String> parms = new HashMap<>();
        parms.put("app_id", "zp01yx_bwusb");
        parms.put("member_code", tmUser.getMember_code());
        parms.put("article_id",articleDetail.getId()+"");

        String value = new Gson().toJson(parms);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), value);
        mPresenter.checkIsStar(body);
    }
    private void deleteStar() {
        //TMUser tmUser = TMSharedPUtil.getTMUser(getContext());

        HashMap<String, String> parms = new HashMap<>();
        parms.put("star_id", star_id+"");


        String value = new Gson().toJson(parms);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), value);
        mPresenter.deleteStar(body);
    }

    private void addSubscription(int id) {
        HashMap<String, String> parms = new HashMap<>();
        parms.put("tid",id+"");
        String value = new Gson().toJson(parms);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), value);
        mPresenter.addSubscription(body);
    }
    @Override
    public void onFaild() {

    }
    private void isSub(int tid) {
        HashMap<String, Object> parms = new HashMap<>();
        parms.put("tid", tid);
        mPresenter.isSub(parms);
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
                        case "getArticle":
                            articleDetail = GsonUtil.GsonToBean(object, ArticleDetail.class);

                            tid = articleDetail.getTid();
                            isSub(tid);
                            checkIsStar();
                            break;
                        case "artcleLike":
                            getDetail();
                            break;
                        case "isSub":
                            JsonObject jsonObject = GsonUtil.GsonToBean(object, JsonObject.class);
                            boolean is_sub = jsonObject.get("is_sub").getAsBoolean();
                            if(is_sub){
                                tv_dy.setVisibility(View.GONE);
                            }else {
                                tv_dy.setVisibility(View.VISIBLE);
                            }
                            break;
                        case "addSubscription":
                            isSub(tid);
                            break;
                        case "checkIsStar":
                            LinkedTreeMap object1 = (LinkedTreeMap) object;
                            if(object1.size()!=0){
                                star_id = (double )object1.get("star_id");
                            }else {
                                star_id=-1;
                            }
                            break;
                        case "deleteStar":
                        case "addStar":
                            checkIsStar();
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
