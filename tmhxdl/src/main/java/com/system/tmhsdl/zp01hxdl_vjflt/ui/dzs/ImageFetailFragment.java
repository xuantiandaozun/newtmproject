package com.system.tmhsdl.zp01hxdl_vjflt.ui.dzs;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.system.myproject.base.MVPBaseFragment;

import com.system.myproject.utils.GsonUtil;
import com.system.myproject.utils.ToastUtil;
import com.system.tmhsdl.zp01hxdl_vjflt.HxdlRouterConfig;
import com.system.tmhsdl.zp01hxdl_vjflt.R;
import com.system.tmhsdl.zp01hxdl_vjflt.R2;
import com.system.tmhsdl.zp01hxdl_vjflt.bean.EventCallBackBean;
import com.system.tmhsdl.zp01hxdl_vjflt.ui.dzs.adapter.IVBottomAdapter;
import com.system.tmhsdl.zp01hxdl_vjflt.ui.dzs.adapter.IVMainAdapter;
import com.system.tmhsdl.zp01hxdl_vjflt.ui.dzs.bean.ContentListBean;
import com.system.tmhsdl.zp01hxdl_vjflt.ui.dzs.contract.DzsContract;
import com.system.tmhsdl.zp01hxdl_vjflt.ui.dzs.presenter.DzsPresenter;
import com.system.uilibrary.views.titlebar.TitleBarView;
import com.tenma.ventures.bean.utils.TMSharedPUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import butterknife.BindView;

@Route(path = HxdlRouterConfig.HXDL_IMAGIVE)
public class ImageFetailFragment extends MVPBaseFragment<DzsContract.View, DzsPresenter>
        implements DzsContract.View  {
    @BindView(R2.id.titleBar)
    TitleBarView titleBar;
    @BindView(R2.id.mainlist)
    RecyclerView mainList;
    @BindView(R2.id.bottomList)
    RecyclerView bottomList;
    @Autowired(name = "params")
    public String params;


    private int themeColor;
    private int textcolor;
    private String id;
    private String type;
    private IVMainAdapter adapter;
    private IVBottomAdapter bottomAdapter;
    private int count;

    @Override
    protected DzsPresenter createPresenter() {
        return new DzsPresenter();
    }

    @Override
    protected void init() {
        HashMap<String, Object> filter = getHashMapByParams(params);
        if (filter != null && filter.containsKey("id")) {
            id = filter.get("id").toString();
        }
        if (filter != null && filter.containsKey("type")) {
            type = filter.get("type").toString();
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.hxdl_fragment_imagedetail;
    }

    @Override
    protected void initDatas() {
        themeColor = Color.parseColor(TMSharedPUtil.getTMThemeColor(getActivity()));
        textcolor = Color.parseColor(TMSharedPUtil.getTMTitleTextColor(getActivity()));
        titleBar.setBackgroundColor(themeColor);
        titleBar.setTitleMainTextColor(textcolor);
       // String charSequence =  1 + "/" + list.size();
      //  titleBar.setTitleMainText(charSequence);
        titleBar.setLeftTextDrawable(R.mipmap.icon_nav_back)
                .setOnLeftTextClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pop();
                    }
                });
        LinearLayoutManager layout = new LinearLayoutManager(getContext());

        layout.setOrientation(LinearLayoutManager.HORIZONTAL);
        mainList.setLayoutManager(layout);
        adapter = new IVMainAdapter(new ArrayList<>());
        mainList.setAdapter(adapter);
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(mainList);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                int visibility = bottomList.getVisibility();
                if(visibility==View.VISIBLE){
                    bottomList.setVisibility(View.GONE);
                }else {
                    bottomList.setVisibility(View.VISIBLE);

                }
            }
        });
        mainList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager l = (LinearLayoutManager)recyclerView.getLayoutManager();
                int adapterNowPos = l.findFirstVisibleItemPosition();
                int position = adapterNowPos + 1;
                List<ContentListBean.ListBean> data = adapter.getData();
                String charSequence =  (adapterNowPos+1) + "/" + count;
                titleBar.setTitleMainText(charSequence);
                if(position==data.size()){
                    if(data.size()<count){
                        mPageIndex++;
                        getContList();
                    }
                }

            }
        });

        LinearLayoutManager layout1 = new LinearLayoutManager(getContext());
        layout1.setOrientation(LinearLayoutManager.HORIZONTAL);
        bottomList.setLayoutManager(layout1);
        bottomAdapter = new IVBottomAdapter(new ArrayList<>());
        bottomAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mainList.scrollToPosition(position);
                bottomList.setVisibility(View.GONE);
            }
        });
        bottomList.setAdapter(bottomAdapter);
        bottomList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager l = (LinearLayoutManager)recyclerView.getLayoutManager();
                int adapterNowPos = l.findFirstVisibleItemPosition();
                int position = adapterNowPos + 1;
                List<ContentListBean.ListBean> data = bottomAdapter.getData();
                if(position== data.size()){
                    if(data.size()<count){
                        mPageIndex++;
                        getContList();
                    }
                }


            }
        });


        getContList();
    }

    private void getContList() {
        HashMap<String, Object> parms = new HashMap<>();
        parms.put("id",id);
        parms.put("sid",type);
        parms.put("page",mPageIndex);
        mPresenter.getContentList(parms);
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
                        case "getContentList":
                            ContentListBean contentListBean = GsonUtil.GsonToBean(object, ContentListBean.class);
                            count = contentListBean.getCount();
                            List<ContentListBean.ListBean> list = contentListBean.getList();
                            if(mPageIndex==1){
                                adapter.setNewData(list);
                                bottomAdapter.setNewData(list);
                            }else {
                                adapter.addData(list);
                                bottomAdapter.addData(list);
                            }

                        //    ultraViewPager.setCurrentItem(1);
                            break;
                        case "getContent":


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
