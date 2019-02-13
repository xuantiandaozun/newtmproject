package com.system.tmhsdl.zp01hxdl_vjflt.ui.dzs;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.library.flowlayout.SpaceItemDecoration;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.system.myproject.base.BaseFragment;
import com.system.myproject.utils.GsonUtil;
import com.system.myproject.utils.UEMethod;

import com.system.tmhsdl.zp01hxdl_vjflt.HxdlRouterConfig;
import com.system.tmhsdl.zp01hxdl_vjflt.R;
import com.system.tmhsdl.zp01hxdl_vjflt.R2;
import com.system.tmhsdl.zp01hxdl_vjflt.bean.EventCallBackBean;
import com.system.tmhsdl.zp01hxdl_vjflt.ui.dzs.adapter.DZSAdapter;
import com.system.tmhsdl.zp01hxdl_vjflt.ui.dzs.bean.BookBean;
import com.system.tmhsdl.zp01hxdl_vjflt.ui.dzs.contract.DzsContract;
import com.system.tmhsdl.zp01hxdl_vjflt.ui.dzs.presenter.DzsPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import butterknife.BindView;

@Route(path = HxdlRouterConfig.HXDL_DZS)
public class DZSFragment extends BaseFragment<DzsContract.View, DzsPresenter>
        implements DzsContract.View, OnRefreshListener, OnLoadMoreListener {
    @BindView(R2.id.mlist)
    RecyclerView mlist;
    @BindView(R2.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private DZSAdapter adapter;

    @Override
    protected DzsPresenter createPresenter() {
        return new DzsPresenter();
    }

    @Override
    protected void init() {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.hxdl_fragment_dzs;
    }

    @Override
    protected void initDatas() {
        mlist.setLayoutManager(new GridLayoutManager(getThisContext(),2));
        mlist.addItemDecoration(new SpaceItemDecoration(UEMethod.dp2px(getThisContext(),10)));
        adapter = new DZSAdapter(new ArrayList<>());
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                BookBean.ListBean item = (BookBean.ListBean) adapter.getItem(position);
                HashMap<String, String> main = new HashMap<>();


                HashMap<String, String> param = new HashMap<>();
                param.put("detail",new Gson().toJson(item));

                main.put("fragment",HxdlRouterConfig.HXDL_DZSDETAIL);
                main.put("params",new Gson().toJson(param));

                ARouter.getInstance().build(HxdlRouterConfig.MAIN_FRAGMENT)
                        .withString("params",GsonUtil.GsonString(main))
                        .withTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom)
                        .navigation();
            }
        });
        mlist.setAdapter(adapter);
        initList();
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadMoreListener(this);
    }
    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mPageIndex = 1;
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
        mPresenter.getBookList(parms);
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
                        case "getBookList":
                            List<BookBean.ListBean> list=null;
                            if(object!=null){
                                BookBean bookBean = GsonUtil.GsonToBean(object, BookBean.class);
                                list = bookBean.getList();
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
       // ToastUtil.showSnack(getThisContext(),message);
    }


}
