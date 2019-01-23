package com.system.tmhsdl.zp01hxdl_vjflt.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.system.tmhsdl.zp01hxdl_vjflt.R;
import com.system.uilibrary.views.attachment.bean.AttachmentEntity;

/**
 * 创建人： zhoudingwen
 * 创建时间：2017/12/27
 */

public class AddImagePhotoAdapter extends BaseQuickAdapter<AttachmentEntity, BaseViewHolder> {
    public final static int TYPE_EDIT=0;
    public final static int TYPE_SHOW=1;

    ImageView attachmentFileIv;
    ImageView attachmentFileDeleteIv;
    public AddImagePhotoAdapter() {
        super(R.layout.hxdl_layout_attachment_view);
    }

    @Override
    protected void convert(BaseViewHolder helper, final AttachmentEntity item) {
        attachmentFileIv = helper.getView(R.id.attachment_file_iv);
        helper.setVisible(R.id.attachment_file_tv,false);
        if (!TextUtils.isEmpty(item.getLocalPath())) {
            //显示本地图片
            Glide.with(mContext).load(item.getLocalPath()).into(attachmentFileIv);
        }
        attachmentFileDeleteIv = helper.getView(R.id.attachment_file_delete_iv);
        if(item.isCanEdit){
            attachmentFileDeleteIv.setVisibility(View.VISIBLE);
            attachmentFileDeleteIv.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            for (int i = 0; i < getData().size(); i++) {
                                AttachmentEntity value=getData().get(i);
                                if (!TextUtils.isEmpty(item.getFilePath())
                                        && !TextUtils.isEmpty(value.getFilePath())
                                        &&  value.getFilePath().equals(item.getFilePath())
                                        ) {
                                    remove(i);
                                }
                                if (!TextUtils.isEmpty(item.getLocalPath())
                                        && !TextUtils.isEmpty(value.getLocalPath())
                                        &&  value.getLocalPath().equals(item.getLocalPath())
                                        ) {
                                    remove(i);
                                }
                            }
                        }
                    }


            );
        }else{
            attachmentFileDeleteIv.setVisibility(View.GONE);
        }
    }
}
