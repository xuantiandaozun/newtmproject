package com.system.tianmayunxi.zp02yx_xzmbh.ui.alltheme.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aries.ui.view.radius.RadiusRelativeLayout;
import com.aries.ui.view.radius.RadiusTextView;
import com.aries.ui.view.radius.delegate.RadiusTextViewDelegate;
import com.aries.ui.view.radius.delegate.RadiusViewDelegate;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.system.tianmayunxi.zp02yx_xzmbh.R;
import com.system.tianmayunxi.zp02yx_xzmbh.ui.alltheme.bean.AllThemBean;
import com.tenma.ventures.bean.utils.TMSharedPUtil;

import java.util.List;

public class ThemeAdapter extends BaseQuickAdapter<AllThemBean.ListBean, BaseViewHolder> {
    public ThemeAdapter(@Nullable List<AllThemBean.ListBean> data) {
        super(R.layout.fragment_theme_item_zp01yx_bwusb2, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AllThemBean.ListBean item) {
        SimpleDraweeView simpleDraweeView=helper.getView(R.id.iv_head);
        simpleDraweeView.setImageURI(item.getImage());

        if(!TextUtils.isEmpty(item.getTitle())){
            helper.setText(R.id.tv_title,item.getTitle());
        }
        RadiusRelativeLayout statue=helper.getView(R.id.tv_statue);
        ImageView iv_add=helper.getView(R.id.iv_add);
        TextView tv_dy=helper.getView(R.id.tv_dy);
        statue.setSelected(false);
        RadiusViewDelegate delegate = statue.getDelegate();
        helper.addOnClickListener(R.id.tv_statue);
        if(item.isIs_sub()){
            tv_dy.setText("取消订阅");
            iv_add.setVisibility(View.GONE);
            delegate.setBackgroundColor(Color.parseColor("#ffffff"));
            tv_dy.setTextColor(Color.parseColor("#000000"));

        }else {
            tv_dy.setText("订阅");
            delegate.setBackgroundColor(Color.parseColor("#3D4779"));
            iv_add.setVisibility(View.VISIBLE);

            tv_dy.setTextColor(Color.parseColor("#ffffff"));


        }


    }

}
