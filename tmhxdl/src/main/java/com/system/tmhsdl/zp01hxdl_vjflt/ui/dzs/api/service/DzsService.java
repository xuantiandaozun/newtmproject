package com.system.tmhsdl.zp01hxdl_vjflt.ui.dzs.api.service;

import com.system.tmhsdl.zp01hxdl_vjflt.bean.TMBaseResoultEntity;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface DzsService {
    /**
     *期刊列表
     * @return
     */
    @GET("zp01hxdl_vjflt/Issn/index")
    Observable<TMBaseResoultEntity<Object>> getInssnList(@QueryMap HashMap<String,Object> body);
    /**
     *电子书列表
     * @return
     */
    @GET("zp01hxdl_vjflt/Book/index")
    Observable<TMBaseResoultEntity<Object>> getBookList(@QueryMap HashMap<String,Object> body);
    /**
     *期刊详情
     * @return
     */
    @GET("zp01hxdl_vjflt/Issn/detail")
    Observable<TMBaseResoultEntity<Object>> getIssnDetail(@QueryMap HashMap<String,Object> body);
    /**
     *期刊详情
     * @return
     */
    @GET("zp01hxdl_vjflt/Issn/detail2")
    Observable<TMBaseResoultEntity<Object>> getIssnDetail2(@QueryMap HashMap<String,Object> body);
    /**
     *电子书详情
     * @return
     */
    @GET("zp01hxdl_vjflt/Book/detail")
    Observable<TMBaseResoultEntity<Object>> getBookDetail(@QueryMap HashMap<String,Object> body);
    /**
     *电子书详情
     * @return
     */
    @GET("zp01hxdl_vjflt/Book/detail2")
    Observable<TMBaseResoultEntity<Object>> getBookDetail2(@QueryMap HashMap<String,Object> body);
    /**
     *推荐期刊
     * @return
     */
    @GET("zp01hxdl_vjflt/issn/recommend")
    Observable<TMBaseResoultEntity<Object>> getRecommendList(@QueryMap HashMap<String,Object> body);
    /**
     *订阅
     * @return
     */
    @GET("zp01hxdl_vjflt/api/index")
    Observable<TMBaseResoultEntity<Object>> getApiIndex(@QueryMap HashMap<String,Object> body);
    /**
     *支付签名
     * @return
     */
    @POST("zp01hxdl_vjflt/api/pay")
    Observable<TMBaseResoultEntity<Object>> getPay(@Body RequestBody body);
    /**
     *期刊图书展示列表
     * @return
     */
    @GET("zp01hxdl_vjflt/api/content_list")
    Observable<TMBaseResoultEntity<Object>> getContentList(@QueryMap HashMap<String,Object> body);
    /**
     *内容接口
     * @return
     */
    @GET("zp01hxdl_vjflt/api/content")
    Observable<TMBaseResoultEntity<Object>> getContent(@QueryMap HashMap<String,Object> body);

}
