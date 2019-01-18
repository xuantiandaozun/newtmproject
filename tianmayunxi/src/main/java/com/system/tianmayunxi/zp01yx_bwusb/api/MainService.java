package com.system.tianmayunxi.zp01yx_bwusb.api;


import com.system.tianmayunxi.zp01yx_bwusb.api.base.BaseApi;

public class MainService {
    private static Service mainService;

    public static Service getMainService() {
        mainService = BaseApi.retrofit().create(Service.class);
        return mainService;
    }
}
