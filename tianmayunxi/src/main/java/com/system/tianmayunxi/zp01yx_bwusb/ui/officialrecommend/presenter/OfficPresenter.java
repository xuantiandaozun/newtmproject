package com.system.tianmayunxi.zp01yx_bwusb.ui.officialrecommend.presenter;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.system.myproject.base.MVPBasePresenter;
import com.system.myproject.listener.OnRequestCallback;
import com.system.myproject.listener.ResultListener;
import com.system.myproject.utils.GsonUtil;
import com.system.tianmayunxi.zp01yx_bwusb.bean.EventCallBackBean;
import com.system.tianmayunxi.zp01yx_bwusb.bean.TMBaseResoultEntity;
import com.system.tianmayunxi.zp01yx_bwusb.ui.officialrecommend.bean.ArticleBean;
import com.system.tianmayunxi.zp01yx_bwusb.ui.officialrecommend.bean.CommonSeeBean;
import com.system.tianmayunxi.zp01yx_bwusb.ui.officialrecommend.bean.TieZiBean;
import com.system.tianmayunxi.zp01yx_bwusb.ui.officialrecommend.contract.OfficContract;
import com.system.tianmayunxi.zp01yx_bwusb.ui.officialrecommend.model.OfficialModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import okhttp3.RequestBody;

public class OfficPresenter extends MVPBasePresenter<OfficContract.View> implements OfficContract.Presenter {
    private OfficContract.Model mModel;

    public OfficPresenter() {
        mModel = new OfficialModel();
    }

    @Override
    public void getTheme(HashMap<String, Object> body) {
        mModel.getTheme(body).subscribe(new OnRequestCallback<>(new ResultListener<TMBaseResoultEntity<Object>>() {
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
                            eventData.put("getTheme", data.getData());
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
    public void getArticleList(HashMap<String, Object> body) {
        mModel.getArticleList(body).subscribe(new OnRequestCallback<>(new ResultListener<TMBaseResoultEntity<Object>>() {
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
                            eventData.put("getArticleList", data.getData());
                            getView().callBack(bean);
                        } else {
                            EventCallBackBean bean = new EventCallBackBean();
                            bean.setEventNumber(EventCallBackBean.WHITEDATA);
                            HashMap<String, Object> eventData = bean.getEventData();
                            eventData.put("getArticleList", null);
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
    public void getArticle(HashMap<String, Object> body) {
        mModel.getArticle(body).subscribe(new OnRequestCallback<>(new ResultListener<TMBaseResoultEntity<Object>>() {
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
                            eventData.put("getArticle", data.getData());
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
    public void getArticMsgList(HashMap<String, Object> body) {
        mModel.getArticMsgList(body).subscribe(new OnRequestCallback<>(new ResultListener<TMBaseResoultEntity<Object>>() {
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
                            eventData.put("getArticMsgList", data.getData());
                            getView().callBack(bean);
                        } else {
                            EventCallBackBean bean = new EventCallBackBean();
                            bean.setEventNumber(EventCallBackBean.WHITEDATA);
                            HashMap<String, Object> eventData = bean.getEventData();
                            eventData.put("getArticMsgList", null);
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
    public void msgInfoList(HashMap<String, Object> body) {
        mModel.msgInfoList(body).subscribe(new OnRequestCallback<>(new ResultListener<TMBaseResoultEntity<Object>>() {
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
                            eventData.put("msgInfoList", data.getData());
                            getView().callBack(bean);
                        } else {
                            EventCallBackBean bean = new EventCallBackBean();
                            bean.setEventNumber(EventCallBackBean.WHITEDATA);
                            HashMap<String, Object> eventData = bean.getEventData();
                            eventData.put("msgInfoList", null);
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
    public void getMyArtic(HashMap<String, Object> body) {
        mModel.getMyArtic(body).subscribe(new OnRequestCallback<>(new ResultListener<TMBaseResoultEntity<Object>>() {
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
                            eventData.put("getMyArtic", data.getData());
                            getView().callBack(bean);
                        } else {
                            EventCallBackBean bean = new EventCallBackBean();
                            bean.setEventNumber(EventCallBackBean.WHITEDATA);
                            HashMap<String, Object> eventData = bean.getEventData();
                            eventData.put("getMyArtic", null);
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
    public void msgInfoDetail(HashMap<String, Object> body) {
        mModel.msgInfoDetail(body).subscribe(new OnRequestCallback<>(new ResultListener<TMBaseResoultEntity<Object>>() {
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
                            eventData.put("msgInfoDetail", data.getData());
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
    public void pushMsg(RequestBody body) {
        mModel.pushMsg(body).subscribe(new OnRequestCallback<>(new ResultListener<TMBaseResoultEntity<Object>>() {
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
                            eventData.put("pushMsg", data.getData());
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
    public void artcleLike(RequestBody body) {
        mModel.artcleLike(body).subscribe(new OnRequestCallback<>(new ResultListener<TMBaseResoultEntity<Object>>() {
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
                            eventData.put("artcleLike", data.getData());
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
    public void msgLike(RequestBody body) {
        mModel.msgLike(body).subscribe(new OnRequestCallback<>(new ResultListener<TMBaseResoultEntity<Object>>() {
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
                            eventData.put("msgLike", data.getData());
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
    public void isSign(RequestBody body) {
        mModel.isSign(body).subscribe(new OnRequestCallback<>(new ResultListener<TMBaseResoultEntity<Object>>() {
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
                            eventData.put("isSign", data.getData());
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
    public void reportMsg(RequestBody body) {
        mModel.reportMsg(body).subscribe(new OnRequestCallback<>(new ResultListener<TMBaseResoultEntity<Object>>() {
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
                            eventData.put("reportMsg", data.getData());
                            getView().callBack(bean);
                            getView().showMessage(0, data.getMessage());
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
    public void delMsg(RequestBody body) {
        mModel.delMsg(body).subscribe(new OnRequestCallback<>(new ResultListener<TMBaseResoultEntity<Object>>() {
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
                            eventData.put("delMsg", data.getData());
                            getView().callBack(bean);
                            getView().showMessage(0, data.getMessage());
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
    public void addStar(RequestBody body) {
        mModel.addStar(body).subscribe(new OnRequestCallback<>(new ResultListener<TMBaseResoultEntity<Object>>() {
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
                            eventData.put("addStar", data.getData());
                            getView().callBack(bean);
                            getView().showMessage(0, data.getMessage());
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
    public void checkIsStar(RequestBody body) {
        mModel.checkIsStar(body).subscribe(new OnRequestCallback<>(new ResultListener<TMBaseResoultEntity<Object>>() {
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
                            eventData.put("checkIsStar", data.getData());
                            getView().callBack(bean);
                            //  getView().showMessage(0, data.getMessage());
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
    public void deleteStar(RequestBody body) {
        mModel.deleteStar(body).subscribe(new OnRequestCallback<>(new ResultListener<TMBaseResoultEntity<Object>>() {
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
                            eventData.put("deleteStar", data.getData());
                            getView().callBack(bean);
                            getView().showMessage(0, data.getMessage());
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
    public void isRemind(RequestBody body) {
        mModel.isRemind(body).subscribe(new OnRequestCallback<>(new ResultListener<TMBaseResoultEntity<Object>>() {
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
                        EventCallBackBean bean = new EventCallBackBean();
                        bean.setEventNumber(EventCallBackBean.WHITEDATA);
                        HashMap<String, Object> eventData = bean.getEventData();
                        eventData.put("isRemind", data.getData());
                        getView().callBack(bean);
                       // getView().showMessage(0, data.getMessage());
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
    public void remind(RequestBody body) {
        mModel.remind(body).subscribe(new OnRequestCallback<>(new ResultListener<TMBaseResoultEntity<Object>>() {
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
                            eventData.put("remind", data.getData());
                            getView().callBack(bean);
                            getView().showMessage(0, data.getMessage());
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
    public void exchangeGoods(RequestBody body) {
        mModel.exchangeGoods(body).subscribe(new OnRequestCallback<>(new ResultListener<TMBaseResoultEntity<Object>>() {
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
                            eventData.put("exchangeGoods", data.getData());
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
    public void getMyMessage(HashMap<String, Object> body) {
        mModel.getMyMessage(body).subscribe(new OnRequestCallback<>(new ResultListener<TMBaseResoultEntity<Object>>() {
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
                            eventData.put("getMyMessage", data.getData());
                            getView().callBack(bean);
                        } else {
                            EventCallBackBean bean = new EventCallBackBean();
                            bean.setEventNumber(EventCallBackBean.WHITEDATA);
                            HashMap<String, Object> eventData = bean.getEventData();
                            eventData.put("getMyMessage", null);
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
    public void getMyPoint(HashMap<String, Object> body) {
        mModel.getMyPoint(body).subscribe(new OnRequestCallback<>(new ResultListener<TMBaseResoultEntity<Object>>() {
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
                            eventData.put("getMyPoint", data.getData());
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
    public void getSignList(HashMap<String, Object> body) {
        mModel.getSignList(body).subscribe(new OnRequestCallback<>(new ResultListener<TMBaseResoultEntity<Object>>() {
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
                            eventData.put("getSignList", data.getData());
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
    public void getGoodsList(HashMap<String, Object> body) {
        mModel.getGoodsList(body).subscribe(new OnRequestCallback<>(new ResultListener<TMBaseResoultEntity<Object>>() {
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
                            eventData.put("getGoodsList", data.getData());
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
    public void Sign(HashMap<String, Object> body) {
        mModel.Sign(body).subscribe(new OnRequestCallback<>(new ResultListener<TMBaseResoultEntity<Object>>() {
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
                            eventData.put("Sign", data.getData());
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
    public void GoodsDetail(HashMap<String, Object> body) {
        mModel.GoodsDetail(body).subscribe(new OnRequestCallback<>(new ResultListener<TMBaseResoultEntity<Object>>() {
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
                            eventData.put("GoodsDetail", data.getData());
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
    public void oneTheme(HashMap<String, Object> body) {
        mModel.oneTheme(body).subscribe(new OnRequestCallback<>(new ResultListener<TMBaseResoultEntity<Object>>() {
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
                            eventData.put("oneTheme", data.getData());
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
    public void isSub(HashMap<String, Object> body) {
        mModel.isSub(body).subscribe(new OnRequestCallback<>(new ResultListener<TMBaseResoultEntity<Object>>() {
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
                            eventData.put("isSub", data.getData());
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
    public void getArticleListNoLogin(HashMap<String, Object> body) {
        mModel.getArticleListNoLogin(body).subscribe(new OnRequestCallback<>(new ResultListener<TMBaseResoultEntity<Object>>() {
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
                            eventData.put("getArticleListNoLogin", data.getData());
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
    public void homeRefresh(HashMap<String, Object> parms, HashMap<String, Object> parms2) {
        Observable<TMBaseResoultEntity<Object>> theme = mModel.getTheme(parms);
        Observable<TMBaseResoultEntity<Object>> articleList = mModel.getArticleListNoLogin(parms2);


        Observable.zip(theme, articleList, new BiFunction<TMBaseResoultEntity<Object>, TMBaseResoultEntity<Object>, Object>() {
            @Override
            public TMBaseResoultEntity<Object> apply(TMBaseResoultEntity<Object> objectTMBaseResoultEntity, TMBaseResoultEntity<Object> objectTMBaseResoultEntity2) throws Exception {
                TMBaseResoultEntity<Object> resoultEntity = new TMBaseResoultEntity<>();
                List<MultiItemEntity> multiItemEntities = new ArrayList<>();
                if (objectTMBaseResoultEntity.getError_code() == 200) {
                    Object data = objectTMBaseResoultEntity.getData();
                    CommonSeeBean commonSeeBean = GsonUtil.GsonToBean(data, CommonSeeBean.class);
                    multiItemEntities.add(commonSeeBean);
                }
                if (objectTMBaseResoultEntity2.getError_code() == 200) {
                    Object data1 = objectTMBaseResoultEntity2.getData();
                    ArticleBean articleBean = GsonUtil.GsonToBean(data1, ArticleBean.class);
                    List<TieZiBean> list = articleBean.getList();
                    multiItemEntities.addAll(list);
                }

                resoultEntity.setError_code(200);
                resoultEntity.setData(multiItemEntities);

                return resoultEntity;
            }
        }).subscribe(new OnRequestCallback<>(new ResultListener<TMBaseResoultEntity<Object>>() {
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
                            eventData.put("homeRefresh", data.getData());
                            getView().callBack(bean);
                        } else {
                            getView().showMessage(0, data.getMessage());
                        }
                    }
            }

            @Override
            public void onFailure(String message) {
                if (isViewAttached()) {
                    getView().showMessage(0, "暂无数据");
                }
            }
        }));

    }
}
