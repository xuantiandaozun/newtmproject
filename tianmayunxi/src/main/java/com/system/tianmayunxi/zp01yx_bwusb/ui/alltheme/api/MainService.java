package com.system.tianmayunxi.zp01yx_bwusb.ui.alltheme.api;

import com.system.tianmayunxi.zp01yx_bwusb.api.base.BaseApi;
import com.system.tianmayunxi.zp01yx_bwusb.ui.alltheme.api.service.AllThemeService;
import com.system.tianmayunxi.zp01yx_bwusb.ui.officialrecommend.api.service.OfficialService;

public class MainService {
    private static AllThemeService service;

    public static AllThemeService getService() {
        service = BaseApi.retrofit().create(AllThemeService.class);
        return service;
    }
}
