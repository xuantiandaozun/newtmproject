package com.system.tianmayunxi.zp02yx_xzmbh.ui.alltheme;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
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
import com.system.myproject.utils.UEMethod;
import com.system.tianmayunxi.zp02yx_xzmbh.R;
import com.system.tianmayunxi.zp02yx_xzmbh.R2;
import com.system.tianmayunxi.zp02yx_xzmbh.Tmyx02RouterConfig;
import com.system.tianmayunxi.zp02yx_xzmbh.TmyxConstant;
import com.system.tianmayunxi.zp02yx_xzmbh.bean.EventCallBackBean;
import com.system.tianmayunxi.zp02yx_xzmbh.ui.alltheme.adapter.ThemeDetailAdapter;
import com.system.tianmayunxi.zp02yx_xzmbh.ui.alltheme.bean.AllThemBean;
import com.system.tianmayunxi.zp02yx_xzmbh.ui.officialrecommend.bean.ArticleBean;
import com.system.tianmayunxi.zp02yx_xzmbh.ui.officialrecommend.bean.OneThemeBean;
import com.system.tianmayunxi.zp02yx_xzmbh.ui.officialrecommend.bean.TieZiBean;
import com.system.tianmayunxi.zp02yx_xzmbh.ui.officialrecommend.contract.OfficContract;
import com.system.tianmayunxi.zp02yx_xzmbh.ui.officialrecommend.presenter.OfficPresenter;
import com.system.uilibrary.views.recyclerview.decoration.VerticalSpaceItemDecoration;
import com.system.uilibrary.views.titlebar.TitleBarView;
import com.tenma.ventures.bean.utils.TMSharedPUtil;
import com.tenma.ventures.share.bean.TMLinkShare;
import com.tenma.ventures.share.util.TMShareUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import okhttp3.RequestBody;

import static com.system.tianmayunxi.zp02yx_xzmbh.ui.officialrecommend.adapter.officAdapter.DATA_TYPE1;
import static com.system.tianmayunxi.zp02yx_xzmbh.ui.officialrecommend.adapter.officAdapter.DATA_TYPE2;

@Route(path = Tmyx02RouterConfig.TMYX02_THEMEDETAIL)
public class ThemeDetailFragment extends MVPBaseFragment<OfficContract.View, OfficPresenter>
        implements OfficContract.View, OnRefreshListener, OnLoadMoreListener {
    @BindView(R2.id.titleBar)
    TitleBarView titleBar;
    @BindView(R2.id.mlist)
    RecyclerView mlist;
    @BindView(R2.id.iv)
    SimpleDraweeView iv;
    @BindView(R2.id.tv_name)
    TextView tv_name;
    @BindView(R2.id.tv_content)
    TextView tv_content;
    @BindView(R2.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @Autowired(name = "params")
    public String params;
    private ThemeDetailAdapter adapter;
    private AllThemBean.ListBean listBean;
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
            listBean = GsonUtil.GsonToBean(detail,  AllThemBean.ListBean.class);
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_themedetail_zp01yx_bwusb2;
    }

    @Override
    protected void initDatas() {
        themeColor = Color.parseColor(TMSharedPUtil.getTMThemeColor(getActivity()));
        textcolor = Color.parseColor(TMSharedPUtil.getTMTitleTextColor(getActivity()));
        titleBar.setBackgroundColor(themeColor);
        titleBar.setTitleMainTextColor(textcolor);

        titleBar.setTitleMainText(listBean.getTitle())
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
        iv.setImageURI(listBean.getImage());
        tv_name.setText(listBean.getTitle());
        mlist.setLayoutManager(new LinearLayoutManager(getThisContext()));
        mlist.addItemDecoration(new VerticalSpaceItemDecoration(UEMethod.dp2px(getThisContext(),5)));
        adapter = new ThemeDetailAdapter(new ArrayList<>());
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                TMBaseFragment fragment=null;
                TMBaseFragment parentFragment=null;
                MultiItemEntity item = (MultiItemEntity) adapter.getItem(position);

                switch (item.getItemType()){
                    case DATA_TYPE1:
                        break;
                    case DATA_TYPE2:
                        TieZiBean item2 = (TieZiBean) item;
                        HashMap<String, String> param = new HashMap<>();
                        param.put("id",item2.getId()+"");
                        fragment = (TMBaseFragment) ARouter.getInstance().build(Tmyx02RouterConfig.TMYX02_TIDETAIL)
                                .withString("params",GsonUtil.GsonString(param))
                                .navigation();
                        parentFragment = (TMBaseFragment) getParentFragment();
                      start(fragment);
                        break;
                }
            }
        });
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                TMBaseFragment fragment=null;
                TMBaseFragment parentFragment=null;
                Object item1 = adapter.getItem(position);
                MultiItemEntity item = (MultiItemEntity) item1;
                switch (item.getItemType()){
                    case DATA_TYPE1:
                        if(view.getId()==R.id.tv_fb){
                            fragment = (TMBaseFragment) ARouter.getInstance().build(Tmyx02RouterConfig.TMYX02_XZZT)
                                    .navigation();
                            parentFragment = (TMBaseFragment) getParentFragment();
                        start(fragment);
                        }
                        break;
                    case DATA_TYPE2:
                        TieZiBean item2 = (TieZiBean) item;
                        if(view.getId()==R.id.ll_iv){
                            HashMap<String, String> param = new HashMap<>();
                            param.put("detail",GsonUtil.GsonString(item2));
                            fragment = (TMBaseFragment) ARouter.getInstance().build(Tmyx02RouterConfig.TMYX02_CKIMAGE)
                                    .withString("params",GsonUtil.GsonString(param))
                                    .navigation();
                            parentFragment = (TMBaseFragment) getParentFragment();
                          start(fragment);
                        }else if(view.getId()==R.id.tv_up){
                            articUp(item2);
                        }else if(view.getId()==R.id.tv_share){
                            TMLinkShare linkShare = new TMLinkShare();
                            linkShare.setDescription(item2.getContent());
                            linkShare.setThumb(item2.getTheme_image());
                            linkShare.setTitle(item2.getTitle());
                            String domain = TMSharedPUtil.getTMBaseConfig(getContext()).getDomain();

                            linkShare.setUrl(domain + TmyxConstant.shareUrl+item2.getId());
                            TMShareUtil.getInstance(getContext()).shareLink(linkShare);
                        }
                        break;
                }

            }
        });
        mlist.setAdapter(adapter);
        initList();
        getOneTheme();
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadMoreListener(this);
    }
    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mPageIndex=1;
        initList();
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        mPageIndex++;
        initList();
    }

    private void getOneTheme() {
        HashMap<String, Object> parms2 = new HashMap<>();
        parms2.put("tid",listBean.getId()+"");
        mPresenter.oneTheme(parms2);
    }

    private void initList() {
        HashMap<String, Object> parms2 = new HashMap<>();
        parms2.put("is_sub","0");
        parms2.put("tid",listBean.getId()+"");
        parms2.put("page",mPageIndex);

        mPresenter.getArticleList(parms2);
    }
    private void articUp(TieZiBean item2) {
        HashMap<String, String> parms = new HashMap<>();
        parms.put("aid", item2.getId() + "");
        String value = new Gson().toJson(parms);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), value);
        mPresenter.artcleLike(body);
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
                        case "getArticleList":
                            List<TieZiBean> list=null;
                            if(object!=null){
                                ArticleBean articleBean = GsonUtil.GsonToBean(object, ArticleBean.class);
                                list = articleBean.getList();
                            }
                            if(mPageIndex==1){
                                refreshLayout.finishRefresh();
                                adapter.setNewData(list);
                            }else {
                                refreshLayout.finishLoadMore();
                                if(list!=null)
                                adapter.addData(list);
                            }
                            break;
                        case "artcleLike":
                            initList();
                            break;
                        case "oneTheme":
                            OneThemeBean oneThemeBean = GsonUtil.GsonToBean(object, OneThemeBean.class);
                            tv_content.setText(oneThemeBean.getContent());
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
