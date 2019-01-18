package com.system.tianmayunxi.zp01yx_bwusb.ui.alltheme.api.service;

import com.system.tianmayunxi.zp01yx_bwusb.bean.TMBaseResoultEntity;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface AllThemeService {
    /**
     *全部主题
     * @return
     */
    @GET("zp01yx_bwusb/api/getAllTheme")
    Observable<TMBaseResoultEntity<Object>> getAllThemeList(@QueryMap HashMap<String,Object> body);
    /**
     *订阅主题
     * @return
     */
    @POST("zp01yx_bwusb/subscription/add_subscription")
    Observable<TMBaseResoultEntity<Object>> addSubscription(@Body RequestBody body);
    /**
     *取消订阅主题
     * @return
     */
    @POST("zp01yx_bwusb/subscription/unsubscribe")
    Observable<TMBaseResoultEntity<Object>> unSubscribe(@Body RequestBody body);

    /**
     *发布文章接口
     * @return
     */
    @POST("zp01yx_bwusb/article/pushArticle")
    Observable<TMBaseResoultEntity<Object>> pushArticle(@Body RequestBody body);
}
