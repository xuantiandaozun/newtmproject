package com.system.tmhsdl.zp01hxdl_vjflt.ui.dzs.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.system.tmhsdl.zp01hxdl_vjflt.R;
import com.system.tmhsdl.zp01hxdl_vjflt.ui.dzs.bean.InssBean;
import com.tenma.ventures.bean.utils.TMSharedPUtil;


import java.util.List;

public class DYAdapter extends BaseQuickAdapter<InssBean.ListBean, BaseViewHolder> {
    public DYAdapter(@Nullable List<InssBean.ListBean> data) {
        super(R.layout.hxdl_fragment_dy_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, InssBean.ListBean item) {
        ImageView iv=helper.getView(R.id.iv);
        Glide.with(mContext).load(item.getImage()).into(iv);
        if(!TextUtils.isEmpty(item.getTitle())){
            helper.setText(R.id.tv_name,item.getTitle());
        }
        if(!TextUtils.isEmpty(item.getIssn())){
            helper.setText(R.id.tv_number,item.getIssn());
        }
    }

}
