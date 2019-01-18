package com.system.tmhsdl.zp01hxdl_vjflt.ui.dzs.api;

import com.system.tmhsdl.zp01hxdl_vjflt.api.base.BaseApi;
import com.system.tmhsdl.zp01hxdl_vjflt.ui.dzs.api.service.DzsService;

public class MainService {
    private static DzsService service;

    public static DzsService getService() {
        service = BaseApi.retrofit().create(DzsService.class);
        return service;
    }
}
