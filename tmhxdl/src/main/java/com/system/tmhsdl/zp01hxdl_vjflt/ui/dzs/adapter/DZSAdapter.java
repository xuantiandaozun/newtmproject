package com.system.tmhsdl.zp01hxdl_vjflt.ui.dzs.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.system.tmhsdl.zp01hxdl_vjflt.R;
import com.system.tmhsdl.zp01hxdl_vjflt.ui.dzs.bean.BookBean;
import com.tenma.ventures.bean.utils.TMSharedPUtil;


import java.util.List;

public class DZSAdapter extends BaseQuickAdapter<BookBean.ListBean, BaseViewHolder> {
    public DZSAdapter(@Nullable List<BookBean.ListBean> data) {
        super(R.layout.hxdl_fragment_dzs_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BookBean.ListBean item) {
        ImageView iv=helper.getView(R.id.iv);
        RelativeLayout re_back=helper.getView(R.id.re_back);

        Glide.with(mContext).load(item.getImage()).into(iv);
        if(!TextUtils.isEmpty(item.getTitle())){
            helper.setText(R.id.tv_name,item.getTitle());
        }
        int themeColor = Color.parseColor(TMSharedPUtil.getTMThemeColor(mContext));
        re_back.setBackgroundColor(themeColor);

    }

}
