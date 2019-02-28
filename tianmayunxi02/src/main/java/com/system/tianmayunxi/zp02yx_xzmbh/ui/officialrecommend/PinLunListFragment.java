package com.system.tianmayunxi.zp02yx_xzmbh.ui.officialrecommend;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.aries.ui.view.radius.RadiusEditText;
import com.chad.library.adapter.base.BaseQuickAdapter;
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
import com.system.tianmayunxi.zp02yx_xzmbh.Tmyx02RouterConfig;
import com.system.tianmayunxi.zp02yx_xzmbh.bean.EventCallBackBean;
import com.system.tianmayunxi.zp02yx_xzmbh.ui.officialrecommend.adapter.PlListAdapter;
import com.system.tianmayunxi.zp02yx_xzmbh.ui.officialrecommend.bean.ArticMsgBean;
import com.system.tianmayunxi.zp02yx_xzmbh.ui.officialrecommend.contract.OfficContract;
import com.system.tianmayunxi.zp02yx_xzmbh.ui.officialrecommend.presenter.OfficPresenter;
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


@Route(path = Tmyx02RouterConfig.TMYX02_PLIST)
public class PinLunListFragment extends MVPBaseFragment<OfficContract.View, OfficPresenter>
        implements OfficContract.View, OnRefreshListener, OnLoadMoreListener {
    @BindView(R2.id.titleBar)
    TitleBarView titleBar;
    @BindView(R2.id.mlist)
    RecyclerView mlist;
    @BindView(R2.id.ed_content)
    RadiusEditText ed_content;
    @BindView(R2.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @Autowired(name = "params")
    public String params;
    private PlListAdapter adapter;
    private String id;
    private int themeColor;
    private int textcolor;

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
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_pllist_zp01yx_bwusb2;
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
        mlist.setLayoutManager(new LinearLayoutManager(getThisContext()));
        DividerItemDecoration decor = new DividerItemDecoration(getThisContext(), DividerItemDecoration.VERTICAL);
        decor.setDrawable(ContextCompat.getDrawable(getThisContext(), R.drawable.recyleview_line));
        mlist.addItemDecoration(decor);
        mlist.setNestedScrollingEnabled(false);
        adapter = new PlListAdapter(new ArrayList<>());
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int id = view.getId();
                ArticMsgBean.ListBean item = (ArticMsgBean.ListBean) adapter.getItem(position);
                 if(view.getId()==R.id.tv_up){
                    msgLike(item);
                }else if(view.getId()==R.id.btn_next){
                    if(item.isIs_landlord()){
                        delMsg(item);
                    }else {
                        HashMap<String, String> param = new HashMap<>();
                        param.put("id",  item.getId()+"");
                        TMBaseFragment fragment = (TMBaseFragment) ARouter.getInstance().build(Tmyx02RouterConfig.TMYX02_REPORT)
                                .withString("params", GsonUtil.GsonString(param))
                                .navigation();
                        start(fragment);
                    }
                }
            }
        });
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
        mlist.setAdapter(adapter);
        initList();
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
    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mPageIndex=1;
        HashMap<String, Object> parms = new HashMap<>();
        parms.put("aid",id);
        parms.put("page",mPageIndex);
        mPresenter.getArticMsgList(parms);
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        mPageIndex++;
        HashMap<String, Object> parms = new HashMap<>();
        parms.put("aid",id);
        parms.put("page",mPageIndex);
        mPresenter.getArticMsgList(parms);
    }

    private void initList() {
        HashMap<String, Object> parms = new HashMap<>();
        parms.put("aid",id);
        mPresenter.getArticMsgList(parms);
    }


    @OnClick(R2.id.btn_pl)
    public void onClick() {
        String content = ed_content.getText().toString();
        if(TextUtils.isEmpty(content)){
           ToastUtil.showSnack(getContext(),"内容不能为空");
           return;
        }
        PushMessage();
    }

    private void PushMessage() {
        String content = ed_content.getText().toString();

        HashMap<String, String> parms = new HashMap<>();
        parms.put("aid",id+"");
        parms.put("pid", "0");
        parms.put("content", content);
        String value = new Gson().toJson(parms);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), value);
        mPresenter.pushMsg(body);
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
                            if(object instanceof String){
                                ToastUtil.showSnack(getThisContext(),(String) object);
                            }
                            pop();
                            break;
                        case "reportMsg":
                        case "delMsg":
                        case "msgLike":
                            if(object instanceof String){
                                ToastUtil.showSnack(getThisContext(),(String) object);
                            }
                            initList();
                            break;
                        case "getArticMsgList":
                            if(object!=null){
                                ArticMsgBean articMsgBean = GsonUtil.GsonToBean(object, ArticMsgBean.class);
                                List<ArticMsgBean.ListBean> list = articMsgBean.getList();
                                if(mPageIndex==1){
                                    adapter.setNewData(list);
                                    refreshLayout.finishRefresh();
                                }else {
                                    adapter.addData(list);
                                    refreshLayout.finishLoadMore();
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
