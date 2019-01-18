package com.system.tianmayunxi.zp01yx_bwusb.ui.myissue.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.system.tianmayunxi.zp01yx_bwusb.BuildConfig;
import com.system.tianmayunxi.zp01yx_bwusb.R;
import com.system.tianmayunxi.zp01yx_bwusb.ui.officialrecommend.bean.MessageBean;
import com.tenma.ventures.bean.utils.TMSharedPUtil;

import java.util.List;

public class MessageAdapter  extends BaseQuickAdapter<MessageBean.ListBean, BaseViewHolder> {
    public MessageAdapter(@Nullable List<MessageBean.ListBean> data) {
        super(R.layout.fragment_message_item_zp01yx_bwusb, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageBean.ListBean item) {
        SimpleDraweeView iv_head=helper.getView(R.id.iv_head);
        ImageView iv_up=helper.getView(R.id.iv_up);
        SimpleDraweeView iv=helper.getView(R.id.iv);
        String head_pic = item.getHead_pic();
        if(!TextUtils.isEmpty(head_pic)){
            if(!head_pic.contains("http")){
                head_pic=TMSharedPUtil.getTMBaseConfig(mContext).getDomain()+ head_pic;
            }
        }

        iv_head.setImageURI(head_pic);
        iv.setImageURI(TMSharedPUtil.getTMBaseConfig(mContext).getDomain()+item.getImage());
        if(!TextUtils.isEmpty(item.getMember_nickname())){
            helper.setText(R.id.tv_username,item.getMember_nickname());
        }
        if(!TextUtils.isEmpty(item.getCreate_time())){
            helper.setText(R.id.tv_time,item.getCreate_time());
        }
        if(!TextUtils.isEmpty(item.getAid())){
            int type = item.getType();
            switch (type){
                case 1:
                    iv_up.setVisibility(View.VISIBLE);
                    helper.setText(R.id.tv_up,"");
                    break;
                case 2:
                    iv_up.setVisibility(View.GONE);
                    helper.setText(R.id.tv_up,"回复了你");
                    break;
                case 3:
                    iv_up.setVisibility(View.GONE);
                    helper.setText(R.id.tv_up,"签到提醒");
                    break;
            }
        }else {
            iv_up.setVisibility(View.GONE);
            helper.setText(R.id.tv_up,"文章被删除");
        }

    }

}

