package com.system.tianmayunxi.zp02yx_xzmbh.ui.officialrecommend.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.aries.ui.view.radius.RadiusTextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.system.myproject.utils.ToastUtil;
import com.system.tianmayunxi.zp02yx_xzmbh.R;
import com.system.tianmayunxi.zp02yx_xzmbh.ui.officialrecommend.bean.ArticMsgBean;
import com.system.tianmayunxi.zp02yx_xzmbh.utils.ClipboardUtils;
import com.tenma.ventures.bean.utils.TMSharedPUtil;

import java.util.List;

public class PlAdapter extends BaseQuickAdapter<ArticMsgBean.ListBean, BaseViewHolder> {
    public PlAdapter(@Nullable List<ArticMsgBean.ListBean> data) {
        super(R.layout.fragment_pl_item_zp01yx_bwusb2, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticMsgBean.ListBean item) {
        SimpleDraweeView iv_head=helper.getView(R.id.iv_head);
        LinearLayout re_pop=helper.getView(R.id.re_pop);
        RadiusTextView btn_next=helper.getView(R.id.btn_next);
        RadiusTextView btn_copy=helper.getView(R.id.btn_copy);
        helper.addOnClickListener(R.id.btn_next);
        ImageView ly=helper.getView(R.id.ly);
        ly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int visibility = re_pop.getVisibility();
                if(visibility==View.VISIBLE){
                    ViewGroup.LayoutParams lp;
                    lp= re_pop.getLayoutParams();
                    lp.width=0;
                    re_pop.setLayoutParams(lp);
                    re_pop.setVisibility(View.INVISIBLE);

                }else {
                    ViewGroup.LayoutParams lp;
                    lp= re_pop.getLayoutParams();
                    lp.width=ViewGroup.LayoutParams.WRAP_CONTENT;
                    re_pop.setLayoutParams(lp);

                    re_pop.setVisibility(View.VISIBLE);
                }
            }
        });
        String content = item.getContent();
        btn_copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ClipboardUtils.copyText(mContext,content);
                    ToastUtil.showSnack(mContext,"复制到剪切板！");
                }catch (Exception e){

                }

            }
        });
        boolean is_landlord = item.isIs_landlord();
        if(is_landlord){
            btn_next.setText("删除");
        }else {
            btn_next.setText("举报");
        }
        helper.addOnClickListener(R.id.btn_pl);
        helper.addOnClickListener(R.id.tv_up);
        String head_pic = item.getHead_pic();
        if(!TextUtils.isEmpty(head_pic)){
            if(!head_pic.contains("http")){
                head_pic=TMSharedPUtil.getTMBaseConfig(mContext).getDomain()+ head_pic;
            }
            iv_head.setImageURI(head_pic);
        }else {
            iv_head.setBackgroundResource(R.mipmap.default_head);
        }

        iv_head.setImageURI(head_pic);
        if(!TextUtils.isEmpty(item.getMember_nickname())){
            helper.setText(R.id.tv_theme,item.getMember_nickname());
        }
        if(!TextUtils.isEmpty(item.getContent())){
            helper.setText(R.id.tv_title,item.getContent());
        }
        if(!TextUtils.isEmpty(item.getCreate_time())){
            helper.setText(R.id.tv_time,item.getCreate_time());
        }
        if(!TextUtils.isEmpty(item.getNum()+"")){
            helper.setText(R.id.btn_pl,item.getNum()+"回复");
        }
        if(!TextUtils.isEmpty(item.getLike_num()+"")){
            helper.setText(R.id.tv_up,"("+item.getLike_num()+")");
        }

    }

}

