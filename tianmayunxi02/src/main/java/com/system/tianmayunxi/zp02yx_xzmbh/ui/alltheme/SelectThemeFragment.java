package com.system.tianmayunxi.zp02yx_xzmbh.ui.alltheme;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.library.flowlayout.FlowLayoutManager;
import com.library.flowlayout.SpaceItemDecoration;
import com.system.myproject.base.MVPBaseFragment;
import com.system.myproject.base.TMBaseFragment;
import com.system.myproject.utils.GsonUtil;
import com.system.myproject.utils.ToastUtil;
import com.system.myproject.utils.UEMethod;
import com.system.tianmayunxi.zp02yx_xzmbh.R;
import com.system.tianmayunxi.zp02yx_xzmbh.R2;
import com.system.tianmayunxi.zp02yx_xzmbh.Tmyx02RouterConfig;
import com.system.tianmayunxi.zp02yx_xzmbh.bean.EventCallBackBean;
import com.system.tianmayunxi.zp02yx_xzmbh.ui.alltheme.adapter.ThemeListAdapter;
import com.system.tianmayunxi.zp02yx_xzmbh.ui.officialrecommend.bean.CommonSeeBean;
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

@Route(path = Tmyx02RouterConfig.TMYX02_XZZT)
public class SelectThemeFragment extends MVPBaseFragment<OfficContract.View, OfficPresenter>
        implements OfficContract.View {
    @BindView(R2.id.titleBar)
    TitleBarView titleBar;
    @BindView(R2.id.mlist)
    RecyclerView mlist;
    private ThemeListAdapter adapter;
    private int themeColor;
    private int textcolor;

    @Override
    protected OfficPresenter createPresenter() {
        return new OfficPresenter();
    }

    @Override
    protected void init() {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_selecttheme_zp01yx_bwusb2;
    }

    @Override
    protected void initDatas() {
        themeColor = Color.parseColor(TMSharedPUtil.getTMThemeColor(getActivity()));
        textcolor = Color.parseColor(TMSharedPUtil.getTMTitleTextColor(getActivity()));
        titleBar.setBackgroundColor(themeColor);
        titleBar.setTitleMainTextColor(textcolor);

        titleBar.setTitleMainText("选择主题")
                .setLeftText("取消")
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
        FlowLayoutManager flowLayoutManager = new FlowLayoutManager();
        mlist.setLayoutManager(flowLayoutManager);
        mlist.addItemDecoration(new SpaceItemDecoration(UEMethod.dp2px(getThisContext(),5)));
        adapter = new ThemeListAdapter(new ArrayList<>());
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                TMBaseFragment fragment=null;
                CommonSeeBean.ListBean item = (CommonSeeBean.ListBean) adapter.getItem(position);
                HashMap<String, String> param = new HashMap<>();
                param.put("detail",GsonUtil.GsonString(item));

                fragment = (TMBaseFragment) ARouter.getInstance()
                        .build(Tmyx02RouterConfig.TMYX02_ADDZT)
                        .withString("params",GsonUtil.GsonString(param))
                        .navigation();
                start(fragment);
            }
        });
        mlist.setAdapter(adapter);
        initList();
    }

    private void initList() {
        HashMap<String, Object> parms = new HashMap<>();
        parms.put("is_index","0");
        mPresenter.getTheme(parms);
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
                            CommonSeeBean commonSeeBean = GsonUtil.GsonToBean(object, CommonSeeBean.class);
                            List<CommonSeeBean.ListBean> list = commonSeeBean.getList();
                            adapter.setNewData(list);
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
