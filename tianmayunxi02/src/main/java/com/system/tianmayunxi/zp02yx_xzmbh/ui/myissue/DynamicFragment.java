package com.system.tianmayunxi.zp02yx_xzmbh.ui.myissue;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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
import com.system.tianmayunxi.zp02yx_xzmbh.TmyxRouterConfig;
import com.system.tianmayunxi.zp02yx_xzmbh.bean.EventCallBackBean;
import com.system.tianmayunxi.zp02yx_xzmbh.ui.myissue.adapter.DynamicAdapter;
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
 * 动态
 */
@Route(path = TmyxRouterConfig.WDFB02_DYNAMIC)
public class DynamicFragment extends BaseFragment<OfficContract.View, OfficPresenter>
        implements OfficContract.View, OnRefreshListener, OnLoadMoreListener {
    @BindView(R2.id.mlist)
    RecyclerView mlist;
    @BindView(R2.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private DynamicAdapter adapter;

    @Override
    protected OfficPresenter createPresenter() {
        return new OfficPresenter();
    }

    @Override
    protected void init() {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_dynamic_zp02yx_bwusb;
    }

    @Override
    protected void initDatas() {
        mlist.setLayoutManager(new LinearLayoutManager(getThisContext()));
        mlist.addItemDecoration(new VerticalSpaceItemDecoration(UEMethod.dp2px(getThisContext(),5)));
        adapter = new DynamicAdapter(new ArrayList<>());
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
                        HashMap<String, String> main = new HashMap<>();


                        HashMap<String, String> param = new HashMap<>();
                        param.put("id",item2.getId()+"");

                        main.put("fragment",TmyxRouterConfig.TMYX02_TIDETAIL);
                        main.put("params",new Gson().toJson(param));

                        ARouter.getInstance().build(TmyxRouterConfig.MAIN02_FRAGMENT)
                                .withString("params",GsonUtil.GsonString(main))
                                .navigation();
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
                            fragment = (TMBaseFragment) ARouter.getInstance().build(TmyxRouterConfig.TMYX02_XZZT)
                                    .navigation();
                            parentFragment = (TMBaseFragment) getParentFragment().getParentFragment();
                            parentFragment.start(fragment);
                        }
                        break;
                    case DATA_TYPE2:
                        TieZiBean item2 = (TieZiBean) item;
                        if(view.getId()==R.id.ll_iv){
                            HashMap<String, String> param = new HashMap<>();
                            param.put("detail",GsonUtil.GsonString(item2));
                            fragment = (TMBaseFragment) ARouter.getInstance().build(TmyxRouterConfig.TMYX02_CKIMAGE)
                                    .withString("params",GsonUtil.GsonString(param))
                                    .navigation();
                            parentFragment = (TMBaseFragment) getParentFragment().getParentFragment();
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
        initList();
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
            String tmToken = TMSharedPUtil.getTMToken(getContext());
            if(!TextUtils.isEmpty(tmToken)){
                HashMap<String, String> main = new HashMap<>();
                HashMap<String, String> param = new HashMap<>();
                main.put("fragment",TmyxRouterConfig.TMYX02_LQJF);
                main.put("params",new Gson().toJson(param));
                ARouter.getInstance().build(TmyxRouterConfig.MAIN02_FRAGMENT)
                        .withString("params",GsonUtil.GsonString(main))
                        .withTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom)
                        .navigation();
            }else {
                Intent intent = new Intent(getActivity().getPackageName() + ".usercenter.login");
                getActivity().startActivity(intent);
            }
        }
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
    private void initList() {
        HashMap<String, Object> parms = new HashMap<>();
        parms.put("page",mPageIndex);
        mPresenter.getMyArtic(parms);
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
                        case "getMyArtic":
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
     //   ToastUtil.showSnack(getThisContext(),message);
    }

}
