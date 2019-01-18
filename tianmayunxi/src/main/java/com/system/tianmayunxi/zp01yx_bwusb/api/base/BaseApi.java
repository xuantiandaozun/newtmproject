package com.system.tianmayunxi.zp01yx_bwusb.api.base;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.system.myproject.BaseMainApp;
import com.system.myproject.utils.NetWorkUtils;
import com.system.myproject.utils.klog.KLog;
import com.system.tianmayunxi.zp01yx_bwusb.BuildConfig;
import com.tenma.ventures.bean.TMBaseConfig;
import com.tenma.ventures.bean.utils.TMSharedPUtil;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by zbmobi on 16/9/9.
 */
public class BaseApi {

    //读超时长，单位：毫秒
    public static final int READ_TIME_OUT = 3000*10;
    //连接时长，单位：毫秒
    public static final int CONNECT_TIME_OUT = 3000*10;

    public static Retrofit mRetrofit;


    /*************************缓存设置*********************/
   /*
   1. noCache 不使用缓存，全部走网络
    2. noStore 不使用缓存，也不存储缓存
    3. onlyIfCached 只使用缓存
    4. maxAge 设置最大失效时间，失效则不使用 需要服务器配合
    5. maxStale 设置最大失效时间，失效则不使用 需要服务器配合 感觉这两个类似 还没怎么弄清楚，清楚的同学欢迎留言
    6. minFresh 设置有效时间，依旧如上
    7. FORCE_NETWORK 只走网络
    8. FORCE_CACHE 只走缓存*/

    /**
     * 设缓存有效期为两天
     */
    private static final long CACHE_STALE_SEC = 60 * 60 * 24 * 2;
    /**
     * 查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
     * max-stale 指示客户机可以接收超出超时期间的响应消息。如果指定max-stale消息的值，那么客户机可接收超出超时期指定值之内的响应消息。
     */
    private static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
    /**
     * 查询网络的Cache-Control设置，头部Cache-Control设为max-age=0
     * (假如请求了服务器并在a时刻返回响应结果，则在max-age规定的秒数内，浏览器将不会发送对应的请求到服务器，数据由缓存直接返回)时则不会使用缓存而请求服务器
     */
    private static final String CACHE_CONTROL_AGE = "max-age=0";

    private static HttpLoggingInterceptor logInterceptor = null;
    private static Interceptor headerInterceptor = null;
    private static OkHttpClient okHttpClient = null;

    private static String mBaseUrl = "";
    private static Context mcontext;

    //构造方法私有
    public static Retrofit retrofit() {
        //if (mRetrofit == null) {
        //开启Log
        if (logInterceptor == null) {
            logInterceptor = new HttpLoggingInterceptor(
                    new HttpLoggingInterceptor.Logger() {
                        @Override
                        public void log(String message) {
                            if (TextUtils.isEmpty(message)) return;
                            if (BuildConfig.DEBUG) {
                                String s = message.substring(0, 1);
                                //如果收到想响应是json才打印
                                if ("{".equals(s) || "[".equals(s)) {
                                    KLog.json("请求数据: " + message);
                                }
                            }
                        }
                    });
            logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        }
        // 缓存
        File cacheFile = new File(mcontext.getCacheDir(), "cache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb
        //增加头部信息
        headerInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                String tmToken = TMSharedPUtil.getTMToken(mcontext);
                Request.Builder builder = chain.request().newBuilder()
                        .addHeader("Content-Type", "application/json");
                if(!TextUtils.isEmpty(tmToken)){
                    builder.addHeader("token",tmToken);
                }

                return chain.proceed(builder.build());
            }
        };
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(READ_TIME_OUT, TimeUnit.MILLISECONDS)
                    .connectTimeout(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
                    .addInterceptor(new RequestLoggingInterceptor())
                    .addInterceptor(logInterceptor) //mRewriteCacheControlInterceptor
                  //  .addInterceptor(new BaseOKHttpInterceptor())
                 //   .addNetworkInterceptor(mRewriteCacheControlInterceptor)
                    .addInterceptor(headerInterceptor)
                    .cache(cache)
                    .build();
        }
        String domain = TMSharedPUtil.getTMBaseConfig(mcontext).getDomain();
        mBaseUrl = domain;
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();
        mRetrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(mBaseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return mRetrofit;

    }


    public static void setContext(Context context) {
        mcontext=context;
    }

    public static class RequestLoggingInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (BuildConfig.DEBUG) {
                KLog.json(String.format("发送请求 %s on %s%n%s",
                        request.url(), chain.connection(), request.headers()));
            }
            return chain.proceed(request);
        }
    }
    /**
     * 云端响应头拦截器，用来配置缓存策略
     * Dangerous interceptor that rewrites the server's cache-control header.
     */
    private final static Interceptor mRewriteCacheControlInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            String cacheControl = request.cacheControl().toString();
            if (!NetWorkUtils.isNetConnected(BaseMainApp.getContext())) {
                request = request.newBuilder()
                        .cacheControl(TextUtils.isEmpty(cacheControl) ? CacheControl.FORCE_NETWORK : CacheControl.FORCE_CACHE)
                        .build();
            }
            Response originalResponse = chain.proceed(request);
            if (NetWorkUtils.isNetConnected(BaseMainApp.getContext())) {
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置

                return originalResponse.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        .build();
            } else {
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + CACHE_STALE_SEC)
                        .removeHeader("Pragma")
                        .build();
            }
        }
    };


}

