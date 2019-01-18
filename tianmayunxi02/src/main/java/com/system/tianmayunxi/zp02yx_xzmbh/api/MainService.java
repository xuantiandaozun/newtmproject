package com.system.tianmayunxi.zp02yx_xzmbh.api;


import com.system.tianmayunxi.zp02yx_xzmbh.api.base.BaseApi;

public class MainService {
    private static Service mainService;

    public static Service getMainService() {
        mainService = BaseApi.retrofit().create(Service.class);
        return mainService;
    }
}
