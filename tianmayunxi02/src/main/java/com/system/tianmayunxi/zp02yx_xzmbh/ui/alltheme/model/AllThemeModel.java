package com.system.tianmayunxi.zp02yx_xzmbh.ui.alltheme.model;

import com.system.tianmayunxi.zp02yx_xzmbh.bean.TMBaseResoultEntity;
import com.system.tianmayunxi.zp02yx_xzmbh.ui.alltheme.api.MainService;
import com.system.tianmayunxi.zp02yx_xzmbh.ui.alltheme.contract.AllThemeContract;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class AllThemeModel  implements AllThemeContract.Model {
    @Override
    public Observable<TMBaseResoultEntity<Object>> getAllThemeList(HashMap<String, Object> body) {
        return MainService.getService().getAllThemeList(body)
                //在新线程中执行登录请求
                .subscribeOn(Schedulers.newThread())
                //在主线程中执行;
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<TMBaseResoultEntity<Object>> addSubscription(RequestBody body) {
        return MainService.getService().addSubscription(body)
                //在新线程中执行登录请求
                .subscribeOn(Schedulers.newThread())
                //在主线程中执行;
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<TMBaseResoultEntity<Object>> pushArticle(RequestBody body) {
        return MainService.getService().pushArticle(body)
                //在新线程中执行登录请求
                .subscribeOn(Schedulers.newThread())
                //在主线程中执行;
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<TMBaseResoultEntity<Object>> unSubscribe(RequestBody body) {
        return MainService.getService().unSubscribe(body)
                //在新线程中执行登录请求
                .subscribeOn(Schedulers.newThread())
                //在主线程中执行;
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<TMBaseResoultEntity<Object>> uploadIv(List<MultipartBody.Part> partList) {
        return com.system.tianmayunxi.zp02yx_xzmbh.api.MainService.getMainService().uploadFile(partList)
                //在新线程中执行登录请求
                .subscribeOn(Schedulers.newThread())
                //在主线程中执行;
                .observeOn(AndroidSchedulers.mainThread());
    }
}
