package com.system.tianmayunxi.zp02yx_xzmbh.ui.officialrecommend.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.system.tianmayunxi.zp02yx_xzmbh.R;
import com.system.tianmayunxi.zp02yx_xzmbh.ui.officialrecommend.bean.CommonSeeBean;
import com.tenma.ventures.bean.utils.TMSharedPUtil;

import java.util.List;

public class CommonIvAdapter extends BaseQuickAdapter<CommonSeeBean.ListBean, BaseViewHolder> {
    public CommonIvAdapter(@Nullable List<CommonSeeBean.ListBean> data) {
        super(R.layout.fragment_commoniv_item_zp02yx_bwusb, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommonSeeBean.ListBean item) {
        SimpleDraweeView iv=helper.getView(R.id.iv);
        if(!TextUtils.isEmpty(item.getTitle())){
            helper.setText(R.id.tv_title,item.getTitle());
        }
        String uriString =item.getImage();
        iv.setImageURI(uriString);
    }

}

