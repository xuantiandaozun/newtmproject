package com.system.uilibrary.popwindow;


import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.system.myproject.utils.HandlerUtil;
import com.system.uilibrary.R;
import com.system.uilibrary.adapter.StringListAdapter;
import com.system.uilibrary.adapter.StringListMatchAdapter;
import com.system.uilibrary.interfaces.OnPopWindowListener;


import java.util.Arrays;
import java.util.List;

public class PopWindowUtil {
    private static PopWindowUtil instance;
    private PopupWindow mPopupWindow;

    private PopWindowUtil() {

    }
    public static PopWindowUtil getInstance() {
        if (instance == null) {
            synchronized (PopWindowUtil.class) {
                if (instance == null) {
                    instance = new PopWindowUtil();
                }
            }
        }
        return instance;
    }
     /**
     *            activity
     * @param view
     *            传入需要显示在什么控件下
     * @param view1
     *            传入内容的view
     * @return
     */
    public PopupWindow makeWrapContentPopupWindow(View view, View view1) {
        mPopupWindow = new PopupWindow(view1,WindowManager.LayoutParams.WRAP_CONTENT,
                            WindowManager.LayoutParams.WRAP_CONTENT,true);
        // 设置PopupWindow的内容view
        mPopupWindow.setContentView(view1);
        mPopupWindow.setFocusable(true); // 设置PopupWindow可获得焦点
        mPopupWindow.setTouchable(true); // 设置PopupWindow可触摸
        mPopupWindow.setOutsideTouchable(true); // 设置非PopupWindow区域可触摸
        mPopupWindow.showAsDropDown(view);
        return mPopupWindow;
    }
    public PopupWindow makeMatchParentPopupWindow(View view, View view1, int heightPixels) {
        int[] ps = new int[2];
        view.getLocationInWindow( ps );
        int height = view.getHeight();
        mPopupWindow = new PopupWindow(view1,WindowManager.LayoutParams.MATCH_PARENT,
                heightPixels-ps[1]-height,true);
        // 设置PopupWindow的内容view
        mPopupWindow.setContentView(view1);
        mPopupWindow.setFocusable(true); // 设置PopupWindow可获得焦点
        mPopupWindow.setTouchable(true); // 设置PopupWindow可触摸
        mPopupWindow.setOutsideTouchable(true); // 设置非PopupWindow区域可触摸
        mPopupWindow.showAsDropDown(view);
        return mPopupWindow;
    }

    public PopupWindow createListPopWindow(Context context, View mView , String[] data, OnPopWindowListener listener){
        View view = LayoutInflater.from(context).inflate(R.layout.popwindow_list, null, false);
        RecyclerView mlist=view.findViewById(R.id.mlist);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        List<String> asList = Arrays.asList(data);
        mlist.setLayoutManager(layoutManager);
        StringListAdapter adapter = new StringListAdapter(asList);
        mlist.setAdapter(adapter);
        PopupWindow windowUtil = makeWrapContentPopupWindow( mView, view);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String item = (String) adapter.getItem(position);
                listener.onPopListListener(item,position);
                windowUtil.dismiss();
            }
        });
        return windowUtil;
    }
    public PopupWindow createListPopWindow(Context context, View mView ,int layout, String[] data, OnPopWindowListener listener){
        View view = LayoutInflater.from(context).inflate(layout, null, false);

        RecyclerView mlist=view.findViewById(R.id.mlist);
        LinearLayout linearLayout=view.findViewById(R.id.root);
        DividerItemDecoration decor = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
        decor.setDrawable(ContextCompat.getDrawable(context,R.drawable.recyleview_line2));
        mlist.addItemDecoration(decor);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        List<String> asList = Arrays.asList(data);
        mlist.setLayoutManager(layoutManager);
        StringListMatchAdapter adapter = new StringListMatchAdapter(asList);
        mlist.setAdapter(adapter);
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        PopupWindow windowUtil = makeMatchParentPopupWindow( mView, view,dm.heightPixels);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String item = (String) adapter.getItem(position);
             //   linearLayout.setBackgroundResource(R.color.background12);
                listener.onPopListListener(item,position);
                windowUtil.dismiss();
            }
        });
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  linearLayout.setBackgroundResource(R.color.background12);
                windowUtil.dismiss();
            }
        });
        return windowUtil;
    }


}
