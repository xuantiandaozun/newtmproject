package com.system.tianmayunxi.zp02yx_xzmbh.ui.officialrecommend.adapter;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.system.myproject.utils.GsonUtil;
import com.system.tianmayunxi.zp02yx_xzmbh.R;
import com.system.tianmayunxi.zp02yx_xzmbh.Tmyx02RouterConfig;
import com.system.tianmayunxi.zp02yx_xzmbh.ui.officialrecommend.bean.CommonSeeBean;
import com.system.tianmayunxi.zp02yx_xzmbh.ui.officialrecommend.bean.TieZiBean;
import com.tenma.ventures.bean.utils.TMSharedPUtil;

import java.util.HashMap;
import java.util.List;

public class officAdapter  extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    public static final int DATA_TYPE1=0;
    public static final int DATA_TYPE2=1;
    private Fragment fragment;

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

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
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
                CommonIvAdapter adapter1 = new CommonIvAdapter(commonSeeBean.getList());
                adapter1.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        String tmToken = TMSharedPUtil.getTMToken(mContext);

                        if(TextUtils.isEmpty(tmToken)){
                            Intent intent = new Intent(fragment.getActivity().getPackageName() + ".usercenter.login");
                            fragment.getActivity().startActivity(intent);
                            return;
                        }
                        CommonSeeBean.ListBean item1 = adapter1.getItem(position);
                        HashMap<String, String> main = new HashMap<>();
                        HashMap<String, String> param = new HashMap<>();
                        param.put("detail", GsonUtil.GsonString(item1));
                        main.put("fragment", Tmyx02RouterConfig.TMYX02_THEMEDETAIL);
                        main.put("params",new Gson().toJson(param));

                        ARouter.getInstance().build(Tmyx02RouterConfig.MAIN02_FRAGMENT)
                                .withString("params",GsonUtil.GsonString(main))
                                .withTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom)
                                .navigation();
                    }
                });
                mlist.setAdapter(adapter1);                break;
            case DATA_TYPE2:
                TieZiBean tieZiBean = (TieZiBean) item;
                RecyclerView mgrid=helper.getView(R.id.mlist);
                mgrid.setNestedScrollingEnabled(false);
                mgrid.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            OnItemClickListener onItemClickListener = getOnItemClickListener();
                            onItemClickListener.onItemClick(officAdapter.this,null,helper.getLayoutPosition());
                        }

                        return false;
                    }
                });
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

                iv_head.setImageURI(tieZiBean.getTheme_image());
                SimpleDraweeView user_head=helper.getView(R.id.user_head);
                String head_pic = tieZiBean.getHead_pic();
                if(!TextUtils.isEmpty(head_pic)){
                    if(!head_pic.contains("http")){
                        String domain = TMSharedPUtil.getTMBaseConfig(mContext).getDomain();

                        head_pic=domain+head_pic;
                    }
                    user_head.setImageURI(head_pic);
                }else {
                    user_head.setBackgroundResource(R.mipmap.default_head);
                }

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
