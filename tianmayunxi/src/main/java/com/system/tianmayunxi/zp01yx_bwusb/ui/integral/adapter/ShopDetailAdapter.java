package com.system.tianmayunxi.zp01yx_bwusb.ui.integral.adapter;

import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.system.tianmayunxi.zp01yx_bwusb.R;

import java.util.List;

public class ShopDetailAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public ShopDetailAdapter(@Nullable List<String> data) {
        super(R.layout.fragment_shopdetail_item_zp01yx_bwusb, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        DisplayMetrics displayMetrics =
                mContext.getResources().getDisplayMetrics();
        int width = displayMetrics.widthPixels;


        SimpleDraweeView imageView= helper.getView(R.id.iv);
        setControllerListener(imageView,item,width);

    }
    /**
     * 通过imageWidth 的宽度，自动适应高度
     * * @param simpleDraweeView view
     * * @param imagePath  Uri
     * * @param imageWidth width
     */
    public static void setControllerListener(final SimpleDraweeView simpleDraweeView, String imagePath, final int imageWidth) {
        final ViewGroup.LayoutParams layoutParams = simpleDraweeView.getLayoutParams();
        ControllerListener controllerListener = new BaseControllerListener<ImageInfo>() {
            @Override
            public void onFinalImageSet(String id, @Nullable ImageInfo imageInfo, @Nullable Animatable anim) {
                if (imageInfo == null) {
                    return;
                }
                int height = imageInfo.getHeight();
                int width = imageInfo.getWidth();
                layoutParams.width = imageWidth;
                layoutParams.height = (int) ((float) (imageWidth * height) / (float) width);
                simpleDraweeView.setLayoutParams(layoutParams);
            }

            @Override
            public void onIntermediateImageSet(String id, @Nullable ImageInfo imageInfo) {
                Log.d("TAG", "Intermediate image received");
            }

            @Override
            public void onFailure(String id, Throwable throwable) {
                throwable.printStackTrace();
            }
        };
        DraweeController controller = Fresco.newDraweeControllerBuilder().setControllerListener(controllerListener).setUri(Uri.parse(imagePath)).build();
        simpleDraweeView.setController(controller);
    }
}

