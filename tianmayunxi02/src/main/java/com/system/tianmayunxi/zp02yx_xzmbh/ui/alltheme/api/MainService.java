package com.system.tianmayunxi.zp02yx_xzmbh.ui.alltheme.api;

import com.system.tianmayunxi.zp02yx_xzmbh.api.base.BaseApi;
import com.system.tianmayunxi.zp02yx_xzmbh.ui.alltheme.api.service.AllThemeService;

public class MainService {
    private static AllThemeService service;

    public static AllThemeService getService() {
        service = BaseApi.retrofit().create(AllThemeService.class);
        return service;
    }
}
