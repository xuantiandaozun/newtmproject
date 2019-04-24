package com.system.tianmayunxi.zp01yx_bwusb.ui.alltheme.presenter;

import com.system.myproject.base.MVPBasePresenter;
import com.system.myproject.listener.OnRequestCallback;
import com.system.myproject.listener.ResultListener;
import com.system.tianmayunxi.zp01yx_bwusb.bean.EventCallBackBean;
import com.system.tianmayunxi.zp01yx_bwusb.bean.TMBaseResoultEntity;
import com.system.tianmayunxi.zp01yx_bwusb.ui.alltheme.contract.AllThemeContract;
import com.system.tianmayunxi.zp01yx_bwusb.ui.alltheme.model.AllThemeModel;
import com.system.uilibrary.dialog.DialogsTools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class AllThemePresenter extends MVPBasePresenter<AllThemeContract.View> implements AllThemeContract.Presenter {
    private AllThemeContract.Model mModel;

    public AllThemePresenter() {
        mModel = new AllThemeModel();
    }

    @Override
    public void getAllThemeList(HashMap<String, Object> body) {
        mModel.getAllThemeList(body).subscribe(new OnRequestCallback<>(new ResultListener<TMBaseResoultEntity<Object>>() {
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
                            eventData.put("getAllThemeList", data.getData());
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
    public void addSubscription(RequestBody body) {
        mModel.addSubscription(body).subscribe(new OnRequestCallback<>(new ResultListener<TMBaseResoultEntity<Object>>() {
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
                            eventData.put("addSubscription", data.getData());
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
    public void pushArticle(RequestBody body) {
        mModel.pushArticle(body).subscribe(new OnRequestCallback<>(new ResultListener<TMBaseResoultEntity<Object>>() {
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
                            eventData.put("pushArticle", data.getData());
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
    public void unSubscribe(RequestBody body) {
        mModel.unSubscribe(body).subscribe(new OnRequestCallback<>(new ResultListener<TMBaseResoultEntity<Object>>() {
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
                            eventData.put("unSubscribe", data.getData());
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
    public Observable<TMBaseResoultEntity<Object>> uploadIv(List<MultipartBody.Part> partList) {
        Observable<TMBaseResoultEntity<Object>> observable = mModel.uploadIv(partList);
        return observable;
    }

    @Override
    public void allUpload(Observable<TMBaseResoultEntity<Object>>[] mdata) {
        List<Object> mlist=new ArrayList();
        Observable.mergeArray(mdata).subscribe(new OnRequestCallback<>(new ResultListener<TMBaseResoultEntity<Object>>() {
            @Override
            public void onStart() {
            }

            @Override
            public void onEnd() {

            }

            @Override
            public void onSuccess(TMBaseResoultEntity<Object> data) {
                DialogsTools.getInstance().closeDialog();
                if (data != null)
                    if (isViewAttached()) {
                        if (data.getError_code() == 200) {

                            EventCallBackBean bean = new EventCallBackBean();
                            bean.setEventNumber(EventCallBackBean.WHITEDATA);
                            HashMap<String, Object> eventData = bean.getEventData();
                            if(mlist.size()!=mdata.length){
                                mlist.add(data.getData());
                                if(mlist.size()==mdata.length){
                                    eventData.put("allUpload", mlist);
                                    getView().callBack(bean);
                                }
                            }else {

                            }

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
