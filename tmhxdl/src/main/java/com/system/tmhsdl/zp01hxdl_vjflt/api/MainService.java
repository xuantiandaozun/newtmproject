package com.system.tmhsdl.zp01hxdl_vjflt.api;


import com.system.tmhsdl.zp01hxdl_vjflt.api.base.BaseApi;

public class MainService {
    private static Service mainService;

    public static Service getMainService() {
        mainService = BaseApi.retrofit().create(Service.class);
        return mainService;
    }
}
