package com.system.tianmayunxi.zp02yx_xzmbh.ui.officialrecommend;

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
import com.system.myproject.utils.ToastUtil;
import com.system.myproject.utils.UEMethod;
import com.system.tianmayunxi.zp02yx_xzmbh.R;
import com.system.tianmayunxi.zp02yx_xzmbh.R2;
import com.system.tianmayunxi.zp02yx_xzmbh.Tmyx02RouterConfig;
import com.system.tianmayunxi.zp02yx_xzmbh.TmyxConstant;
import com.system.tianmayunxi.zp02yx_xzmbh.bean.EventCallBackBean;
import com.system.tianmayunxi.zp02yx_xzmbh.ui.officialrecommend.adapter.officAdapter;
import com.system.tianmayunxi.zp02yx_xzmbh.ui.officialrecommend.bean.ArticleBean;
import com.system.tianmayunxi.zp02yx_xzmbh.ui.officialrecommend.bean.CommonSeeBean;
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
 * 官方推荐
 */
@Route(path = Tmyx02RouterConfig.TMYX02_GFTJ)
public class OfficRecommendFragment extends BaseFragment<OfficContract.View, OfficPresenter>
        implements OfficContract.View, OnRefreshListener, OnLoadMoreListener
{
    @BindView(R2.id.mlist)
    RecyclerView mlist;
    @BindView(R2.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private officAdapter adapter;

    @Override
    protected OfficPresenter createPresenter() {
        return new OfficPresenter();
    }

    @Override
    protected void init() {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_official_recommend_zp01yx_bwusb2;
    }

    @Override
    protected void initDatas() {
        LinearLayoutManager layout = new LinearLayoutManager(getThisContext());
        mlist.setLayoutManager(layout);
        mlist.addItemDecoration(new VerticalSpaceItemDecoration(UEMethod.dp2px(getThisContext(),5)));
        adapter = new officAdapter(new ArrayList<>());
        adapter.setFragment(this);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MultiItemEntity item = (MultiItemEntity) adapter.getItem(position);
                String tmToken = TMSharedPUtil.getTMToken(getContext());
                if(TextUtils.isEmpty(tmToken)){
                    Intent intent = new Intent(getActivity().getPackageName() + ".usercenter.login");
                    getActivity().startActivity(intent);
                    return;
                }
                switch (item.getItemType()){
                    case DATA_TYPE1:
                        break;
                    case DATA_TYPE2:
                        TieZiBean item2 = (TieZiBean) item;

                        HashMap<String, String> main = new HashMap<>();


                        HashMap<String, String> param = new HashMap<>();
                        param.put("id",item2.getId()+"");
                        param.put("detail",new Gson().toJson(item2) );

                        main.put("fragment", Tmyx02RouterConfig.TMYX02_TIDETAIL);
                        main.put("params",new Gson().toJson(param));

                        ARouter.getInstance().build(Tmyx02RouterConfig.MAIN02_FRAGMENT)
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
                String tmToken = TMSharedPUtil.getTMToken(getContext());
                if(TextUtils.isEmpty(tmToken)){
                    Intent intent = new Intent(getActivity().getPackageName() + ".usercenter.login");
                    getActivity().startActivity(intent);
                    return;
                }
                switch (item.getItemType()){
                    case DATA_TYPE1:
                        if(view.getId()==R.id.tv_fb){
                             tmToken = TMSharedPUtil.getTMToken(getContext());
                            if(!TextUtils.isEmpty(tmToken)){
                                HashMap<String, String> main = new HashMap<>();
                                HashMap<String, String> param = new HashMap<>();
                                main.put("fragment", Tmyx02RouterConfig.TMYX02_XZZT);
                                main.put("params",new Gson().toJson(param));
                                ARouter.getInstance().build(Tmyx02RouterConfig.MAIN02_FRAGMENT)
                                        .withString("params",GsonUtil.GsonString(main))
                                        .withTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom)
                                        .navigation();
                            }else {
                                Intent intent = new Intent(getActivity().getPackageName() + ".usercenter.login");
                                getActivity().startActivity(intent);
                            }

                        }
                        break;
                    case DATA_TYPE2:
                        TieZiBean item2 = (TieZiBean) item;
                        if(view.getId()==R.id.ll_iv){
                            HashMap<String, String> main = new HashMap<>();


                            HashMap<String, String> param = new HashMap<>();
                            param.put("detail",GsonUtil.GsonString(item2));

                            main.put("fragment", Tmyx02RouterConfig.TMYX02_CKIMAGE);
                            main.put("params",new Gson().toJson(param));

                            ARouter.getInstance().build(Tmyx02RouterConfig.MAIN02_FRAGMENT)
                                    .withString("params",GsonUtil.GsonString(main))
                                    .withTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom)
                                    .navigation();

                        }else if(view.getId()==R.id.tv_up){
                            articUp(item2);
                        }else if(view.getId()==R.id.tv_share){
                            TMLinkShare linkShare = new TMLinkShare();
                            linkShare.setDescription(item2.getContent());
                            linkShare.setThumb(TMSharedPUtil.getTMBaseConfig(getContext()).getDomain()+item2.getTheme_image());
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

        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadMoreListener(this);

    }
    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        mPageIndex++;
        HashMap<String, Object> parms2 = new HashMap<>();
        parms2.put("is_sub","0");
        parms2.put("tid","0");
        parms2.put("page",mPageIndex);
        mPresenter.getArticleListNoLogin(parms2);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mPageIndex=1;
        initList();
    }
    @Override
    public void onResume() {
        super.onResume();
        onRefresh(null);
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
                main.put("fragment", Tmyx02RouterConfig.TMYX02_LQJF);
                main.put("params",new Gson().toJson(param));
                ARouter.getInstance().build(Tmyx02RouterConfig.MAIN02_FRAGMENT)
                        .withString("params",GsonUtil.GsonString(main))
                        .withTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom)
                        .navigation();
            }else {
                Intent intent = new Intent(getActivity().getPackageName() + ".usercenter.login");
                getActivity().startActivity(intent);
            }
        }
    }

    private void initList() {
        HashMap<String, Object> parms = new HashMap<>();
        parms.put("is_index","1");
      //  mPresenter.getTheme(parms);

        HashMap<String, Object> parms2 = new HashMap<>();
        parms2.put("is_sub","0");
        parms2.put("tid","0");
        parms2.put("page",mPageIndex);
       // mPresenter.getArticleList(parms2);

        mPresenter.homeRefresh(parms,parms2);
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
                        case "getTheme":
                            List<MultiItemEntity> data = adapter.getData();
                            CommonSeeBean commonSeeBean = GsonUtil.GsonToBean(object, CommonSeeBean.class);
                            adapter.addData(0,commonSeeBean);
                            break;
                        case "getArticleList":
                        case "getArticleListNoLogin":
                            if(mPageIndex==1){
                                refreshLayout.finishRefresh();
                            }else {
                                refreshLayout.finishLoadMore();
                            }
                            if(object!=null){
                                ArticleBean articleBean = GsonUtil.GsonToBean(object, ArticleBean.class);
                                List<TieZiBean> list = articleBean.getList();
                                adapter.addData(list);
                            }
                            break;
                        case "homeRefresh":

                            if(mPageIndex==1){
                                adapter.setNewData((List<MultiItemEntity>) object);
                                refreshLayout.finishRefresh();
                            }else {
                                adapter.addData((List<MultiItemEntity>) object);
                                refreshLayout.finishLoadMore();
                            }
                            break;
                        case "artcleLike":
                            initList();
                            ToastUtil.showSnack(getThisContext(),(String) object);

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
    //    ToastUtil.showSnack(getThisContext(),message);
    }
}
