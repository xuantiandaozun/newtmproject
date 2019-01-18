package com.system.tmhsdl.zp01hxdl_vjflt.ui.dzs.presenter;

import com.system.myproject.base.MVPBasePresenter;
import com.system.myproject.listener.OnRequestCallback;
import com.system.myproject.listener.ResultListener;
import com.system.tmhsdl.zp01hxdl_vjflt.bean.EventCallBackBean;
import com.system.tmhsdl.zp01hxdl_vjflt.bean.TMBaseResoultEntity;
import com.system.tmhsdl.zp01hxdl_vjflt.ui.dzs.contract.DzsContract;
import com.system.tmhsdl.zp01hxdl_vjflt.ui.dzs.model.DzsModel;

import java.util.HashMap;

import okhttp3.RequestBody;

public class DzsPresenter extends MVPBasePresenter<DzsContract.View> implements DzsContract.Presenter {
    private DzsContract.Model mModel;

    public DzsPresenter() {
        mModel = new DzsModel();
    }
    @Override
    public void getInssnList(HashMap<String, Object> body) {
        mModel.getInssnList(body).subscribe(new OnRequestCallback<>(new ResultListener<TMBaseResoultEntity<Object>>() {
            @Override
            public void onStart() {
            }

            @Override
            public void onEnd() {

            }

            @Override
            public void onSuccess(TMBaseResoultEntity<Object> data) {
                if (data != null)
                    if (isViewAttached()) {
                        if (data.getError_code() == 200) {
                            EventCallBackBean bean = new EventCallBackBean();
                            bean.setEventNumber(EventCallBackBean.WHITEDATA);
                            HashMap<String, Object> eventData = bean.getEventData();
                            eventData.put("getInssnList", data.getData());
                            getView().callBack(bean);
                        } else {
                            EventCallBackBean bean = new EventCallBackBean();
                            bean.setEventNumber(EventCallBackBean.WHITEDATA);
                            HashMap<String, Object> eventData = bean.getEventData();
                            eventData.put("getInssnList",null);
                            getView().callBack(bean);
                            getView().showMessage(0, data.getMessage());
                        }
                    }
            }

            @Override
            public void onFailure(String message) {
                if (isViewAttached()) {
                    getView().showMessage(0, message);
                }
            }
        }));

    }

    @Override
    public void getBookList(HashMap<String, Object> body) {
        mModel.getBookList(body).subscribe(new OnRequestCallback<>(new ResultListener<TMBaseResoultEntity<Object>>() {
            @Override
            public void onStart() {
            }

            @Override
            public void onEnd() {

            }

            @Override
            public void onSuccess(TMBaseResoultEntity<Object> data) {
                if (data != null)
                    if (isViewAttached()) {
                        if (data.getError_code() == 200) {
                            EventCallBackBean bean = new EventCallBackBean();
                            bean.setEventNumber(EventCallBackBean.WHITEDATA);
                            HashMap<String, Object> eventData = bean.getEventData();
                            eventData.put("getBookList", data.getData());
                            getView().callBack(bean);
                        } else {
                            EventCallBackBean bean = new EventCallBackBean();
                            bean.setEventNumber(EventCallBackBean.WHITEDATA);
                            HashMap<String, Object> eventData = bean.getEventData();
                            eventData.put("getBookList",null);
                            getView().callBack(bean);
                            getView().showMessage(0, data.getMessage());
                        }
                    }
            }

            @Override
            public void onFailure(String message) {
                if (isViewAttached()) {
                    getView().showMessage(0, message);
                }
            }
        }));
    }

    @Override
    public void getIssnDetail(HashMap<String, Object> body) {
        mModel.getIssnDetail(body).subscribe(new OnRequestCallback<>(new ResultListener<TMBaseResoultEntity<Object>>() {
            @Override
            public void onStart() {
            }

            @Override
            public void onEnd() {

            }

            @Override
            public void onSuccess(TMBaseResoultEntity<Object> data) {
                if (data != null)
                    if (isViewAttached()) {
                        if (data.getError_code() == 200) {
                            EventCallBackBean bean = new EventCallBackBean();
                            bean.setEventNumber(EventCallBackBean.WHITEDATA);
                            HashMap<String, Object> eventData = bean.getEventData();
                            eventData.put("getIssnDetail", data.getData());
                            getView().callBack(bean);
                        } else {
                            getView().showMessage(0, data.getMessage());
                        }
                    }
            }

            @Override
            public void onFailure(String message) {
                if (isViewAttached()) {
                    getView().showMessage(0, message);
                }
            }
        }));
    }

    @Override
    public void getBookDetail(HashMap<String, Object> body) {
        mModel.getBookDetail(body).subscribe(new OnRequestCallback<>(new ResultListener<TMBaseResoultEntity<Object>>() {
            @Override
            public void onStart() {
            }

            @Override
            public void onEnd() {

            }

            @Override
            public void onSuccess(TMBaseResoultEntity<Object> data) {
                if (data != null)
                    if (isViewAttached()) {
                        if (data.getError_code() == 200) {
                            EventCallBackBean bean = new EventCallBackBean();
                            bean.setEventNumber(EventCallBackBean.WHITEDATA);
                            HashMap<String, Object> eventData = bean.getEventData();
                            eventData.put("getBookDetail", data.getData());
                            getView().callBack(bean);
                        } else {
                            getView().showMessage(0, data.getMessage());
                        }
                    }
            }

            @Override
            public void onFailure(String message) {
                if (isViewAttached()) {
                    getView().showMessage(0, message);
                }
            }
        }));
    }

    @Override
    public void getRecommendList(HashMap<String, Object> body) {
        mModel.getRecommendList(body).subscribe(new OnRequestCallback<>(new ResultListener<TMBaseResoultEntity<Object>>() {
            @Override
            public void onStart() {
            }

            @Override
            public void onEnd() {

            }

            @Override
            public void onSuccess(TMBaseResoultEntity<Object> data) {
                if (data != null)
                    if (isViewAttached()) {
                        if (data.getError_code() == 200) {
                            EventCallBackBean bean = new EventCallBackBean();
                            bean.setEventNumber(EventCallBackBean.WHITEDATA);
                            HashMap<String, Object> eventData = bean.getEventData();
                            eventData.put("getRecommendList", data.getData());
                            getView().callBack(bean);
                        } else {
                            getView().showMessage(0, data.getMessage());
                        }
                    }
            }

            @Override
            public void onFailure(String message) {
                if (isViewAttached()) {
                    getView().showMessage(0, message);
                }
            }
        }));
    }

    @Override
    public void getApiIndex(HashMap<String, Object> body) {
        mModel.getApiIndex(body).subscribe(new OnRequestCallback<>(new ResultListener<TMBaseResoultEntity<Object>>() {
            @Override
            public void onStart() {
            }

            @Override
            public void onEnd() {

            }

            @Override
            public void onSuccess(TMBaseResoultEntity<Object> data) {
                if (data != null)
                    if (isViewAttached()) {
                        if (data.getError_code() == 200) {
                            EventCallBackBean bean = new EventCallBackBean();
                            bean.setEventNumber(EventCallBackBean.WHITEDATA);
                            HashMap<String, Object> eventData = bean.getEventData();
                            eventData.put("getApiIndex", data.getData());
                            getView().callBack(bean);
                        } else {
                            getView().showMessage(0, data.getMessage());
                        }
                    }
            }

            @Override
            public void onFailure(String message) {
                if (isViewAttached()) {
                    getView().showMessage(0, message);
                }
            }
        }));
    }

    @Override
    public void getContentList(HashMap<String, Object> body) {
        mModel.getContentList(body).subscribe(new OnRequestCallback<>(new ResultListener<TMBaseResoultEntity<Object>>() {
            @Override
            public void onStart() {
            }

            @Override
            public void onEnd() {

            }

            @Override
            public void onSuccess(TMBaseResoultEntity<Object> data) {
                if (data != null)
                    if (isViewAttached()) {
                        if (data.getError_code() == 200) {
                            EventCallBackBean bean = new EventCallBackBean();
                            bean.setEventNumber(EventCallBackBean.WHITEDATA);
                            HashMap<String, Object> eventData = bean.getEventData();
                            eventData.put("getContentList", data.getData());
                            getView().callBack(bean);
                        } else {
                            getView().showMessage(0, data.getMessage());
                        }
                    }
            }

            @Override
            public void onFailure(String message) {
                if (isViewAttached()) {
                    getView().showMessage(0, message);
                }
            }
        }));
    }

    @Override
    public void getContent(HashMap<String, Object> body) {
        mModel.getContent(body).subscribe(new OnRequestCallback<>(new ResultListener<TMBaseResoultEntity<Object>>() {
            @Override
            public void onStart() {
            }

            @Override
            public void onEnd() {

            }

            @Override
            public void onSuccess(TMBaseResoultEntity<Object> data) {
                if (data != null)
                    if (isViewAttached()) {
                        if (data.getError_code() == 200) {
                            EventCallBackBean bean = new EventCallBackBean();
                            bean.setEventNumber(EventCallBackBean.WHITEDATA);
                            HashMap<String, Object> eventData = bean.getEventData();
                            eventData.put("getContent", data.getData());
                            getView().callBack(bean);
                        } else {
                            getView().showMessage(0, data.getMessage());
                        }
                    }
            }

            @Override
            public void onFailure(String message) {
                if (isViewAttached()) {
                    getView().showMessage(0, message);
                }
            }
        }));
    }

    @Override
    public void getPay(RequestBody body) {
        mModel.getPay(body).subscribe(new OnRequestCallback<>(new ResultListener<TMBaseResoultEntity<Object>>() {
            @Override
            public void onStart() {
            }

            @Override
            public void onEnd() {

            }

            @Override
            public void onSuccess(TMBaseResoultEntity<Object> data) {
                if (data != null)
                    if (isViewAttached()) {
                        if (data.getError_code() == 200) {
                            EventCallBackBean bean = new EventCallBackBean();
                            bean.setEventNumber(EventCallBackBean.WHITEDATA);
                            HashMap<String, Object> eventData = bean.getEventData();
                            eventData.put("getPay", data.getData());
                            getView().callBack(bean);
                        } else {
                            getView().showMessage(0, data.getMessage());
                        }
                    }
            }

            @Override
            public void onFailure(String message) {
                if (isViewAttached()) {
                    getView().showMessage(0, message);
                }
            }
        }));
    }
}
