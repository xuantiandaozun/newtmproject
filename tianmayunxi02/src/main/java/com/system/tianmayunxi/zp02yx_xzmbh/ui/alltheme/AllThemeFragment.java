package com.system.tianmayunxi.zp02yx_xzmbh.ui.alltheme;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.library.flowlayout.SpaceItemDecoration;
import com.system.myproject.base.BaseFragment;
import com.system.myproject.utils.GsonUtil;
import com.system.myproject.utils.UEMethod;
import com.system.tianmayunxi.zp02yx_xzmbh.R;
import com.system.tianmayunxi.zp02yx_xzmbh.R2;
import com.system.tianmayunxi.zp02yx_xzmbh.Tmyx02RouterConfig;
import com.system.tianmayunxi.zp02yx_xzmbh.bean.EventCallBackBean;
import com.system.tianmayunxi.zp02yx_xzmbh.ui.alltheme.adapter.ThemeAdapter;
import com.system.tianmayunxi.zp02yx_xzmbh.ui.alltheme.bean.AllThemBean;
import com.system.tianmayunxi.zp02yx_xzmbh.ui.alltheme.contract.AllThemeContract;
import com.system.tianmayunxi.zp02yx_xzmbh.ui.alltheme.presenter.AllThemePresenter;
import com.tenma.ventures.bean.utils.TMSharedPUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import okhttp3.RequestBody;

@Route(path = Tmyx02RouterConfig.TMYX02_QBZT)
public class AllThemeFragment extends BaseFragment<AllThemeContract.View, AllThemePresenter>
        implements AllThemeContract.View {
    @BindView(R2.id.mlist)
    RecyclerView mlist;
    private ThemeAdapter adapter;

    @Override
    protected AllThemePresenter createPresenter() {
        return new AllThemePresenter();
    }

    @Override
    protected void init() {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_alltheme_zp02yx_bwusb;
    }

    @Override
    protected void initDatas() {
        mlist.setLayoutManager(new GridLayoutManager(getThisContext(),3));
        mlist.addItemDecoration(new SpaceItemDecoration(UEMethod.dp2px(getThisContext(),5)));
        adapter = new ThemeAdapter(new ArrayList<>());
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String tmToken = TMSharedPUtil.getTMToken(getContext());

                if(TextUtils.isEmpty(tmToken)){
                    Intent intent = new Intent(getActivity().getPackageName() + ".usercenter.login");
                    getActivity().startActivity(intent);
                    return;
                }

                AllThemBean.ListBean item = (AllThemBean.ListBean) adapter.getItem(position);

                HashMap<String, String> main = new HashMap<>();



                HashMap<String, String> param = new HashMap<>();
                param.put("detail",GsonUtil.GsonString(item));

                main.put("fragment", Tmyx02RouterConfig.TMYX02_THEMEDETAIL);
                main.put("params",new Gson().toJson(param));

                ARouter.getInstance().build(Tmyx02RouterConfig.MAIN02_FRAGMENT)
                        .withString("params",GsonUtil.GsonString(main))
                        .withTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom)
                        .navigation();


            }
        });
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                AllThemBean.ListBean item = (AllThemBean.ListBean) adapter.getItem(position);
                if(view.getId()==R.id.tv_statue){
                    if (!item.isIs_sub()){
                        addSubscription(item.getId());
                    }else {
                        unSubscription(item.getId());
                    }
              }
            }
        });
        mlist.setAdapter(adapter);

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            initList();
        }
    }



    private void unSubscription(int id) {
        HashMap<String, String> parms = new HashMap<>();
        parms.put("tid",id+"");
        String value = new Gson().toJson(parms);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), value);
        mPresenter.unSubscribe(body);
    }

    private void addSubscription(int id) {
        HashMap<String, String> parms = new HashMap<>();
        parms.put("tid",id+"");
        String value = new Gson().toJson(parms);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), value);
        mPresenter.addSubscription(body);
    }

    private void initList() {
        HashMap<String, Object> parms = new HashMap<>();
        mPresenter.getAllThemeList(parms);
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
                        case "getAllThemeList":
                            AllThemBean allThemBean = GsonUtil.GsonToBean(object, AllThemBean.class);
                            List<AllThemBean.ListBean> list = allThemBean.getList();
                            adapter.setNewData(list);
                            break;
                        case "addSubscription":
                        case "unSubscribe":
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
        //ToastUtil.showSnack(getThisContext(),message);
    }
}
