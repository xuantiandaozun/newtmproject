package com.system.tianmayunxi.zp02yx_xzmbh.ui.alltheme.contract;

import com.system.myproject.base.MVPBaseView;
import com.system.tianmayunxi.zp02yx_xzmbh.bean.EventCallBackBean;
import com.system.tianmayunxi.zp02yx_xzmbh.bean.TMBaseResoultEntity;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public interface AllThemeContract {

    interface View extends MVPBaseView {
        void onFaild();
        void callBack(EventCallBackBean bean);
    }

    interface Presenter {
        void getAllThemeList(HashMap<String, Object> body);
        void addSubscription(RequestBody body);
        void pushArticle(RequestBody body);
        void unSubscribe(RequestBody body);
        Observable<TMBaseResoultEntity<Object>> uploadIv(List<MultipartBody.Part> partList);
        void allUpload(Observable<TMBaseResoultEntity<Object>>[] mdata);
    }

    interface Model {
        Observable<TMBaseResoultEntity<Object>> getAllThemeList(HashMap<String, Object> body);
        Observable<TMBaseResoultEntity<Object>> addSubscription(RequestBody body);
        Observable<TMBaseResoultEntity<Object>> pushArticle(RequestBody body);
        Observable<TMBaseResoultEntity<Object>> unSubscribe(RequestBody body);
        Observable<TMBaseResoultEntity<Object>> uploadIv(List<MultipartBody.Part> partList);
    }
}
