package com.system.uilibrary.views.attachment.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.system.uilibrary.R;
import com.system.uilibrary.views.attachment.bean.AttachmentEntity;

/**
 * Created by zbmobi on 16/6/11.
 */
public class AttachmentAdapter extends BaseQuickAdapter<AttachmentEntity, BaseViewHolder> {
    public final static int TYPE_EDIT=0;
    public final static int TYPE_SHOW=1;

    ImageView attachmentFileIv;
    ImageView attachmentFileDeleteIv;

    public AttachmentAdapter() {
        super(R.layout.layout_attachment_grid_item_view);
    }

    @Override
    protected void convert(BaseViewHolder helper, final AttachmentEntity item) {

        if (!TextUtils.isEmpty(item.getFilePreName())) {
            String name = item.getFilePreName();
            helper.setText(R.id.attachment_file_tv, name);
        }
        attachmentFileIv = helper.getView(R.id.attachment_file_iv);
        if (!TextUtils.isEmpty(item.getFileExtension())) {
            switch (item.getFileExtension().toUpperCase()) {
                case "PNG":
                case "JPG":
                case "JPEG":
                case ".PNG":
                case ".JPG":
                case ".JPEG":
                    if (!TextUtils.isEmpty(item.getLocalPath())) {
                        //显示本地图片
                        Glide.with(mContext).load(item.getLocalPath()).into(attachmentFileIv);
                    } else {
                        if (item.getFielFullName().contains("http://")) {
                            //显示网络图片
                            Glide.with(mContext).load(item.getFielFullName()).into(attachmentFileIv);

                        } else {
                            //显示网络图片
                            Glide.with(mContext).load(item.getFilePath()).into(attachmentFileIv);

                        }
                    }

                    break;
                case "MP4":
                case ".MP4":
                    break;
                case "MP3":
                case "RAW":
                case "WAV":
                case "AMR":
                case ".MP3":
                case ".RAW":
                case ".WAV":
                case ".AMR":
                    attachmentFileIv.setImageResource(R.mipmap.ic_file_audio);
                    break;
                default:
                    attachmentFileIv.setImageResource(R.mipmap.ic_file_misc);
            }
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
