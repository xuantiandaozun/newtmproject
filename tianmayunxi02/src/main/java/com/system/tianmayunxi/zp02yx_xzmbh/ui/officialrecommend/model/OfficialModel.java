package com.system.tianmayunxi.zp02yx_xzmbh.ui.officialrecommend.model;



import com.system.tianmayunxi.zp02yx_xzmbh.bean.TMBaseResoultEntity;
import com.system.tianmayunxi.zp02yx_xzmbh.ui.officialrecommend.api.MainService;
import com.system.tianmayunxi.zp02yx_xzmbh.ui.officialrecommend.contract.OfficContract;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

public class OfficialModel implements OfficContract.Model{
    @Override
    public Observable<TMBaseResoultEntity<Object>> getTheme(HashMap<String,Object> body) {
        return MainService.getService().getTheme(body)
                //在新线程中执行登录请求
                .subscribeOn(Schedulers.newThread())
                //在主线程中执行;
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<TMBaseResoultEntity<Object>> getArticleList(HashMap<String, Object> body) {
        return MainService.getService().getArticleList(body)
                //在新线程中执行登录请求
                .subscribeOn(Schedulers.newThread())
                //在主线程中执行;
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<TMBaseResoultEntity<Object>> getArticle(HashMap<String, Object> body) {
        return MainService.getService().getArticle(body)
                //在新线程中执行登录请求
                .subscribeOn(Schedulers.newThread())
                //在主线程中执行;
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<TMBaseResoultEntity<Object>> getArticMsgList(HashMap<String, Object> body) {
        return MainService.getService().getArticMsgList(body)
                //在新线程中执行登录请求
                .subscribeOn(Schedulers.newThread())
                //在主线程中执行;
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<TMBaseResoultEntity<Object>> msgInfoDetail(HashMap<String, Object> body) {
        return MainService.getService().msgInfoDetail(body)
                //在新线程中执行登录请求
                .subscribeOn(Schedulers.newThread())
                //在主线程中执行;
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<TMBaseResoultEntity<Object>> msgInfoList(HashMap<String, Object> body) {
        return MainService.getService().msgInfoList(body)
                //在新线程中执行登录请求
                .subscribeOn(Schedulers.newThread())
                //在主线程中执行;
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<TMBaseResoultEntity<Object>> getMyArtic(HashMap<String, Object> body) {
        return MainService.getService().getMyArtic(body)
                //在新线程中执行登录请求
                .subscribeOn(Schedulers.newThread())
                //在主线程中执行;
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<TMBaseResoultEntity<Object>> getArticleListNoLogin(HashMap<String, Object> body) {
        return MainService.getService().getArticleListNoLogin(body)
                //在新线程中执行登录请求
                .subscribeOn(Schedulers.newThread())
                //在主线程中执行;
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<TMBaseResoultEntity<Object>> pushMsg(RequestBody body) {
        return MainService.getService().pushMsg(body)
                //在新线程中执行登录请求
                .subscribeOn(Schedulers.newThread())
                //在主线程中执行;
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<TMBaseResoultEntity<Object>> msgLike(RequestBody body) {
        return MainService.getService().msgLike(body)
                //在新线程中执行登录请求
                .subscribeOn(Schedulers.newThread())
                //在主线程中执行;
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<TMBaseResoultEntity<Object>> artcleLike(RequestBody body) {
        return MainService.getService().artcleLike(body)
                //在新线程中执行登录请求
                .subscribeOn(Schedulers.newThread())
                //在主线程中执行;
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<TMBaseResoultEntity<Object>> isSign(RequestBody body) {
        return MainService.getService().isSign(body)
                //在新线程中执行登录请求
                .subscribeOn(Schedulers.newThread())
                //在主线程中执行;
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<TMBaseResoultEntity<Object>> exchangeGoods(RequestBody body) {
        return MainService.getService().exchangeGoods(body)
                //在新线程中执行登录请求
                .subscribeOn(Schedulers.newThread())
                //在主线程中执行;
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<TMBaseResoultEntity<Object>> reportMsg(RequestBody body) {
        return MainService.getService().reportMsg(body)
                //在新线程中执行登录请求
                .subscribeOn(Schedulers.newThread())
                //在主线程中执行;
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<TMBaseResoultEntity<Object>> delMsg(RequestBody body) {
        return MainService.getService().delMsg(body)
                //在新线程中执行登录请求
                .subscribeOn(Schedulers.newThread())
                //在主线程中执行;
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<TMBaseResoultEntity<Object>> addStar(RequestBody body) {
        return MainService.getService().addStar(body)
                //在新线程中执行登录请求
                .subscribeOn(Schedulers.newThread())
                //在主线程中执行;
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<TMBaseResoultEntity<Object>> checkIsStar(RequestBody body) {
        return MainService.getService().checkIsStar(body)
                //在新线程中执行登录请求
                .subscribeOn(Schedulers.newThread())
                //在主线程中执行;
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<TMBaseResoultEntity<Object>> deleteStar(RequestBody body) {
        return MainService.getService().deleteStar(body)
                //在新线程中执行登录请求
                .subscribeOn(Schedulers.newThread())
                //在主线程中执行;
                .observeOn(AndroidSchedulers.mainThread());
    }


    @Override
    public Observable<TMBaseResoultEntity<Object>> isRemind(RequestBody body) {
        return MainService.getService().isRemind(body)
                //在新线程中执行登录请求
                .subscribeOn(Schedulers.newThread())
                //在主线程中执行;
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<TMBaseResoultEntity<Object>> remind(RequestBody body) {
        return MainService.getService().remind(body)
                //在新线程中执行登录请求
                .subscribeOn(Schedulers.newThread())
                //在主线程中执行;
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<TMBaseResoultEntity<Object>> AllBindService(RequestBody body) {
        return MainService.getService().AllBindService(body)
                //在新线程中执行登录请求
                .subscribeOn(Schedulers.newThread())
                //在主线程中执行;
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<TMBaseResoultEntity<Object>> BindScore(RequestBody body) {
        return MainService.getService().BindScore(body)
                //在新线程中执行登录请求
                .subscribeOn(Schedulers.newThread())
                //在主线程中执行;
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<TMBaseResoultEntity<Object>> loginscore(RequestBody body) {
        return MainService.getService().loginscore(body)
                //在新线程中执行登录请求
                .subscribeOn(Schedulers.newThread())
                //在主线程中执行;
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<TMBaseResoultEntity<Object>> loginLog(RequestBody body) {
        return MainService.getService().loginLog(body)
                //在新线程中执行登录请求
                .subscribeOn(Schedulers.newThread())
                //在主线程中执行;
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<TMBaseResoultEntity<Object>> getSignRule(RequestBody body) {
        return MainService.getService().getSignRule(body)
                //在新线程中执行登录请求
                .subscribeOn(Schedulers.newThread())
                //在主线程中执行;
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<TMBaseResoultEntity<Object>> getMyMessage(HashMap<String,Object> body) {
        return MainService.getService().getMyMessage(body)
                //在新线程中执行登录请求
                .subscribeOn(Schedulers.newThread())
                //在主线程中执行;
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<TMBaseResoultEntity<Object>> getMyPoint(HashMap<String, Object> body) {
        return MainService.getService().getMyPoint(body)
                //在新线程中执行登录请求
                .subscribeOn(Schedulers.newThread())
                //在主线程中执行;
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<TMBaseResoultEntity<Object>> getSignList(HashMap<String, Object> body) {
        return MainService.getService().getSignList(body)
                //在新线程中执行登录请求
                .subscribeOn(Schedulers.newThread())
                //在主线程中执行;
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<TMBaseResoultEntity<Object>> getGoodsList(HashMap<String, Object> body) {
        return MainService.getService().getGoodsList(body)
                //在新线程中执行登录请求
                .subscribeOn(Schedulers.newThread())
                //在主线程中执行;
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<TMBaseResoultEntity<Object>> Sign(HashMap<String, Object> body) {
        return MainService.getService().Sign(body)
                //在新线程中执行登录请求
                .subscribeOn(Schedulers.newThread())
                //在主线程中执行;
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<TMBaseResoultEntity<Object>> GoodsDetail(HashMap<String, Object> body) {
        return MainService.getService().GoodsDetail(body)
                //在新线程中执行登录请求
                .subscribeOn(Schedulers.newThread())
                //在主线程中执行;
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<TMBaseResoultEntity<Object>> oneTheme(HashMap<String, Object> body) {
        return MainService.getService().oneTheme(body)
                //在新线程中执行登录请求
                .subscribeOn(Schedulers.newThread())
                //在主线程中执行;
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<TMBaseResoultEntity<Object>> isSub(HashMap<String, Object> body) {
        return MainService.getService().isSub(body)
                //在新线程中执行登录请求
                .subscribeOn(Schedulers.newThread())
                //在主线程中执行;
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<TMBaseResoultEntity<Object>> addSubscription(RequestBody body) {
        return com.system.tianmayunxi.zp02yx_xzmbh.ui.alltheme.api.MainService.getService().addSubscription(body)
                //在新线程中执行登录请求
                .subscribeOn(Schedulers.newThread())
                //在主线程中执行;
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<TMBaseResoultEntity<Object>> unSubscribe(RequestBody body) {
        return com.system.tianmayunxi.zp02yx_xzmbh.ui.alltheme.api.MainService.getService().unSubscribe(body)
                //在新线程中执行登录请求
                .subscribeOn(Schedulers.newThread())
                //在主线程中执行;
                .observeOn(AndroidSchedulers.mainThread());
    }
}
