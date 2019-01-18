package com.system.tianmayunxi.zp01yx_bwusb.views;

import android.view.View;

import com.bigkoo.convenientbanner.holder.Holder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.system.tianmayunxi.zp01yx_bwusb.R;
import com.system.tianmayunxi.zp01yx_bwusb.bean.BannerBean;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NetWorkImageHolderView extends Holder<BannerBean> {
    private SimpleDraweeView ivPost;

    public NetWorkImageHolderView(View itemView) {
        super(itemView);
    }

    @Override
    protected void initView(View itemView) {
        ivPost =itemView.findViewById(R.id.ivPost);
    }

    @Override
    public void updateUI(BannerBean data) {
        if(isNumeric(data.getImg())){
            ivPost.setImageResource(Integer.valueOf(data.getImg()));
        }else {

            String img = data.getImg();
            ivPost.setImageURI(img);
        }

    }
    private boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*$");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }
}
