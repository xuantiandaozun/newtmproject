package com.system.uilibrary.views.attachment;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.system.uilibrary.R;
import com.system.uilibrary.views.attachment.adapter.AttachmentAdapter;
import com.system.uilibrary.views.attachment.bean.AttachmentEntity;
import com.system.uilibrary.views.attachment.listener.OnAttachmentClick;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by zbmobi on 16/6/11.
 */
public class AttachmentView extends RelativeLayout {

    private View rootView;

    private RecyclerView ervAttachment;
    private AttachmentAdapter mAdapter;


    private int editType = 0; //0=可编辑，1=不可编辑
    private Context context;

    public OnAttachmentClick onAttachmentClick;



    public AttachmentView(Context ctx) {
        super(ctx);
        context = ctx;
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        rootView = LayoutInflater.from(ctx).inflate(R.layout.layout_attachment_grid_view, null);
        ervAttachment = (RecyclerView) rootView.findViewById(R.id.attachment_file_erv);
        initView();
        addView(rootView, lp);

    }

    public AttachmentView(Context ctx, AttributeSet attrs) {
        super(ctx, attrs);
        context = ctx;

        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        rootView = LayoutInflater.from(ctx).inflate(R.layout.layout_attachment_grid_view, null);
        ervAttachment = (RecyclerView) rootView.findViewById(R.id.attachment_file_erv);

        //       res = ctx.getResources();
//
//        TypedArray a = ctx.getTheme().obtainStyledAttributes(attrs, R.styleable.attachment, 0, 0);
//
//        try {
//            isCanEdit = a.getBoolean(R.styleable.attachment_isCanEdit, true);
//
//        } finally {
//            a.recycle();
//        }

        initView();
        addView(rootView, lp);

    }


    public void initView() {
        GridLayoutManager mGrdiLayoutManager = new GridLayoutManager(getThisContext(), 3);
        ervAttachment.setLayoutManager(mGrdiLayoutManager);
        mAdapter = new AttachmentAdapter();
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                AttachmentEntity item = mAdapter.getItem(position);
                if (item != null) {
                    if(onAttachmentClick!=null){
                        onAttachmentClick.onAttachmentClick(position,item);
                    }
//
                }
            }
        });
        ervAttachment.setAdapter(mAdapter);
    }

    public Context getThisContext() {
        return context;
    }


    public View getView() {
        return rootView;
    }

    public void addNewFile(List<AttachmentEntity> item) {
        mAdapter.setNewData(item);
    }

    public void setOnAttachmentClick(OnAttachmentClick click) {
        this.onAttachmentClick = click;

    }

    public List<AttachmentEntity> getFileList() {
        List<AttachmentEntity> list = new ArrayList<>();
        for (int i = 0; i < mAdapter.getItemCount(); i++) {
            AttachmentEntity item = mAdapter.getItem(i);
            list.add(item);
        }
        return list;
    }

}
