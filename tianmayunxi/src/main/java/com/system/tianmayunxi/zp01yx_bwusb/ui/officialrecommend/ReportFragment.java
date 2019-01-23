package com.system.tianmayunxi.zp01yx_bwusb.ui.officialrecommend;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.system.myproject.base.MVPBaseFragment;
import com.system.myproject.base.TMBaseFragment;
import com.system.myproject.utils.GsonUtil;
import com.system.myproject.utils.ToastUtil;
import com.system.tianmayunxi.zp01yx_bwusb.R;
import com.system.tianmayunxi.zp01yx_bwusb.R2;
import com.system.tianmayunxi.zp01yx_bwusb.TmyxRouterConfig;
import com.system.tianmayunxi.zp01yx_bwusb.bean.EventCallBackBean;
import com.system.tianmayunxi.zp01yx_bwusb.ui.officialrecommend.adapter.ReportAdapter;
import com.system.tianmayunxi.zp01yx_bwusb.ui.officialrecommend.bean.ArticMsgBean;
import com.system.tianmayunxi.zp01yx_bwusb.ui.officialrecommend.bean.ArticleDetail;
import com.system.tianmayunxi.zp01yx_bwusb.ui.officialrecommend.bean.ReportBean;
import com.system.tianmayunxi.zp01yx_bwusb.ui.officialrecommend.contract.OfficContract;
import com.system.tianmayunxi.zp01yx_bwusb.ui.officialrecommend.presenter.OfficPresenter;
import com.system.tianmayunxi.zp01yx_bwusb.views.titlebar.TitleBarView;
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

@Route(path = TmyxRouterConfig.TMYX_REPORT)
public class ReportFragment extends MVPBaseFragment<OfficContract.View, OfficPresenter>
        implements OfficContract.View {
    @BindView(R2.id.titleBar)
    TitleBarView titleBar;
    @BindView(R2.id.mlist)
    RecyclerView mlist;
    @Autowired(name = "params")
    public String params;
    private ReportAdapter adapter;
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
        return R.layout.fragment_report_zp01yx_bwusb;
    }

    @Override
    protected void initDatas() {
        themeColor = Color.parseColor(TMSharedPUtil.getTMThemeColor(getActivity()));
        textcolor = Color.parseColor(TMSharedPUtil.getTMTitleTextColor(getActivity()));

        titleBar.setBackgroundColor(themeColor);
        titleBar.setTitleMainTextColor(textcolor);

        titleBar.setTitleMainText("我要举报")
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
        adapter = new ReportAdapter(new ArrayList<>());
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<ReportBean> data = adapter.getData();
                for (int i = 0; i < data.size(); i++) {
                    ReportBean reportBean = data.get(i);
                    if (i == position) {
                        reportBean.setCheck(true);
                    } else {
                        reportBean.setCheck(false);
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });
        mlist.setAdapter(adapter);
        initList();
    }

    @OnClick({R2.id.btn_next})
    public void onClick(View view) {
        TMBaseFragment fragment = null;
        if (view.getId() == R.id.btn_next) {
            List<ReportBean> data = adapter.getData();
            for (int i = 0; i < data.size(); i++) {
                ReportBean reportBean = data.get(i);
                if (reportBean.isCheck()) {
                    reportMsg(reportBean.getContent());
                }
            }

        }
    }

    private void reportMsg(String content) {
        HashMap<String, String> parms = new HashMap<>();
        parms.put("mid", id + "");
        parms.put("content", content);
        String value = new Gson().toJson(parms);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), value);
        mPresenter.reportMsg(body);
    }

    private void initList() {
        List<ReportBean> data = adapter.getData();
        data.add(new ReportBean("垃圾营销号"));
        data.add(new ReportBean("低俗色情"));
        data.add(new ReportBean("标题与内容不符"));
        data.add(new ReportBean("敏感信息"));
        data.add(new ReportBean("造谣/ 诽谤/内容失实"));
        adapter.notifyDataSetChanged();
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
                        case "reportMsg":
                            pop();
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
