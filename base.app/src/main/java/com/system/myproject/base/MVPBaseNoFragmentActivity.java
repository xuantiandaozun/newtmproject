package com.system.myproject.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;

import com.alibaba.android.arouter.launcher.ARouter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.system.myproject.BaseConfig;
import com.system.myproject.utils.AppManager;
import com.system.myproject.utils.SPUtils;

import java.lang.reflect.Type;
import java.util.HashMap;

import butterknife.ButterKnife;

/**
 * 创建人： zhoudingwen
 * 创建时间：2018/4/2
 */

public abstract class MVPBaseNoFragmentActivity<V, P extends MVPBasePresenter<V>> extends AppCompatActivity {
    protected P mPresenter;
    public String mtoken;
    public String mUser;
    public String mUserId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);
        setTheme(getThemeResId());
        setContentView(LayoutInflater.from(this).inflate(
                getLayoutResId(), null));
        //初始化数据
        ButterKnife.bind(this);
        //Arouter注入
        ARouter.getInstance().inject(this);
        //Mvp
        mPresenter = createPresenter();
        //填充View
        if (mPresenter != null) {
            mPresenter.attachView((V) this);
        }
        String token = BaseConfig.getToken(getThisContext());
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
        initDatas();
        AppManager.addActivity(this);
    }
    /**
     * 创建presenter
     * @return
     */
    protected abstract P createPresenter();
    /**
     * 设置主题
     *
     * @return
     */
    protected abstract int getThemeResId();

    /**
     * 返回布局资源ID
     *
     * @return
     */
    protected abstract int getLayoutResId();

    /**
     * 初始化
     */
    protected abstract void init(Bundle savedInstanceState);

    /**
     * 实现功能，填充数据
     */
    protected abstract void initDatas();

    /**
     * 获取当前Context
     * @return
     */
    public Context getThisContext(){
        return this;
    }



    /**
     * 处理传值
     *
     * @return
     */
    public HashMap<String, Object> getFilter() {
        if (getIntent() != null && getIntent().getSerializableExtra("filter") != null) {
            HashMap<String, Object> filter = (HashMap<String, Object>) getIntent().getSerializableExtra("filter");
            return filter;
        }
        return null;
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
    /**
     * 跳转到指定页面
     *
     * @param cls
     */
    public void goToActivity(Class<?> cls) {
        goToActivity(cls, null);
    }

    /**
     * 跳转到指定页面
     *
     * @param cls
     * @param filter
     */
    public void goToActivity(Class<?> cls, HashMap<String, Object> filter) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (filter != null) {
            intent.putExtra("filter", filter);
        }
        startActivity(intent);
    }
    /**
     * 关闭Activity
     */
    public void CloseActivity() {
        finish();

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }


}
