package com.system.tianmayunxi.zp02yx_xzmbh.ui.myissue;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.system.myproject.base.BaseFragment;
import com.system.myproject.base.TMBaseFragment;
import com.system.myproject.utils.GsonUtil;
import com.system.tianmayunxi.zp02yx_xzmbh.R;
import com.system.tianmayunxi.zp02yx_xzmbh.R2;
import com.system.tianmayunxi.zp02yx_xzmbh.Tmyx02RouterConfig;
import com.system.tianmayunxi.zp02yx_xzmbh.bean.EventCallBackBean;
import com.system.tianmayunxi.zp02yx_xzmbh.ui.myissue.adapter.MessageAdapter;
import com.system.tianmayunxi.zp02yx_xzmbh.ui.officialrecommend.bean.MessageBean;
import com.system.tianmayunxi.zp02yx_xzmbh.ui.officialrecommend.contract.OfficContract;
import com.system.tianmayunxi.zp02yx_xzmbh.ui.officialrecommend.presenter.OfficPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import butterknife.BindView;

@Route(path = Tmyx02RouterConfig.WDFB02_MESSAGE)
public class MessageFragment extends BaseFragment<OfficContract.View, OfficPresenter>
        implements OfficContract.View, OnRefreshListener, OnLoadMoreListener {
    @BindView(R2.id.mlist)
    RecyclerView mlist;
    @BindView(R2.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private MessageAdapter adapter;

    @Override
    protected OfficPresenter createPresenter() {
        return new OfficPresenter();
    }

    @Override
    protected void init() {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_message_zp01yx_bwusb2;
    }

    @Override
    protected void initDatas() {
        mlist.setLayoutManager(new LinearLayoutManager(getThisContext()));
        DividerItemDecoration decor = new DividerItemDecoration(getThisContext(), DividerItemDecoration.VERTICAL);
        decor.setDrawable(ContextCompat.getDrawable(getThisContext(),R.drawable.recyleview_line));
        mlist.addItemDecoration(decor);
        adapter = new MessageAdapter(new ArrayList<>());
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                TMBaseFragment fragment=null;
                TMBaseFragment parentFragment=null;
                MessageBean.ListBean item = (MessageBean.ListBean) adapter.getItem(position);
                if(!TextUtils.isEmpty(item.getAid())){
                    int type = item.getType();
                    switch (type){
                        case 1:

                            break;
                        case 2:
                            HashMap<String, String> main = new HashMap<>();


                            HashMap<String, String> param = new HashMap<>();
                            param.put("id",item.getAid()+"");

                            main.put("fragment", Tmyx02RouterConfig.TMYX02_TIDETAIL);
                            main.put("params",new Gson().toJson(param));

                            ARouter.getInstance().build(Tmyx02RouterConfig.MAIN02_FRAGMENT)
                                    .withString("params",GsonUtil.GsonString(main))
                                    .navigation();

                            break;
                    }
                }else {

                }

            }
        });
        mlist.setAdapter(adapter);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadMoreListener(this);
        initList();
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
        mPresenter.getMyMessage(parms);
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
                        case "getMyMessage":
                          /*  List<MessageBean>  list = new Gson().fromJson(GsonString(object), new TypeToken<List<MessageBean>>() {
                            }.getType());*/
                         //   adapter.addData(list);
                            List<MessageBean.ListBean> list=null;
                            if(object!=null){
                                MessageBean messageBean = GsonUtil.GsonToBean(object, MessageBean.class);
                                list = messageBean.getList();
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
      //  ToastUtil.showSnack(getThisContext(),message);
    }

}
