package com.system.tianmayunxi.zp01yx_bwusb.bean;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by 18081 on 2018/5/5.
 */

public class StringBean  extends AbstractExpandableItem<SecendStringBean> implements MultiItemEntity {
    private String Name;
    private String Value;

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public int getItemType() {
        return 0;
    }

    @Override
    public int getLevel() {
        return 0;
    }
}
