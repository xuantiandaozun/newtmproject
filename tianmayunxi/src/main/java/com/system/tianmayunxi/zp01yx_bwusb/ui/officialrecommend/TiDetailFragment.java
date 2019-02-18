package com.system.tianmayunxi.zp01yx_bwusb.ui.officialrecommend;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;
import com.library.flowlayout.SpaceItemDecoration;
import com.system.myproject.base.MVPBaseFragment;
import com.system.myproject.base.TMBaseFragment;
import com.system.myproject.utils.GsonUtil;
import com.system.myproject.utils.ToastUtil;
import com.system.myproject.utils.UEMethod;
import com.system.tianmayunxi.zp01yx_bwusb.BuildConfig;
import com.system.tianmayunxi.zp01yx_bwusb.R;
import com.system.tianmayunxi.zp01yx_bwusb.R2;
import com.system.tianmayunxi.zp01yx_bwusb.TmyxConstant;
import com.system.tianmayunxi.zp01yx_bwusb.TmyxRouterConfig;
import com.system.tianmayunxi.zp01yx_bwusb.bean.EventCallBackBean;
import com.system.tianmayunxi.zp01yx_bwusb.ui.officialrecommend.adapter.PlAdapter;
import com.system.tianmayunxi.zp01yx_bwusb.ui.officialrecommend.adapter.TieIvAdapter;
import com.system.tianmayunxi.zp01yx_bwusb.ui.officialrecommend.bean.ArticMsgBean;
import com.system.tianmayunxi.zp01yx_bwusb.ui.officialrecommend.bean.ArticleDetail;
import com.system.tianmayunxi.zp01yx_bwusb.ui.officialrecommend.contract.OfficContract;
import com.system.tianmayunxi.zp01yx_bwusb.ui.officialrecommend.presenter.OfficPresenter;
import com.tenma.ventures.bean.TMUser;
import com.tenma.ventures.bean.utils.TMSharedPUtil;
import com.tenma.ventures.share.bean.TMLinkShare;
import com.tenma.ventures.share.util.TMShareUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.RequestBody;

@Route(path = TmyxRouterConfig.TMYX_TIDETAIL)
public class TiDetailFragment extends MVPBaseFragment<OfficContract.View, OfficPresenter>
        implements OfficContract.View {
    @BindView(R2.id.head)
    SimpleDraweeView head;
    @BindView(R2.id.iv_head)
    SimpleDraweeView iv_head;
    @BindView(R2.id.user_head)
    SimpleDraweeView user_head;
    @BindView(R2.id.mlist)
    RecyclerView mlist;
    @BindView(R2.id.pl_mlist)
    RecyclerView pl_mlist;
    @BindView(R2.id.tv_artictitle)
    TextView tv_artictitle;
    @BindView(R2.id.tv_nickname)
    TextView tv_nickname;
    @BindView(R2.id.tv_content)
    TextView tv_content;
    @BindView(R2.id.tv_time)
    TextView tv_time;
    @BindView(R2.id.tv_username)
    TextView tv_username;
    @BindView(R2.id.tv_up)
    TextView tv_up;
    @BindView(R2.id.tv_dy)
    TextView tv_dy;
    @BindView(R2.id.tv_comments)
    TextView tv_comments;
    @BindView(R2.id.titleBar)
    LinearLayout titleBar;
    @BindView(R2.id.tv_centertitle)
    TextView tv_centertitle;
    @Autowired(name = "params")
    public String params;
    private PlAdapter adapter;
    private TieIvAdapter ivadapter;
    private String id;
    private int tid;
    private int themeColor;
    private ArticleDetail articleDetail;
    private int textcolor;
    private boolean isStart;
    private double star_id;
    private String detail;
    private boolean is_sub;

    @Override
    protected OfficPresenter createPresenter() {
        return new OfficPresenter();
    }

    @Override
    protected void init() {
        HashMap<String, Object> filter = getHashMapByParams(params);
        if (filter != null && filter.containsKey("id")) {
            id = filter.get("id").toString();
        }
        if (filter != null && filter.containsKey("detail")) {
            detail = filter.get("detail").toString();
        }
        String paramStr = getActivity().getIntent().getStringExtra("paramStr");

        if (TextUtils.isEmpty(id)) {
            if (!TextUtils.isEmpty(paramStr)) {
                id = paramStr;
            }
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_tidetail_zp01yx_bwusb;
    }

    @Override
    protected void initDatas() {
        themeColor = Color.parseColor(TMSharedPUtil.getTMThemeColor(getActivity()));
        textcolor = Color.parseColor(TMSharedPUtil.getTMTitleTextColor(getActivity()));

        titleBar.setBackgroundColor(themeColor);
        tv_centertitle.setTextColor(textcolor);
        mlist.setNestedScrollingEnabled(false);

        mlist.setLayoutManager(new GridLayoutManager(getThisContext(), 3));
        mlist.addItemDecoration(new SpaceItemDecoration(UEMethod.dp2px(getThisContext(), 5)));
        ArrayList<String> gridData = new ArrayList<>();
        ivadapter = new TieIvAdapter(gridData);
        mlist.setAdapter(ivadapter);
        ivadapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                TMBaseFragment fragment = null;
                HashMap<String, String> param = new HashMap<>();


                param.put("detail", detail);

                fragment = (TMBaseFragment) ARouter.getInstance().build(TmyxRouterConfig.TMYX_CKIMAGE)
                        .withString("params", GsonUtil.GsonString(param))
                        .navigation();
                start(fragment);

            }
        });
        pl_mlist.setLayoutManager(new LinearLayoutManager(getThisContext()));
        DividerItemDecoration decor = new DividerItemDecoration(getThisContext(), DividerItemDecoration.VERTICAL);
        decor.setDrawable(ContextCompat.getDrawable(getThisContext(), R.drawable.recyleview_line));
        pl_mlist.addItemDecoration(decor);
        pl_mlist.setNestedScrollingEnabled(false);
        this.adapter = new PlAdapter(new ArrayList<>());
        this.adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ArticMsgBean.ListBean item = (ArticMsgBean.ListBean) adapter.getItem(position);
                TMBaseFragment fragment = null;
                HashMap<String, String> param = new HashMap<>();

                param.put("detail", GsonUtil.GsonString(item));
                param.put("id", id);
                if (view.getId() == R.id.btn_pl) {
                    fragment = (TMBaseFragment) ARouter.getInstance().build(TmyxRouterConfig.TMYX_PLALL)
                            .withString("params", GsonUtil.GsonString(param))
                            .navigation();
                    start(fragment);
                } else if (view.getId() == R.id.tv_up) {
                    msgLike(item);
                } else if (view.getId() == R.id.btn_next) {
                    if (item.isIs_landlord()) {
                        delMsg(item);
                    } else {
                        param.put("id", item.getId() + "");
                        fragment = (TMBaseFragment) ARouter.getInstance().build(TmyxRouterConfig.TMYX_REPORT)
                                .withString("params", GsonUtil.GsonString(param))
                                .navigation();
                        start(fragment);

                        // reportMsg(item);
                    }
                }
            }
        });
        pl_mlist.setAdapter(this.adapter);

    }

    private void checkIsStar() {
        TMUser tmUser = TMSharedPUtil.getTMUser(getContext());

        HashMap<String, String> parms = new HashMap<>();
        parms.put("app_id", "zp01yx_bwusb");
        parms.put("member_code", tmUser.getMember_code());
        parms.put("article_id", articleDetail.getId() + "");

        String value = new Gson().toJson(parms);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), value);
        mPresenter.checkIsStar(body);
    }

    private void deleteStar() {
        //TMUser tmUser = TMSharedPUtil.getTMUser(getContext());

        HashMap<String, String> parms = new HashMap<>();
        parms.put("star_id", star_id + "");


        String value = new Gson().toJson(parms);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), value);
        mPresenter.deleteStar(body);
    }

    private void delMsg(ArticMsgBean.ListBean item) {
        HashMap<String, String> parms = new HashMap<>();
        parms.put("mid", item.getId() + "");
        String value = new Gson().toJson(parms);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), value);
        mPresenter.delMsg(body);
    }

    private void addStar() {
        TMUser tmUser = TMSharedPUtil.getTMUser(getContext());
        HashMap<String, Object> parms = new HashMap<>();
        JSONObject jsonObject = new JSONObject();

        HashMap<String, String> main = new HashMap<>();


        HashMap<String, String> param = new HashMap<>();
        param.put("id",id);
        param.put("detail",new Gson().toJson(detail));

        main.put("fragment",TmyxRouterConfig.TMYX_TIDETAIL);
        main.put("params",new Gson().toJson(param));


        JSONObject value = new JSONObject();
        try {
            value.put("native", "true");
            value.put("src", "com.system.tianmayunxi.zp01yx_bwusb.ui.FragmentActivity");
            value.put("paramStr", GsonUtil.GsonString(main));
            value.put("wwwFolder", "");

            jsonObject.put("androidInfo", value);

        } catch (JSONException e) {
            e.printStackTrace();
        }




        parms.put("member_code", tmUser.getMember_code());
        parms.put("title", articleDetail.getTitle());
        parms.put("intro", articleDetail.getContent());
        parms.put("app_id", "zp01yx_bwusb");
        parms.put("article_id", articleDetail.getId() + "");
        parms.put("extend", jsonObject.toString());
        parms.put("type", "1");
        String domain = TMSharedPUtil.getTMBaseConfig(getContext()).getDomain();
        parms.put("pic",domain+articleDetail.getTheme_image());
        String values = new Gson().toJson(parms);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), values);
        mPresenter.addStar(body);
    }

    private void reportMsg(ArticMsgBean.ListBean item) {
        HashMap<String, String> parms = new HashMap<>();
        parms.put("mid", item.getId() + "");
        String value = new Gson().toJson(parms);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), value);
        mPresenter.reportMsg(body);
    }

    private void msgLike(ArticMsgBean.ListBean item) {
        HashMap<String, String> parms = new HashMap<>();
        parms.put("mid", item.getId() + "");
        String value = new Gson().toJson(parms);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), value);
        mPresenter.msgLike(body);
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        initList();
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        getDetail();
    }

    private void getDetail() {
        HashMap<String, Object> parms2 = new HashMap<>();
        parms2.put("aid", id);
        mPresenter.getArticle(parms2);
    }

    private void initList() {
        HashMap<String, Object> parms = new HashMap<>();
        parms.put("aid", id);
        mPresenter.getArticMsgList(parms);
    }

    @OnClick({R2.id.iv_pl, R2.id.tv_share, R2.id.iv_share,R2.id.ret_circle, R2.id.tv_addstar, R2.id.tv_up, R2.id.tv_dy, R2.id.iv_jf, R2.id.iv_back})
    public void onClick(View view) {
        TMBaseFragment fragment = null;
        if (view.getId() == R.id.iv_pl || view.getId() == R.id.ret_circle) {
            HashMap<String, String> param = new HashMap<>();
            param.put("id", id);
            fragment = (TMBaseFragment) ARouter.getInstance().build(TmyxRouterConfig.TMYX_PLLIST)
                    .withString("params", GsonUtil.GsonString(param))
                    .navigation();
            start(fragment);

        } else if (view.getId() == R.id.tv_share||view.getId()==R.id.iv_share) {
            TMLinkShare linkShare = new TMLinkShare();
            linkShare.setDescription(articleDetail.getContent());
            linkShare.setThumb(TMSharedPUtil.getTMBaseConfig(getContext()).getDomain()+articleDetail.getTheme_image());
            linkShare.setTitle(articleDetail.getTitle());
            String domain = TMSharedPUtil.getTMBaseConfig(getContext()).getDomain();

            linkShare.setUrl(domain + TmyxConstant.shareUrl+id);
            TMShareUtil.getInstance(getContext()).shareLink(linkShare);
        } else if (view.getId() == R.id.tv_up) {
            articUp();
        } else if (view.getId() == R.id.tv_dy) {
            if(!is_sub){
                addSubscription(tid);
            }
        } else if (view.getId() == R.id.iv_jf) {
            fragment = (TMBaseFragment) ARouter.getInstance().build(TmyxRouterConfig.TMYX_LQJF)
                    .navigation();
            TMBaseFragment parentFragment = (TMBaseFragment) getParentFragment();
            start(fragment);
        } else if (view.getId() == R.id.iv_back) {
            if (getPreFragment() != null) {
                pop();
            } else {
                getActivity().finish();
            }
        } else if (view.getId() == R.id.tv_addstar) {
            if (star_id < 0) {
                addStar();
            } else {
                deleteStar();
            }
        }
    }

    private void addSubscription(int id) {
        HashMap<String, String> parms = new HashMap<>();
        parms.put("tid", id + "");
        String value = new Gson().toJson(parms);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), value);
        mPresenter.addSubscription(body);
    }

    private void articUp() {
        HashMap<String, String> parms = new HashMap<>();
        parms.put("aid", id + "");
        String value = new Gson().toJson(parms);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), value);
        mPresenter.artcleLike(body);
    }

    private void isSub(int tid) {
        HashMap<String, Object> parms = new HashMap<>();
        parms.put("tid", tid);
        mPresenter.isSub(parms);
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
                        case "getArticle":
                            articleDetail = GsonUtil.GsonToBean(object, ArticleDetail.class);

                            head.setImageURI(articleDetail.getTheme_image());
                            iv_head.setImageURI(articleDetail.getTheme_image());

                            String head_pic = articleDetail.getHead_pic();


                            user_head.setImageURI(head_pic);

                            String title = articleDetail.getTitle();
                            if (!TextUtils.isEmpty(title)) {
                                tv_artictitle.setText(title);
                            }
                            String theme_title1 = articleDetail.getTheme_title();
                            if (!TextUtils.isEmpty(theme_title1)) {
                                tv_nickname.setText(theme_title1);
                            }
                            String member_nickname = articleDetail.getMember_nickname();
                            if (!TextUtils.isEmpty(member_nickname)) {
                                tv_username.setText(member_nickname);
                            }
                            String content = articleDetail.getContent();
                            if (!TextUtils.isEmpty(content)) {
                                tv_content.setText(content);
                            }
                            String create_at = articleDetail.getCreate_at();
                            if (!TextUtils.isEmpty(create_at)) {
                                tv_time.setText(create_at);
                            }
                            String theme_title = articleDetail.getTheme_title();
                            if (!TextUtils.isEmpty(theme_title)) {
                                tv_centertitle.setText(theme_title);
                            }
                            int like_num = articleDetail.getLike_num();
                            if (!TextUtils.isEmpty(like_num + "")) {
                                tv_up.setText("(" + like_num + ")");
                            }
                            int msg_num = articleDetail.getMsg_num();
                            if (!TextUtils.isEmpty(msg_num + "")) {
                                tv_comments.setText("(" + msg_num + ")");
                            }
                            List<ArticleDetail.ImagesBean> images = articleDetail.getImages();
                            List<String> strings = new ArrayList<>();
                            for (int i = 0; i < images.size(); i++) {
                                strings.add(images.get(i).getImage());
                            }
                            tid = articleDetail.getTid();
                            isSub(tid);
                            ivadapter.setNewData(strings);
                            checkIsStar();
                            break;
                        case "getArticMsgList":
                            if (object != null) {
                                ArticMsgBean articMsgBean = GsonUtil.GsonToBean(object, ArticMsgBean.class);
                                List<ArticMsgBean.ListBean> list = articMsgBean.getList();
                                adapter.setNewData(list);
                            } else {
                                adapter.setNewData(null);
                            }
                            break;
                        case "artcleLike":
                            getDetail();
                            break;
                        case "msgLike":
                            initList();
                            break;
                        case "delMsg":
                        case "reportMsg":
                            initList();
                            break;
                        case "isSub":
                            JsonObject jsonObject = GsonUtil.GsonToBean(object, JsonObject.class);
                            is_sub = jsonObject.get("is_sub").getAsBoolean();
                            if (is_sub) {
                                tv_dy.setText("已订阅");
                            } else {
                                tv_dy.setVisibility(View.VISIBLE);
                            }
                            break;
                        case "addSubscription":
                            isSub(tid);
                            break;
                        case "checkIsStar":
                            LinkedTreeMap object1 = (LinkedTreeMap) object;
                            if (object1.size() != 0) {
                                star_id = (double) object1.get("star_id");
                            } else {
                                star_id = -1;
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
