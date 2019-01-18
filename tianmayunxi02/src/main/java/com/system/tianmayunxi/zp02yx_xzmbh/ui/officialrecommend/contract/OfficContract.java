package com.system.tianmayunxi.zp02yx_xzmbh.ui.officialrecommend.contract;

import com.system.myproject.base.MVPBaseView;
import com.system.tianmayunxi.zp02yx_xzmbh.bean.EventCallBackBean;
import com.system.tianmayunxi.zp02yx_xzmbh.bean.TMBaseResoultEntity;


import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.RequestBody;

public interface OfficContract {

    interface View extends MVPBaseView {
        void onFaild();
        void callBack(EventCallBackBean bean);
    }

    interface Presenter {
        void getTheme(HashMap<String, Object> body);
        void getArticleList(HashMap<String, Object> body);
        void getArticle(HashMap<String, Object> body);
        void getArticMsgList(HashMap<String, Object> body);
        void msgInfoList(HashMap<String, Object> body);
        void getMyArtic(HashMap<String, Object> body);
        void msgInfoDetail(HashMap<String, Object> body);
        void getArticleListNoLogin(HashMap<String, Object> body);
        void pushMsg(RequestBody body);
        void artcleLike(RequestBody body);
        void msgLike(RequestBody body);
        void isSign(RequestBody body);
        void reportMsg(RequestBody body);
        void delMsg(RequestBody body);
        void addStar(RequestBody body);
        void checkIsStar(RequestBody body);
        void deleteStar(RequestBody body);
        void isRemind(RequestBody body);
        void remind(RequestBody body);

        void exchangeGoods(RequestBody body);
        void getMyMessage(HashMap<String, Object> body);
        void getMyPoint(HashMap<String, Object> body);
        void getSignList(HashMap<String, Object> body);
        void getGoodsList(HashMap<String, Object> body);
        void Sign(HashMap<String, Object> body);
        void GoodsDetail(HashMap<String, Object> body);
        void oneTheme(HashMap<String, Object> body);
        void isSub(HashMap<String, Object> body);
        void addSubscription(RequestBody body);

        void homeRefresh(HashMap<String, Object> parms, HashMap<String, Object> parms2);
    }

    interface Model {
        Observable<TMBaseResoultEntity<Object>> getTheme(HashMap<String, Object> body);
        Observable<TMBaseResoultEntity<Object>> getArticleList(HashMap<String, Object> body);
        Observable<TMBaseResoultEntity<Object>> getArticle(HashMap<String, Object> body);
        Observable<TMBaseResoultEntity<Object>> getArticMsgList(HashMap<String, Object> body);
        Observable<TMBaseResoultEntity<Object>> msgInfoDetail(HashMap<String, Object> body);
        Observable<TMBaseResoultEntity<Object>> msgInfoList(HashMap<String, Object> body);
        Observable<TMBaseResoultEntity<Object>> getMyArtic(HashMap<String, Object> body);
        Observable<TMBaseResoultEntity<Object>> getArticleListNoLogin(HashMap<String,Object> body);

        Observable<TMBaseResoultEntity<Object>> pushMsg(RequestBody body);
        Observable<TMBaseResoultEntity<Object>> msgLike(RequestBody body);
        Observable<TMBaseResoultEntity<Object>> artcleLike(RequestBody body);
        Observable<TMBaseResoultEntity<Object>> isSign(RequestBody body);
        Observable<TMBaseResoultEntity<Object>> exchangeGoods(RequestBody body);
        Observable<TMBaseResoultEntity<Object>> reportMsg(RequestBody body);
        Observable<TMBaseResoultEntity<Object>> delMsg(RequestBody body);
        Observable<TMBaseResoultEntity<Object>> addStar(RequestBody body);
        Observable<TMBaseResoultEntity<Object>> checkIsStar(RequestBody body);
        Observable<TMBaseResoultEntity<Object>> deleteStar(RequestBody body);
        Observable<TMBaseResoultEntity<Object>> isRemind(RequestBody body);
        Observable<TMBaseResoultEntity<Object>> remind(RequestBody body);

        Observable<TMBaseResoultEntity<Object>> getMyMessage(HashMap<String, Object> body);
        Observable<TMBaseResoultEntity<Object>> getMyPoint(HashMap<String, Object> body);
        Observable<TMBaseResoultEntity<Object>> getSignList(HashMap<String, Object> body);
        Observable<TMBaseResoultEntity<Object>> getGoodsList(HashMap<String, Object> body);
        Observable<TMBaseResoultEntity<Object>> Sign(HashMap<String, Object> body);
        Observable<TMBaseResoultEntity<Object>> GoodsDetail(HashMap<String, Object> body);
        Observable<TMBaseResoultEntity<Object>> oneTheme(HashMap<String, Object> body);
        Observable<TMBaseResoultEntity<Object>> isSub(HashMap<String, Object> body);
        Observable<TMBaseResoultEntity<Object>> addSubscription(RequestBody body);


    }
}
