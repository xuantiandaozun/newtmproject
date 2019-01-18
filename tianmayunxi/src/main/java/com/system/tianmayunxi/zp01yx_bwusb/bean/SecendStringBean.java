package com.system.tianmayunxi.zp01yx_bwusb.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by 18081 on 2018/5/27.
 */

public class SecendStringBean implements MultiItemEntity {
    private String Name;
    private String UpName;
    private Object value;

    public String getUpName() {
        return UpName;
    }

    public void setUpName(String upName) {
        UpName = upName;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public int getItemType() {
        return 1;
    }
}
