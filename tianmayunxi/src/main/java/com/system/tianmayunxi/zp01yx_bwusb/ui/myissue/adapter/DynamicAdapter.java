package com.system.tianmayunxi.zp01yx_bwusb.ui.myissue.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.system.tianmayunxi.zp01yx_bwusb.BuildConfig;
import com.system.tianmayunxi.zp01yx_bwusb.R;
import com.system.tianmayunxi.zp01yx_bwusb.ui.officialrecommend.adapter.TieIvAdapter;
import com.system.tianmayunxi.zp01yx_bwusb.ui.officialrecommend.bean.TieZiBean;
import com.tenma.ventures.bean.utils.TMSharedPUtil;

import java.util.List;

public class DynamicAdapter extends BaseQuickAdapter<TieZiBean, BaseViewHolder> {
    public DynamicAdapter(@Nullable List<TieZiBean> data) {
        super(R.layout.fragment_dynamic_item_zp01yx_bwusb, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TieZiBean item) {
        RecyclerView mgrid=helper.getView(R.id.mlist);
        mgrid.setLayoutManager(new GridLayoutManager(mContext,3));
        mgrid.setAdapter(new TieIvAdapter(item.getImage()));

        SimpleDraweeView iv_head=helper.getView(R.id.iv_head);
        helper.addOnClickListener(R.id.mlist);
        helper.addOnClickListener(R.id.tv_share);

        SimpleDraweeView user_head=helper.getView(R.id.user_head);


        iv_head.setImageURI(TMSharedPUtil.getTMBaseConfig(mContext).getDomain()+item.getTheme_image());

        String head_pic = item.getHead_pic();
        if(!head_pic.contains("http")){
            head_pic=TMSharedPUtil.getTMBaseConfig(mContext).getDomain()+ head_pic;
        }
        user_head.setImageURI(head_pic);


        if(!TextUtils.isEmpty(item.getTheme_title())){
            helper.setText(R.id.tv_theme,"#"+item.getTheme_title()+"#");
        }
        if(!TextUtils.isEmpty(item.getTitle())){
            helper.setText(R.id.tv_title,item.getTitle());
        }
        if(!TextUtils.isEmpty(item.getCreate_at())){
            helper.setText(R.id.tv_time,item.getCreate_at());
        }
        if(!TextUtils.isEmpty(item.getContent())){
            helper.setText(R.id.tv_content,item.getContent());
        }
        if(!TextUtils.isEmpty(item.getMember_nickname())){
            helper.setText(R.id.tv_username,item.getMember_nickname());
        }
        if(!TextUtils.isEmpty(item.getLike_num()+"")){
            helper.setText(R.id.tv_up,"("+item.getLike_num()+")");
        }
        if(!TextUtils.isEmpty(item.getMsg_num()+"")){
            helper.setText(R.id.tv_comments,"("+item.getMsg_num()+")");
        }
    }

}
