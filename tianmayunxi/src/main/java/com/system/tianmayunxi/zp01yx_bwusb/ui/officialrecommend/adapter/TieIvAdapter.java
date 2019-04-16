package com.system.tianmayunxi.zp01yx_bwusb.ui.officialrecommend.adapter;

import android.support.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.system.tianmayunxi.zp01yx_bwusb.BuildConfig;
import com.system.tianmayunxi.zp01yx_bwusb.R;
import com.tenma.ventures.bean.utils.TMSharedPUtil;

import java.util.List;

public class TieIvAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public TieIvAdapter(@Nullable List<String> data) {
        super(R.layout.fragment_tieiv_item_zp01yx_bwusb, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        SimpleDraweeView iv=helper.getView(R.id.iv);
        //iv.setImageURI(item);
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL); // 磁盘缓存策略

        Glide.with(mContext)
                .load(item) // 图片地址
                .apply(options) // 参数
                .into(iv); // 需要显示的ImageView控件
    }

}

