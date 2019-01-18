package com.system.tianmayunxi.zp01yx_bwusb.ui.officialrecommend.api;

import com.system.tianmayunxi.zp01yx_bwusb.api.base.BaseApi;
import com.system.tianmayunxi.zp01yx_bwusb.ui.officialrecommend.api.service.OfficialService;

public class MainService {
    private static OfficialService service;

    public static OfficialService getService() {
        service = BaseApi.retrofit().create(OfficialService.class);
        return service;
    }
}
