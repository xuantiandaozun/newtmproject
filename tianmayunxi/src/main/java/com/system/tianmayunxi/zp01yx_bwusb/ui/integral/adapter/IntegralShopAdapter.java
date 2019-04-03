package com.system.tianmayunxi.zp01yx_bwusb.ui.integral.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.system.myproject.utils.UEMethod;
import com.system.tianmayunxi.zp01yx_bwusb.BuildConfig;
import com.system.tianmayunxi.zp01yx_bwusb.R;
import com.system.tianmayunxi.zp01yx_bwusb.ui.officialrecommend.bean.GoodsListBean;
import com.tenma.ventures.bean.utils.TMSharedPUtil;

import java.util.List;

public class IntegralShopAdapter extends BaseQuickAdapter<GoodsListBean.ListBean, BaseViewHolder> {
    public IntegralShopAdapter(@Nullable List<GoodsListBean.ListBean> data) {
        super(R.layout.fragment_integralshop_item_zp01yx_bwusb, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsListBean.ListBean item) {
        SimpleDraweeView iv=helper.getView(R.id.iv);
        iv.setImageURI(TMSharedPUtil.getTMBaseConfig(mContext).getDomain()+item.getImage());

        RoundingParams roundingParams = new RoundingParams();
        roundingParams.setCornersRadii(UEMethod.dp2px(mContext,10), UEMethod.dp2px(mContext,10), 0, 0);
        GenericDraweeHierarchyBuilder builder = new GenericDraweeHierarchyBuilder(mContext.getResources());
        GenericDraweeHierarchy hierarchy = builder.build();
        hierarchy.setRoundingParams(roundingParams);
        iv.setHierarchy(hierarchy);

        if(!TextUtils.isEmpty(item.getGoods_name())){
            helper.setText(R.id.tv_name,item.getGoods_name());
        }
        if(!TextUtils.isEmpty(item.getScore()+"")){
            helper.setText(R.id.tv_price,"￥"+item.getScore()+"");
        }

    }

}
