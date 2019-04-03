package com.system.tmhsdl.zp01hxdl_vjflt.ui.dzs.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.system.tmhsdl.zp01hxdl_vjflt.R;
import com.system.tmhsdl.zp01hxdl_vjflt.ui.dzs.bean.InssBean;
import com.tenma.ventures.bean.utils.TMSharedPUtil;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DZKAdapter  extends BaseQuickAdapter<InssBean.ListBean, BaseViewHolder> {
    public DZKAdapter(@Nullable List<InssBean.ListBean> data) {
        super(R.layout.hxdl_fragment_dzk_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, InssBean.ListBean item) {
        ImageView iv=helper.getView(R.id.iv);
        RelativeLayout re_back=helper.getView(R.id.re_back);
        Glide.with(mContext).load(item.getImage()).into(iv);
        if(!TextUtils.isEmpty(item.getTitle())){
            helper.setText(R.id.tv_name,item.getTitle());
        }
        if(!TextUtils.isEmpty(item.getIssn())){
            helper.setText(R.id.tv_number,item.getIssn());
        }
        int themeColor = Color.parseColor(TMSharedPUtil.getTMThemeColor(mContext));
        re_back.setBackgroundColor(themeColor);
        int create_time = item.getCreate_time();
        if(!TextUtils.isEmpty(create_time+"")){
            String yyyyMMdd = timeStamp2Date(create_time+"", "yyyyMMdd");
            helper.setText(R.id.tv_number,yyyyMMdd);

        }
    }
    public  String timeStamp2Date(String seconds,String format) {
                 if(seconds == null || seconds.isEmpty() || seconds.equals("null")){
                       return "";
                     }
                 if(format == null || format.isEmpty()){
                       format = "yyyy-MM-dd HH:mm:ss";
                    }
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                 return sdf.format(new Date(Long.valueOf(seconds+"000")));
            }

}
