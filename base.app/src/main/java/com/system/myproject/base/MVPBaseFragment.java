package com.system.myproject.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.system.myproject.BaseConfig;
import com.system.myproject.utils.SPUtils;

import java.lang.reflect.Type;
import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * 创建人： zhoudingwen
 * 创建时间：2018/4/2
 */

public abstract class MVPBaseFragment<V,P extends MVPBasePresenter<V>> extends TMBaseFragment {
    /*分页相关*/
    /* 每页最大分页数量 */
    public int mPageSize = 20;
    public static final int mMaxPageSize = Integer.MAX_VALUE;
    public static final int mNormalPageSize = 20;
    /* 当前页码 */
    public int mPageIndex = 1;
    public View rootView;
    protected P mPresenter;
    private Unbinder unbinder;
    public String mtoken;
    public String mUser;
    public String mUserId;

    public static final int REQUEST_CODE = 100;
    public static final int REQUEST_DATA = 101;
    public static final int REQUEST_CAMERA = 112;
    public static final int REQUEST_IMAGE = 102;
    public static final int REQUEST_FILE = 103;
    public static final int REQUEST_HANDWRITTEN = 110;
    public static final int REQUEST_AUDIO = 104;
    public static final int REQUEST_VIDEO = 105;
    public static final int REQUEST_MAP = 106;
    public static final int REQUEST_REFRESH = 108;
    public static final int REQUEST_SLICKFLOW_INFO = 111;
    public static final int REQUEST_BACKGROUND = 130;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Arouter注入
        ARouter.getInstance().inject(this);
        init();
        //MVP
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView((V) this);
        }
        String token = BaseConfig.getToken(getContext());
        if(!TextUtils.isEmpty(token)){
            mtoken=token;
        }
        String user = (String) SPUtils.get(getThisContext(), BaseConfig.USER, "");
        if(!TextUtils.isEmpty(user)){
            mUser=user;
        }
        String userid = (String) SPUtils.get(getThisContext(), BaseConfig.USERID, "");
        if(!TextUtils.isEmpty(userid)){
            mUserId=userid;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(getLayoutResId(), container, false);
        unbinder= ButterKnife.bind(this, rootView);
        return rootView;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initDatas();
    }

    /**
     * 创建Presenter
     * @return
     */
    protected abstract P createPresenter();

    /**
     * 初始化
     */
    protected abstract void init();

    /**
     * 返回布局资源ID
     *
     * @return
     */
    protected abstract int getLayoutResId();

    /**
     * 实现功能，填充数据
     */
    protected abstract void initDatas();
    /**
     * 获取context
     *
     * @return
     */
    public Context getThisContext() {
        return getActivity();
    }
    /**
     * 关闭Fragment
     */
    public void closeFragment() {
        Bundle bundle = new Bundle();
        setFragmentResult(RESULT_OK, bundle);
        pop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
    /**
     * 根据String获取参数
     *
     * @param params
     * @return
     */
    public HashMap<String, Object> getHashMapByParams(String params) {
        if (TextUtils.isEmpty(params)) {
            return new HashMap<>();
        }
        Type type = new TypeToken<HashMap<String, Object>>() {
        }.getType();
        HashMap<String, Object> filter = new Gson().fromJson(params, type);
        return filter;
    }
}
