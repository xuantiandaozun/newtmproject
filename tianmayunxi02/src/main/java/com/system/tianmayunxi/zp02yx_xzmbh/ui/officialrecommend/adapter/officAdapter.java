package com.system.tianmayunxi.zp02yx_xzmbh.ui.officialrecommend.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.system.tianmayunxi.zp02yx_xzmbh.R;
import com.system.tianmayunxi.zp02yx_xzmbh.ui.officialrecommend.bean.CommonSeeBean;
import com.system.tianmayunxi.zp02yx_xzmbh.ui.officialrecommend.bean.TieZiBean;
import com.tenma.ventures.bean.utils.TMSharedPUtil;

import java.util.List;

public class officAdapter  extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    public static final int DATA_TYPE1=0;
    public static final int DATA_TYPE2=1;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public officAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(DATA_TYPE1, R.layout.layout_commensee_zp01yx_bwusb2);
        addItemType(DATA_TYPE2, R.layout.layout_tiezi_item_zp01yx_bwusb2);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {
        int itemType = item.getItemType();
        switch (itemType){
            case DATA_TYPE1:
                CommonSeeBean commonSeeBean = (CommonSeeBean) item;
                RecyclerView mlist=helper.getView(R.id.mlist);
                LinearLayoutManager layout = new LinearLayoutManager(mContext);
                layout.setOrientation(LinearLayout.HORIZONTAL);
                mlist.setLayoutManager(layout);
                helper.addOnClickListener(R.id.tv_fb);
                mlist.setAdapter(new CommonIvAdapter(commonSeeBean.getList()));
                break;
            case DATA_TYPE2:
                TieZiBean tieZiBean = (TieZiBean) item;

                RecyclerView mgrid=helper.getView(R.id.mlist);
                mgrid.setLayoutManager(new GridLayoutManager(mContext,3));
                TieIvAdapter adapter = new TieIvAdapter(tieZiBean.getImage());
                adapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        OnItemChildClickListener onItemChildClickListener = getOnItemChildClickListener();
                        int layoutPosition = helper.getLayoutPosition();
                        onItemChildClickListener.onItemChildClick(officAdapter.this,view,layoutPosition);
                    }
                });
                mgrid.setAdapter(adapter);
                SimpleDraweeView iv_head=helper.getView(R.id.iv_head);
                helper.addOnClickListener(R.id.mlist);
                helper.addOnClickListener(R.id.tv_up);
                helper.addOnClickListener(R.id.tv_share);

                iv_head.setImageURI(TMSharedPUtil.getTMBaseConfig(mContext).getDomain()+tieZiBean.getTheme_image());
                SimpleDraweeView user_head=helper.getView(R.id.user_head);
                String head_pic = tieZiBean.getHead_pic();
                if(!TextUtils.isEmpty(head_pic)){
                    if(!head_pic.contains("http")){
                        head_pic=TMSharedPUtil.getTMBaseConfig(mContext).getDomain()+ head_pic;
                    }
                }
                user_head.setImageURI(head_pic);

                if(!TextUtils.isEmpty(tieZiBean.getTheme_title())){
                    helper.setText(R.id.tv_theme,"#"+tieZiBean.getTheme_title()+"#");
                }
                if(!TextUtils.isEmpty(tieZiBean.getTitle())){
                    helper.setText(R.id.tv_title,tieZiBean.getTitle());
                }
                if(!TextUtils.isEmpty(tieZiBean.getCreate_at())){
                    helper.setText(R.id.tv_time,tieZiBean.getCreate_at());
                }
                if(!TextUtils.isEmpty(tieZiBean.getContent())){
                    helper.setText(R.id.tv_content,tieZiBean.getContent());
                }
                if(!TextUtils.isEmpty(tieZiBean.getMember_nickname())){
                    helper.setText(R.id.tv_username,tieZiBean.getMember_nickname());
                }
                if(!TextUtils.isEmpty(tieZiBean.getLike_num()+"")){
                    helper.setText(R.id.tv_up,"("+tieZiBean.getLike_num()+")");
                }
                if(!TextUtils.isEmpty(tieZiBean.getMsg_num()+"")){
                    helper.setText(R.id.tv_comments,"("+tieZiBean.getMsg_num()+")");
                }
                break;
        }
    }
}
