package com.system.tianmayunxi.zp02yx_xzmbh.ui.officialrecommend;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.aries.ui.view.radius.RadiusEditText;
import com.aries.ui.view.radius.RadiusTextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.system.myproject.base.MVPBaseFragment;
import com.system.myproject.base.TMBaseFragment;
import com.system.myproject.utils.GsonUtil;
import com.system.myproject.utils.ToastUtil;
import com.system.tianmayunxi.zp02yx_xzmbh.R;
import com.system.tianmayunxi.zp02yx_xzmbh.R2;
import com.system.tianmayunxi.zp02yx_xzmbh.TmyxRouterConfig;
import com.system.tianmayunxi.zp02yx_xzmbh.bean.EventCallBackBean;
import com.system.tianmayunxi.zp02yx_xzmbh.ui.officialrecommend.adapter.PlAllAdapter;
import com.system.tianmayunxi.zp02yx_xzmbh.ui.officialrecommend.bean.ArticMsgBean;
import com.system.tianmayunxi.zp02yx_xzmbh.ui.officialrecommend.contract.OfficContract;
import com.system.tianmayunxi.zp02yx_xzmbh.ui.officialrecommend.presenter.OfficPresenter;
import com.system.tianmayunxi.zp02yx_xzmbh.utils.ClipboardUtils;
import com.system.uilibrary.views.titlebar.TitleBarView;
import com.tenma.ventures.bean.utils.TMSharedPUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.RequestBody;

@Route(path = TmyxRouterConfig.TMYX02_PLALL)
public class PlAllFragment extends MVPBaseFragment<OfficContract.View, OfficPresenter>
        implements OfficContract.View, OnRefreshListener, OnLoadMoreListener {
    @BindView(R2.id.titleBar)
    TitleBarView titleBar;
    @BindView(R2.id.iv_head)
    SimpleDraweeView iv_head;
    @BindView(R2.id.tv_theme)
    TextView tv_theme;
    @BindView(R2.id.tv_title)
    TextView tv_title;
    @BindView(R2.id.tv_time)
    TextView tv_time;
    @BindView(R2.id.btn_next)
    RadiusTextView btn_next;
    @BindView(R2.id.tv_up)
    TextView tv_up;
    @BindView(R2.id.re_pop)
    LinearLayout re_pop;
    @BindView(R2.id.ed_content)
    RadiusEditText ed_content;
    @BindView(R2.id.pl_mlist)
    RecyclerView pl_mlist;
    @BindView(R2.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @Autowired(name = "params")
    public String params;
    private PlAllAdapter adapter;
    private ArticMsgBean.ListBean beans;
    private String id;
    private boolean isDetail;
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
            beans = GsonUtil.GsonToBean(detail, ArticMsgBean.ListBean.class);
        }
        if (filter != null && filter.containsKey("id")) {
            id = filter.get("id").toString();
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_plall_zp01yx_bwusb2;
    }

    @Override
    protected void initDatas() {
        themeColor = Color.parseColor(TMSharedPUtil.getTMThemeColor(getActivity()));
        textcolor = Color.parseColor(TMSharedPUtil.getTMTitleTextColor(getActivity()));
        titleBar.setBackgroundColor(themeColor);
        titleBar.setTitleMainTextColor(textcolor);
        titleBar.setTitleMainText("评论")
                .setLeftTextDrawable(R.mipmap.icon_nav_back)
                .setOnLeftTextClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pop();
                    }
                });
        String head_pic = beans.getHead_pic();
        if(!TextUtils.isEmpty(head_pic)){
            if(!head_pic.contains("http")){
                head_pic=TMSharedPUtil.getTMBaseConfig(getContext()).getDomain()+beans.getHead_pic();
            }
        }

        iv_head.setImageURI(head_pic);
        tv_theme.setText(beans.getMember_nickname());
        tv_title.setText(beans.getContent());
        tv_time.setText(beans.getCreate_time());

        pl_mlist.setLayoutManager(new LinearLayoutManager(getThisContext()));
        DividerItemDecoration decor = new DividerItemDecoration(getThisContext(), DividerItemDecoration.VERTICAL);
        decor.setDrawable(ContextCompat.getDrawable(getThisContext(), R.drawable.recyleview_line));
        pl_mlist.addItemDecoration(decor);
        pl_mlist.setNestedScrollingEnabled(false);
        adapter = new PlAllAdapter(new ArrayList<>());
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ArticMsgBean.ListBean item = (ArticMsgBean.ListBean) adapter.getItem(position);
                if(view.getId()==R.id.tv_up){
                    msgLike(item);
                }else if(view.getId()==R.id.btn_next){
                    if(item.isIs_landlord()){
                        delMsg(item);
                    }else {
                        HashMap<String, String> param = new HashMap<>();
                        param.put("id",  item.getId()+"");
                        TMBaseFragment fragment = (TMBaseFragment) ARouter.getInstance().build(TmyxRouterConfig.TMYX02_REPORT)
                                .withString("params", GsonUtil.GsonString(param))
                                .navigation();
                        start(fragment);
                    }
                }
            }
        });
        if(beans.isIs_landlord()){
            btn_next.setText("删除");
        }else {
            btn_next.setText("举报");
        }
        pl_mlist.setAdapter(adapter);
        initList();
        getMsgDetail();
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadMoreListener(this);
    }

    private void msgLike(ArticMsgBean.ListBean item) {
        HashMap<String, String> parms = new HashMap<>();
        parms.put("mid", item.getId() + "");
        String value = new Gson().toJson(parms);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), value);
        mPresenter.msgLike(body);
    }

    private void delMsg(ArticMsgBean.ListBean item) {
        HashMap<String, String> parms = new HashMap<>();
        parms.put("mid",item.getId()+"");
        String value = new Gson().toJson(parms);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), value);
        mPresenter.delMsg(body);
    }

    private void reportMsg(ArticMsgBean.ListBean item) {
        HashMap<String, String> parms = new HashMap<>();
        parms.put("mid",item.getId()+"");
        String value = new Gson().toJson(parms);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), value);
        mPresenter.reportMsg(body);
    }
    @OnClick({R2.id.btn_pl,R2.id.tv_up,R2.id.ly,R2.id.btn_next,R2.id.btn_copy})
    public void onClick(View view) {
        if(view.getId()==R.id.btn_pl){
            String content = ed_content.getText().toString();
            if(TextUtils.isEmpty(content)){
                ToastUtil.showSnack(getContext(),"内容不能为空");
                return;
            }
            PushMessage();
        }else if(view.getId()==R.id.tv_up){
            msgLike(beans);
        }else if(view.getId()==R.id.ly){
            int visibility = re_pop.getVisibility();
            if(visibility==View.VISIBLE){
                re_pop.setVisibility(View.GONE);
            }else {
                re_pop.setVisibility(View.VISIBLE);
            }
        }else if(view.getId()==R.id.btn_next){
            if(beans.isIs_landlord()){
                delMsg(beans);
                isDetail=true;
            }else {
                HashMap<String, String> param = new HashMap<>();
                param.put("id",  beans.getId()+"");
                TMBaseFragment fragment = (TMBaseFragment) ARouter.getInstance().build(TmyxRouterConfig.TMYX02_REPORT)
                        .withString("params", GsonUtil.GsonString(param))
                        .navigation();
                start(fragment);
            }
        }else if(view.getId()==R.id.btn_copy){
            try {
                ClipboardUtils.copyText(getContext(),beans.getContent());
                ToastUtil.showSnack(getContext(),"复制到剪切板！");
            }catch (Exception e){

            }
        }

    }
    private void PushMessage() {
        String content = ed_content.getText().toString();

        HashMap<String, String> parms = new HashMap<>();
        parms.put("aid",id);
        parms.put("pid",beans.getId()+"");
        parms.put("content", content);
        String value = new Gson().toJson(parms);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), value);
        mPresenter.pushMsg(body);
    }
    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mPageIndex=1;
        HashMap<String, Object> parms = new HashMap<>();
        parms.put("pid",beans.getId()+"");
        parms.put("page",mPageIndex);
        mPresenter.msgInfoList(parms);
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        mPageIndex++;
        HashMap<String, Object> parms = new HashMap<>();
        parms.put("pid",beans.getId()+"");
        parms.put("page",mPageIndex);
        mPresenter.msgInfoList(parms);
    }

    private void initList() {
        HashMap<String, Object> parms = new HashMap<>();
        parms.put("pid",beans.getId()+"");
        mPresenter.msgInfoList(parms);
    }
    private void getMsgDetail() {
        HashMap<String, Object> parms = new HashMap<>();
        parms.put("mid",beans.getId()+"");
        mPresenter.msgInfoDetail(parms);
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
                        case "pushMsg":
                            pop();
                            break;
                        case "reportMsg":
                        case "delMsg":
                        case "msgLike":
                            if(isDetail){
                                pop();
                            }else {
                                initList();
                                getMsgDetail();
                            }

                            break;
                        case "msgInfoList":
                            if(object!=null){
                                ArticMsgBean articMsgBean = GsonUtil.GsonToBean(object, ArticMsgBean.class);
                                List<ArticMsgBean.ListBean> list = articMsgBean.getList();
                                if(mPageIndex==1){
                                    refreshLayout.finishRefresh();
                                    adapter.setNewData(list);
                                }else {
                                    refreshLayout.finishLoadMore();
                                    adapter.addData(list);
                                }
                            }else {
                                if(mPageIndex==1){
                                    adapter.setNewData(null);
                                    refreshLayout.finishRefresh();
                                }else {
                                    refreshLayout.finishLoadMore();
                                }
                            }

                            break;
                        case "msgInfoDetail":
                            ArticMsgBean.ListBean listBean = GsonUtil.GsonToBean(object, ArticMsgBean.ListBean.class);
                            tv_up.setText("("+listBean.getLike_num()+")");
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
