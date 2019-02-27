/*
 *
 *  MIT License
 *
 *  Copyright (c) 2017 Alibaba Group
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 *
 */

package com.system.tianmayunxi.zp02yx_xzmbh.ui.officialrecommend.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.system.myproject.utils.SizeUtil;
import com.system.tianmayunxi.zp02yx_xzmbh.R;
import com.tenma.ventures.bean.utils.TMSharedPUtil;

import java.util.List;


/**
 * Created by mikeafc on 15/11/26.
 */
public class UltraPagerAdapter extends PagerAdapter {
    private final List<String> images;
    private boolean isMultiScr;
    private onImageViewClickListener listener;
    private Context context;

    public onImageViewClickListener getListener() {
        return listener;
    }

    public void setListener(onImageViewClickListener listener) {
        this.listener = listener;
    }

    public UltraPagerAdapter(boolean isMultiScr, List<String> images) {
        this.isMultiScr = isMultiScr;
        this.images = images;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(container.getContext()).inflate(R.layout.layout_child_zp01yx_bwusb2, null);
        //new LinearLayout(container.getContext());
        SimpleDraweeView textView = (SimpleDraweeView) linearLayout.findViewById(R.id.pager_textview);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onListener(position);
                }
            }
        });
      //  Picasso.with(context).load(images.get(position)).into(textView);
        setControllerListener(textView,images.get(position),getScreenWidth(context));
        //  textView.setImageURI(images.get(position));
        linearLayout.setId(R.id.item_id);

        container.addView(linearLayout);
        return linearLayout;
    }
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
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
                float v = (float) (imageWidth * height) / (float) width;
                int i = SizeUtil.dp2px(300);
                if(v> i){
                    v=SizeUtil.dp2px(300);
                    layoutParams.width =(int) (v *((float)width/(float)height));
                }else {
                    layoutParams.width = imageWidth;

                }

                layoutParams.height = (int) v;
                simpleDraweeView.setLayoutParams(layoutParams);
            }

            @Override
            public void onIntermediateImageSet(String id, @Nullable ImageInfo imageInfo) {
            }

            @Override
            public void onFailure(String id, Throwable throwable) {
                throwable.printStackTrace();
            }
        };
        DraweeController controller = Fresco.newDraweeControllerBuilder().setControllerListener(controllerListener).setUri(Uri.parse(imagePath)).build();
        simpleDraweeView.setController(controller);
    }



    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        LinearLayout view = (LinearLayout) object;
        container.removeView(view);
    }

    public interface onImageViewClickListener {
        void onListener(int position);
    }
}
