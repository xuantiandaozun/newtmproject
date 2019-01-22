package com.system.uilibrary.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.system.uilibrary.R;

import java.util.List;

public class StringListMatchAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public StringListMatchAdapter(@Nullable List<String> data) {
        super(R.layout.string_match_list_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
      helper.setText(R.id.tv_name,item);
    }
}
