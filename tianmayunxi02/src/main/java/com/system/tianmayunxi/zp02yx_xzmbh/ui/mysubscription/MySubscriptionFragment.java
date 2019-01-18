package com.system.tianmayunxi.zp02yx_xzmbh.ui.mysubscription;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.system.myproject.base.BaseFragment;
import com.system.myproject.base.TMBaseFragment;
import com.system.myproject.utils.GsonUtil;
import com.system.myproject.utils.UEMethod;
import com.system.tianmayunxi.zp02yx_xzmbh.R;
import com.system.tianmayunxi.zp02yx_xzmbh.R2;
import com.system.tianmayunxi.zp02yx_xzmbh.Tmyx02RouterConfig;
import com.system.tianmayunxi.zp02yx_xzmbh.bean.EventCallBackBean;
import com.system.tianmayunxi.zp02yx_xzmbh.ui.mysubscription.adapter.MySubAdapter;
import com.system.tianmayunxi.zp02yx_xzmbh.ui.officialrecommend.bean.ArticleBean;
import com.system.tianmayunxi.zp02yx_xzmbh.ui.officialrecommend.bean.TieZiBean;
import com.system.tianmayunxi.zp02yx_xzmbh.ui.officialrecommend.contract.OfficContract;
import com.system.tianmayunxi.zp02yx_xzmbh.ui.officialrecommend.presenter.OfficPresenter;
import com.system.uilibrary.views.recyclerview.decoration.VerticalSpaceItemDecoration;
import com.tenma.ventures.bean.utils.TMSharedPUtil;
import com.tenma.ventures.share.bean.TMLinkShare;
import com.tenma.ventures.share.util.TMShareUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.RequestBody;

import static com.system.tianmayunxi.zp02yx_xzmbh.ui.officialrecommend.adapter.officAdapter.DATA_TYPE1;
import static com.system.tianmayunxi.zp02yx_xzmbh.ui.officialrecommend.adapter.officAdapter.DATA_TYPE2;

/**
 * 我的订阅
 */
@Route(path = Tmyx02RouterConfig.TMYX02_WDDY)
public class MySubscriptionFragment extends BaseFragment<OfficContract.View, OfficPresenter>
        implements OfficContract.View, OnRefreshListener, OnLoadMoreListener {
    @BindView(R2.id.mlist)
    RecyclerView mlist;
    @BindView(R2.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private MySubAdapter adapter;

    @Override
    protected OfficPresenter createPresenter() {
        return new OfficPresenter();
    }

    @Override
    protected void init() {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_mysubscrio_zp01yx_bwusb2;
    }

    @Override
    protected void initDatas() {
        mlist.setLayoutManager(new LinearLayoutManager(getThisContext()));
        mlist.addItemDecoration(new VerticalSpaceItemDecoration(UEMethod.dp2px(getThisContext(),5)));
        adapter = new MySubAdapter(new ArrayList<>());
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                TMBaseFragment fragment=null;
                TMBaseFragment parentFragment=null;
                TieZiBean item2 = (TieZiBean) adapter.getItem(position);

                HashMap<String, String> main = new HashMap<>();


                HashMap<String, String> param = new HashMap<>();
                param.put("id",item2.getId()+"");

                main.put("fragment", Tmyx02RouterConfig.TMYX02_TIDETAIL);
                main.put("params",new Gson().toJson(param));

                ARouter.getInstance().build(Tmyx02RouterConfig.MAIN02_FRAGMENT)
                        .withString("params",GsonUtil.GsonString(main))
                        .navigation();
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
                            parentFragment.start(fragment);
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
                            parentFragment.start(fragment);
                        }else if(view.getId()==R.id.tv_up){
                            articUp(item2);
                        }else if(view.getId()==R.id.tv_share){
                            TMLinkShare linkShare = new TMLinkShare();
                            linkShare.setDescription(item2.getContent());
                            linkShare.setThumb(TMSharedPUtil.getTMBaseConfig(getContext()).getDomain()+item2.getTheme_image());
                            linkShare.setTitle(item2.getTitle());
                            linkShare.setUrl(TMSharedPUtil.getTMBaseConfig(getContext()).getDomain()+item2.getImage().get(0));
                            TMShareUtil.getInstance(getContext()).shareLink(linkShare);
                        }
                        break;
                }

            }
        });
        mlist.setAdapter(adapter);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadMoreListener(this);

    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            onRefresh(null);
        }
    }
    private void articUp(TieZiBean item2) {
        HashMap<String, String> parms = new HashMap<>();
        parms.put("aid", item2.getId() + "");
        String value = new Gson().toJson(parms);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), value);
        mPresenter.artcleLike(body);
    }

    @OnClick({R2.id.iv_jf})
    public void onClick(View view){
        TMBaseFragment fragment=null;
        TMBaseFragment parentFragment=null;
        int id = view.getId();
        if(id==R.id.iv_jf){
            HashMap<String, String> main = new HashMap<>();


            HashMap<String, String> param = new HashMap<>();

            main.put("fragment", Tmyx02RouterConfig.TMYX02_LQJF);
            main.put("params",new Gson().toJson(param));



            ARouter.getInstance().build(Tmyx02RouterConfig.MAIN02_FRAGMENT)
                    .withString("params",GsonUtil.GsonString(main))
                    .withTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom)
                    .navigation();
        }
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
                            if(object!=null){
                                ArticleBean articleBean = GsonUtil.GsonToBean(object, ArticleBean.class);
                                List<TieZiBean> list = articleBean.getList();
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
                        case "artcleLike":
                            onRefresh(null);
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
      //  ToastUtil.showSnack(getThisContext(),message);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mPageIndex=1;
        HashMap<String, Object> parms = new HashMap<>();
        parms.put("is_sub","1");
        parms.put("tid","0");
        parms.put("page",mPageIndex);

        mPresenter.getArticleList(parms);
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        mPageIndex++;
        HashMap<String, Object> parms = new HashMap<>();
        parms.put("is_sub","1");
        parms.put("tid","0");
        parms.put("page",mPageIndex);

        mPresenter.getArticleList(parms);
    }
}
