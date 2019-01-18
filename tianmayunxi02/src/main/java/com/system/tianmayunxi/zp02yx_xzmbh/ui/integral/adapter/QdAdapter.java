package com.system.tianmayunxi.zp02yx_xzmbh.ui.integral.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.system.tianmayunxi.zp02yx_xzmbh.R;
import com.system.tianmayunxi.zp02yx_xzmbh.ui.officialrecommend.bean.ListSignBean;

import java.util.List;

public class QdAdapter extends BaseQuickAdapter<ListSignBean.ListBean, BaseViewHolder> {
    public QdAdapter(@Nullable List<ListSignBean.ListBean> data) {
        super(R.layout.fragment_qd_item_zp01yx_bwusb2, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ListSignBean.ListBean item) {
        TextView tv_point=helper.getView(R.id.tv_point);
        if(!TextUtils.isEmpty(item.getScore()+"")){
            helper.setText(R.id.tv_point,"+"+item.getScore()+"");
        }
        if(!TextUtils.isEmpty(item.getDay()+"")){
            helper.setText(R.id.tv_day,item.getDay()+"天");
        }
        if(item.isIs_sign()){
            tv_point.setBackgroundResource(R.drawable.yx02_btn_yun_hui);
        }else {
            tv_point.setBackgroundResource(R.drawable.yx02_btn_yun);
        }
    }

}
