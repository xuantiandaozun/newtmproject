package com.system.tianmayunxi.zp01yx_bwusb.utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.system.myproject.listener.OnRequestCallback;
import com.system.myproject.listener.ResultListener;
import com.system.tianmayunxi.zp01yx_bwusb.api.MainService;
import com.system.tianmayunxi.zp01yx_bwusb.api.Service;
import com.system.tianmayunxi.zp01yx_bwusb.api.base.BaseApi;
import com.system.tianmayunxi.zp01yx_bwusb.bean.TMBaseResoultEntity;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;

public class CommService {
    public  static CommService instance;

    public static CommService getInstance(){
        if (instance==null){
            instance=new CommService();
        }
        return instance;
    }
    public void uploadFile(List<MultipartBody.Part> partList, onCallBack listener) {
        Gson gson = new Gson();
        MainService.getMainService().uploadFile(partList)
                //在新线程中执行登录请求
                .subscribeOn(Schedulers.newThread())
                //在主线程中执行;
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new OnRequestCallback<>(new ResultListener<TMBaseResoultEntity<Object>>() {
                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onEnd() {

                    }

                    @Override
                    public void onSuccess(TMBaseResoultEntity<Object> data) {
                        if (data != null)
                            if (data.getError_code()== 200) {
                                JsonObject returnData = new JsonParser().parse(gson.toJson(data.getData())).getAsJsonObject();
                                JsonElement access_token = returnData.get("path");
                                listener.onUserBack(access_token.getAsString());
                            }
                    }

                    @Override
                    public void onFailure(String message) {

                    }
                }));
    }
    public interface onCallBack {
        void onUserBack(String data);
    }
}
