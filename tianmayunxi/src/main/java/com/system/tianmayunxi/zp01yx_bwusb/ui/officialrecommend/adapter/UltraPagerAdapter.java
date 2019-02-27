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

package com.system.tianmayunxi.zp01yx_bwusb.ui.officialrecommend.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.facebook.drawee.view.SimpleDraweeView;
import com.squareup.picasso.Picasso;
import com.system.tianmayunxi.zp01yx_bwusb.BuildConfig;
import com.system.tianmayunxi.zp01yx_bwusb.R;
import com.tenma.ventures.bean.utils.TMSharedPUtil;

import java.util.List;


/**
 * Created by mikeafc on 15/11/26.
 */
public class UltraPagerAdapter extends PagerAdapter {
    private final  List<String>  images;
    private boolean isMultiScr;
    private onImageViewClickListener listener;
    private Context context;

    public onImageViewClickListener getListener() {
        return listener;
    }

    public void setListener(onImageViewClickListener listener) {
        this.listener = listener;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public UltraPagerAdapter(boolean isMultiScr, List<String> images) {
        this.isMultiScr = isMultiScr;
        this.images=images;
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
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(container.getContext()).inflate(R.layout.layout_child_zp01yx_bwusb, null);
        //new LinearLayout(container.getContext());
        ImageView textView = (ImageView) linearLayout.findViewById(R.id.pager_textview);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    listener.onListener(position);
                }
            }
        });
        Picasso.with(context).load(images.get(position)).into(textView);

      //  textView.setImageURI(images.get(position));
        linearLayout.setId(R.id.item_id);

        container.addView(linearLayout);
        return linearLayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        LinearLayout view = (LinearLayout) object;
        container.removeView(view);
    }
    public interface onImageViewClickListener{
        void onListener(int position);
    }
}
