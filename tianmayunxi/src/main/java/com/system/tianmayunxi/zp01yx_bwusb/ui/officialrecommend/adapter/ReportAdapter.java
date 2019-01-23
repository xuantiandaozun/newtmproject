package com.system.tianmayunxi.zp01yx_bwusb.ui.officialrecommend.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.system.tianmayunxi.zp01yx_bwusb.R;
import com.system.tianmayunxi.zp01yx_bwusb.ui.officialrecommend.bean.ReportBean;
import com.system.tianmayunxi.zp01yx_bwusb.views.CheckBoxSample;

import java.util.List;

public class ReportAdapter extends BaseQuickAdapter<ReportBean, BaseViewHolder> {
    public ReportAdapter(@Nullable List<ReportBean> data) {
        super(R.layout.fragment_report_item_zp01yx_bwusb, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ReportBean item) {
        helper.setText(R.id.tv_content,item.getContent());

        CheckBoxSample sample=helper.getView(R.id.check);
        sample.setChecked(item.isCheck());
    }

}
