package com.system.tianmayunxi.zp02yx_xzmbh.ui.officialrecommend.api.service;

import com.system.tianmayunxi.zp02yx_xzmbh.bean.TMBaseResoultEntity;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface OfficialService {
    /**
     *首页主题或发布文章选择主题
     * @return
     */
    @GET("zp02yx_xzmbh/theme/getTheme")
    Observable<TMBaseResoultEntity<Object>> getTheme(@QueryMap HashMap<String,Object> body);
    /**
     *文章列表接口
     * @return
     */
    @GET("zp02yx_xzmbh/article/getArticleList")
    Observable<TMBaseResoultEntity<Object>> getArticleList(@QueryMap HashMap<String,Object> body);
    /**
     *未登录文章列表接口
     * @return
     */
    @GET("zp02yx_xzmbh/api2/getArticleList")
    Observable<TMBaseResoultEntity<Object>> getArticleListNoLogin(@QueryMap HashMap<String,Object> body);
    /**
     *文章详情
     * @return
     */
    @GET("zp02yx_xzmbh/article/getArticle")
    Observable<TMBaseResoultEntity<Object>> getArticle(@QueryMap HashMap<String,Object> body);
    /**
     *文章留言列表
     * @return
     */
    @GET("zp02yx_xzmbh/msg/articleMsg")
    Observable<TMBaseResoultEntity<Object>> getArticMsgList(@QueryMap HashMap<String,Object> body);
    /**
     *留言接口
     * @return
     */
    @POST("zp02yx_xzmbh/msg/pushMsg")
    Observable<TMBaseResoultEntity<Object>> pushMsg(@Body  RequestBody body);
    /**
     *文章点赞
     * @return
     */
    @POST("zp02yx_xzmbh/article/like")
    Observable<TMBaseResoultEntity<Object>> artcleLike(@Body  RequestBody body);
    /**
     *留言列表
     * @return
     */
    @GET("zp02yx_xzmbh/msg/msgInfoList")
    Observable<TMBaseResoultEntity<Object>> msgInfoList(@QueryMap HashMap<String,Object> body);
    /**
     *留言详情
     * @return
     */
    @GET("zp02yx_xzmbh/msg/msgInfo")
    Observable<TMBaseResoultEntity<Object>> msgInfoDetail(@QueryMap HashMap<String,Object> body);
    /**
     *留言点赞
     * @return
     */
    @POST("zp02yx_xzmbh/msg/msgLike")
    Observable<TMBaseResoultEntity<Object>> msgLike(@Body  RequestBody body);
    /**
     *我的文章
     * @return
     */
    @GET("zp02yx_xzmbh/article/myArticle")
    Observable<TMBaseResoultEntity<Object>> getMyArtic(@QueryMap HashMap<String,Object> body);
    /**
     *我的消息
     * @return
     */
    @GET("zp02yx_xzmbh/member/myMessage")
    Observable<TMBaseResoultEntity<Object>> getMyMessage(@QueryMap HashMap<String,Object> body);
    /**
     *获取会员当前积分
     * @return
     */
    @GET("member/Memberpoint/getPoint")
    Observable<TMBaseResoultEntity<Object>> getMyPoint(@QueryMap HashMap<String,Object> body);
    /**
     *签到列表
     * @return
     */
    @GET("zp02yx_xzmbh/member/sign_list")
    Observable<TMBaseResoultEntity<Object>> getSignList(@QueryMap HashMap<String,Object> body);
    /**
     *商品列表
     * @return
     */
    @GET("zp02yx_xzmbh/goods/goods_list")
    Observable<TMBaseResoultEntity<Object>> getGoodsList(@QueryMap HashMap<String,Object> body);

    /**
     *是否签到
     * @return
     */
    @POST("zp02yx_xzmbh/member/is_sign")
    Observable<TMBaseResoultEntity<Object>> isSign(@Body  RequestBody body);

    /**
     *签到
     * @return
     */
    @GET("zp02yx_xzmbh/member/sign")
    Observable<TMBaseResoultEntity<Object>> Sign(@QueryMap HashMap<String,Object> body);
    /**
     *单个主题
     * @return
     */
    @GET("zp02yx_xzmbh/Theme/oneTheme")
    Observable<TMBaseResoultEntity<Object>> oneTheme(@QueryMap HashMap<String,Object> body);
    /**
     *商品详情
     * @return
     */
    @GET("zp02yx_xzmbh/goods/detail")
    Observable<TMBaseResoultEntity<Object>> GoodsDetail(@QueryMap HashMap<String,Object> body);
    /**
     *兑换
     * @return
     */
    @POST("zp02yx_xzmbh/Member/exchange")
    Observable<TMBaseResoultEntity<Object>> exchangeGoods(@Body  RequestBody body);
    /**
     *举报留言
     * @return
     */
    @POST("zp02yx_xzmbh/msg/report")
    Observable<TMBaseResoultEntity<Object>> reportMsg(@Body  RequestBody body);
    /**
     *楼主删留言
     * @return
     */
    @POST("zp02yx_xzmbh/msg/delMsg")
    Observable<TMBaseResoultEntity<Object>> delMsg(@Body  RequestBody body);
    /**
     *添加收藏
     * @return
     */
    @POST("member/Memberstar/addStar")
    Observable<TMBaseResoultEntity<Object>> addStar(@Body  RequestBody body);
    /**
     *查询单个文章收藏
     * @return
     */
    @POST("member/Memberstar/checkIsStar")
    Observable<TMBaseResoultEntity<Object>> checkIsStar(@Body  RequestBody body);
    /**
     *从收藏列表中取消收藏
     * @return
     */
    @POST("member/Memberstar/deleteStar")
    Observable<TMBaseResoultEntity<Object>> deleteStar(@Body  RequestBody body);

    /**
     *是否订阅
     * @return
     */
    @GET("zp02yx_xzmbh/Subscription/is_subscription")
    Observable<TMBaseResoultEntity<Object>> isSub(@QueryMap HashMap<String,Object> body);
    /**
     *是否开启提醒
     * @return
     */
    @POST("zp02yx_xzmbh/member/is_remind")
    Observable<TMBaseResoultEntity<Object>> isRemind(@Body  RequestBody body);
    /**
     *设置提醒
     * @return
     */
    @POST("zp02yx_xzmbh/member/remind")
    Observable<TMBaseResoultEntity<Object>> remind(@Body  RequestBody body);
    /**
     *完善资料
     * @return
     */
    @POST("zp02yx_xzmbh/api/AllBindScore")
    Observable<TMBaseResoultEntity<Object>> AllBindService(@Body  RequestBody body);
    /**
     *绑定手机
     * @return
     */
    @POST("zp02yx_xzmbh/api/BindScore?type=4")
    Observable<TMBaseResoultEntity<Object>> BindScore(@Body  RequestBody body);
    /**
     *每日登录
     * @return
     */
    @POST("zp02yx_xzmbh/api/loginscore")
    Observable<TMBaseResoultEntity<Object>> loginscore(@Body  RequestBody body);
    /**
     *每日登录领取积分
     * @return
     */
    @POST("zp02yx_xzmbh/api/login_log")
    Observable<TMBaseResoultEntity<Object>> loginLog(@Body  RequestBody body);
    /**
     *签到规则
     * @return
     */
    @POST("zp02yx_xzmbh/api/get_system")
    Observable<TMBaseResoultEntity<Object>> getSignRule(@Body  RequestBody body);
}
