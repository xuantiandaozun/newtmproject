package com.system.tmhsdl.zp01hxdl_vjflt.ui.dzs.contract;

import com.system.myproject.base.MVPBaseView;
import com.system.tmhsdl.zp01hxdl_vjflt.bean.EventCallBackBean;
import com.system.tmhsdl.zp01hxdl_vjflt.bean.TMBaseResoultEntity;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public interface DzsContract {
    interface View extends MVPBaseView {
        void onFaild();
        void callBack(EventCallBackBean bean);
    }

    interface Presenter {
        void getInssnList(HashMap<String, Object> body);
        void getBookList(HashMap<String, Object> body);
        void getIssnDetail(HashMap<String, Object> body);
        void getBookDetail(HashMap<String, Object> body);
        void getRecommendList(HashMap<String, Object> body);
        void getApiIndex(HashMap<String, Object> body);
        void getContentList(HashMap<String, Object> body);
        void getContent(HashMap<String, Object> body);
        void getPay(RequestBody body);

    }

    interface Model {
        Observable<TMBaseResoultEntity<Object>> getInssnList(HashMap<String, Object> body);
        Observable<TMBaseResoultEntity<Object>> getBookList(HashMap<String, Object> body);
        Observable<TMBaseResoultEntity<Object>> getIssnDetail(HashMap<String, Object> body);
        Observable<TMBaseResoultEntity<Object>> getBookDetail(HashMap<String, Object> body);
        Observable<TMBaseResoultEntity<Object>> getRecommendList(HashMap<String, Object> body);
        Observable<TMBaseResoultEntity<Object>> getApiIndex(HashMap<String, Object> body);
        Observable<TMBaseResoultEntity<Object>> getContentList(HashMap<String, Object> body);
        Observable<TMBaseResoultEntity<Object>> getContent(HashMap<String, Object> body);
        Observable<TMBaseResoultEntity<Object>> getPay(RequestBody body);
    }
}
