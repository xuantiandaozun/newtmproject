package com.system.tianmayunxi.zp01yx_bwusb.ui.alltheme.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.system.tianmayunxi.zp01yx_bwusb.R;
import com.system.tianmayunxi.zp01yx_bwusb.ui.officialrecommend.bean.CommonSeeBean;

import java.util.List;

public class ThemeListAdapter  extends BaseQuickAdapter<CommonSeeBean.ListBean, BaseViewHolder> {
    public ThemeListAdapter(@Nullable List<CommonSeeBean.ListBean> data) {
        super(R.layout.fragment_themelist_item_zp01yx_bwusb, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommonSeeBean.ListBean item) {
        if(!TextUtils.isEmpty(item.getTitle())){
            helper.setText(R.id.tv_title,item.getTitle());
        }
    }
}

