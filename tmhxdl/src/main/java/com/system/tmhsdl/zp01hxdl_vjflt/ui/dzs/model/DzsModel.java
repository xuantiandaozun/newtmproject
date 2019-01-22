package com.system.tmhsdl.zp01hxdl_vjflt.ui.dzs.model;

import com.system.tmhsdl.zp01hxdl_vjflt.bean.TMBaseResoultEntity;
import com.system.tmhsdl.zp01hxdl_vjflt.ui.dzs.api.MainService;
import com.system.tmhsdl.zp01hxdl_vjflt.ui.dzs.contract.DzsContract;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

public class DzsModel implements DzsContract.Model {
    @Override
    public Observable<TMBaseResoultEntity<Object>> getInssnList(HashMap<String, Object> body) {
        return MainService.getService().getInssnList(body)
                //在新线程中执行登录请求
                .subscribeOn(Schedulers.newThread())
                //在主线程中执行;
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<TMBaseResoultEntity<Object>> getBookList(HashMap<String, Object> body) {
        return MainService.getService().getBookList(body)
                //在新线程中执行登录请求
                .subscribeOn(Schedulers.newThread())
                //在主线程中执行;
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<TMBaseResoultEntity<Object>> getIssnDetail(HashMap<String, Object> body) {
        return MainService.getService().getIssnDetail(body)
                //在新线程中执行登录请求
                .subscribeOn(Schedulers.newThread())
                //在主线程中执行;
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<TMBaseResoultEntity<Object>> getIssnDetail2(HashMap<String, Object> body) {
        return MainService.getService().getIssnDetail2(body)
                //在新线程中执行登录请求
                .subscribeOn(Schedulers.newThread())
                //在主线程中执行;
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<TMBaseResoultEntity<Object>> getBookDetail(HashMap<String, Object> body) {
        return MainService.getService().getBookDetail(body)
                //在新线程中执行登录请求
                .subscribeOn(Schedulers.newThread())
                //在主线程中执行;
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<TMBaseResoultEntity<Object>> getBookDetail2(HashMap<String, Object> body) {
        return MainService.getService().getBookDetail2(body)
                //在新线程中执行登录请求
                .subscribeOn(Schedulers.newThread())
                //在主线程中执行;
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<TMBaseResoultEntity<Object>> getRecommendList(HashMap<String, Object> body) {
        return MainService.getService().getRecommendList(body)
                //在新线程中执行登录请求
                .subscribeOn(Schedulers.newThread())
                //在主线程中执行;
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<TMBaseResoultEntity<Object>> getApiIndex(HashMap<String, Object> body) {
        return MainService.getService().getApiIndex(body)
                //在新线程中执行登录请求
                .subscribeOn(Schedulers.newThread())
                //在主线程中执行;
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<TMBaseResoultEntity<Object>> getContentList(HashMap<String, Object> body) {
        return MainService.getService().getContentList(body)
                //在新线程中执行登录请求
                .subscribeOn(Schedulers.newThread())
                //在主线程中执行;
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<TMBaseResoultEntity<Object>> getContent(HashMap<String, Object> body) {
        return MainService.getService().getContent(body)
                //在新线程中执行登录请求
                .subscribeOn(Schedulers.newThread())
                //在主线程中执行;
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<TMBaseResoultEntity<Object>> getPay(RequestBody body) {
        return MainService.getService().getPay(body)
                //在新线程中执行登录请求
                .subscribeOn(Schedulers.newThread())
                //在主线程中执行;
                .observeOn(AndroidSchedulers.mainThread());
    }
}
