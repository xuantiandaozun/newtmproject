package com.system.uilibrary.views.recyclerview.decoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.system.uilibrary.R;

/**
 * Created by 18081 on 2018/5/5.
 */

public class DividerItemDecoration extends RecyclerView.ItemDecoration  {
    private int divider_hight;
    public Context context;
    //默认以垂直布局绘制分割线
    private int orientation= LinearLayoutManager.VERTICAL;

    //画笔，如果不通过修改系统分割线来设置，可以通过自定义的paint绘制
    Paint paint;

    //默认分割线的宽度
    int divider_size=1;
    /*
    * 带参构造方法，可根据布局方式确定绘制特定的分割线
    * */
    public DividerItemDecoration(Context context, int orientation,int hight) {
        this.context=context;
        this.orientation=orientation;
        this.divider_hight=hight;

        //如果传入参数不对抛出异常
        if (orientation!=LinearLayoutManager.VERTICAL&&orientation!=LinearLayoutManager.HORIZONTAL){
            throw new IllegalArgumentException("参数不对哟，亲爱的");
        }

        //设置画笔
        paint=new Paint();
        paint.setAntiAlias(true);
        paint.setColor(context.getResources().getColor(R.color.line_background));
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(divider_size);

    }
    public DividerItemDecoration(Context context, int orientation,int color,int hight) {
        this.context=context;
        this.orientation=orientation;
        this.divider_hight=hight;

        //如果传入参数不对抛出异常
        if (orientation!=LinearLayoutManager.VERTICAL&&orientation!=LinearLayoutManager.HORIZONTAL){
            throw new IllegalArgumentException("参数不对哟，亲爱的");
        }

        //设置画笔
        paint=new Paint();
        paint.setAntiAlias(true);
        paint.setColor(context.getResources().getColor(color));
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(divider_size);

    }
    /*
    * 该方法实现通过canvas参数绘制分割线
    * */
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state){
        if (orientation==LinearLayoutManager.VERTICAL){
            drawVerticalDivider(c,parent);
        }else {
            drawHorizontalDivider(c,parent);
        }
    }

    /*
    * paint绘制水平方向布局的分割线
    * */
    private void drawHorizontalDivider(Canvas c, RecyclerView parent) {

        //获取item布局顶部y坐标，也就是将要绘制分割线的顶部y坐标
        final int top=parent.getPaddingTop();
        //获取item布局底部y坐标，也就是将要绘制分割线的底部y坐标
        final int bottom=parent.getMeasuredHeight()-parent.getPaddingBottom();
        //获取item数量
        final int item_num=parent.getChildCount();
        //循环绘制item分割线
        for (int i=0;i<item_num;i++){
            View child_item=parent.getChildAt(i);
            //获取到当前item的布局参数
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) parent.getLayoutParams();
            int left=child_item.getLeft()+layoutParams.getMarginEnd();
            int right=left+divider_size;

            //开始绘制
            c.drawRect(left,top,right,bottom,paint);
        }

    }

    /*
    * paint绘制垂直方向布局的分割线
    * */
    private void drawVerticalDivider(Canvas c, RecyclerView parent) {
        //计算item布局左边的x坐标没，也就是分割线的左边x坐标
        final int left=parent.getPaddingLeft();
        //计算item右边的x坐标，也就是分割线的右边x坐标
        final int right=parent.getMeasuredWidth()-parent.getPaddingRight();
        //获取item数量
        final int item_num=parent.getChildCount();

        //循环绘制item分割线
        for (int i=0;i<item_num;i++){
            View child_item=parent.getChildAt(i);

            //获取到当前item的布局参数
            RecyclerView.LayoutParams layoutParams= (RecyclerView.LayoutParams) child_item.getLayoutParams();

            //绘制分割线的起始y坐标
            int top=child_item.getBottom()+layoutParams.bottomMargin;
            //绘制分割线底部y坐标
            int bottom=top+divider_size;

            //开始绘制
            c.drawRect(left,top,right,bottom+20,paint);
        }
    }

    /*
    * 该方法与上一方法雷同，实现通过canvas参数绘制分割线，
    * 与onDraw()不同的点在于onDrawOver()是在item view绘制之后调用
    * */
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    /*
    * 设置item偏移，在item之间添加分割线，后面一个item就需要往后偏移分割线的宽度
    * */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        //根据布局方向设置item偏移值
        if (orientation==LinearLayoutManager.VERTICAL){
            outRect.set(0,0,0,divider_size+divider_hight);
        }else {
            outRect.set(0,0,divider_size,0);
        }
    }

}
