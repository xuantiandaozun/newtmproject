package com.system.tianmayunxi.zp02yx_xzmbh.ui.officialrecommend.api;

import com.system.tianmayunxi.zp02yx_xzmbh.api.base.BaseApi;
import com.system.tianmayunxi.zp02yx_xzmbh.ui.officialrecommend.api.service.OfficialService;

public class MainService {
    private static OfficialService service;

    public static OfficialService getService() {
        service = BaseApi.retrofit().create(OfficialService.class);
        return service;
    }
}
