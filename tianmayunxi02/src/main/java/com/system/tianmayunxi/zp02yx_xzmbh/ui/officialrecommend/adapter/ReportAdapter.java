package com.system.tianmayunxi.zp02yx_xzmbh.ui.officialrecommend.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.system.tianmayunxi.zp02yx_xzmbh.R;
import com.system.tianmayunxi.zp02yx_xzmbh.ui.officialrecommend.bean.ReportBean;
import com.system.uilibrary.views.CheckBoxSample;

import java.util.List;

public class ReportAdapter extends BaseQuickAdapter<ReportBean, BaseViewHolder> {
    public ReportAdapter(@Nullable List<ReportBean> data) {
        super(R.layout.fragment_report_item_zp01yx_bwusb2, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ReportBean item) {
        helper.setText(R.id.tv_content,item.getContent());

        CheckBoxSample sample=helper.getView(R.id.check);
        sample.setChecked(item.isCheck());
    }

}
