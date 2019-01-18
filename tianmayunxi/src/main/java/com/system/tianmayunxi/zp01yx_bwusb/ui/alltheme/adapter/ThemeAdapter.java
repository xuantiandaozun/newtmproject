package com.system.tianmayunxi.zp01yx_bwusb.ui.alltheme.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.aries.ui.view.radius.RadiusTextView;
import com.aries.ui.view.radius.delegate.RadiusTextViewDelegate;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.system.tianmayunxi.zp01yx_bwusb.BuildConfig;
import com.system.tianmayunxi.zp01yx_bwusb.R;
import com.system.tianmayunxi.zp01yx_bwusb.ui.alltheme.bean.AllThemBean;
import com.tenma.ventures.bean.utils.TMSharedPUtil;

import java.util.List;

public class ThemeAdapter extends BaseQuickAdapter<AllThemBean.ListBean, BaseViewHolder> {
    public ThemeAdapter(@Nullable List<AllThemBean.ListBean> data) {
        super(R.layout.fragment_theme_item_zp01yx_bwusb, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AllThemBean.ListBean item) {
        SimpleDraweeView simpleDraweeView=helper.getView(R.id.iv_head);
        simpleDraweeView.setImageURI(TMSharedPUtil.getTMBaseConfig(mContext).getDomain()+item.getImage());

        if(!TextUtils.isEmpty(item.getTitle())){
            helper.setText(R.id.tv_title,item.getTitle());
        }
        RadiusTextView statue=helper.getView(R.id.tv_statue);
        statue.setSelected(false);
        RadiusTextViewDelegate delegate = statue.getDelegate();
        helper.addOnClickListener(R.id.tv_statue);
        if(item.isIs_sub()){
            statue.setText("已订阅");

            delegate.setLeftSelectedDrawable(null);
            delegate.setLeftDrawable(null);
        }else {
            statue.setText("订阅");
            delegate.setLeftDrawable(R.mipmap.icon_dy1);
        }

    }

}
