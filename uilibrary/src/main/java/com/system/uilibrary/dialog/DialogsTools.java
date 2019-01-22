package com.system.uilibrary.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.system.myproject.interfaces.OnItemClickListener;
import com.system.uilibrary.R;
import com.system.uilibrary.interfaces.OnStringCallBack;
import com.system.uilibrary.interfaces.PhotoListener;
import com.system.uilibrary.views.alertview.AlertView;

public class DialogsTools {
    private Context context;
    private Dialog dialog;
    private static DialogsTools dialogUtils;
    private InputMethodManager imm;
    private   int position;

    public static DialogsTools getInstance(){
        if(dialogUtils==null){
            dialogUtils=new DialogsTools();
        }
        return dialogUtils;
    }
    public  void initContext(Context context){
        this.context =context;
        imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    /**
     * 加载框
     * @param msg
     */
    public  void createLoadingDialog(String msg) {
        LayoutInflater inflater = LayoutInflater.from(context);
        // 得到加载view
        View v = inflater.inflate(R.layout.dialog_loading, null);
        // 加载布局
        LinearLayout layout =  v.findViewById(R.id.dialog_loading_view);
        // 提示文字
        TextView tipTextView =  v.findViewById(R.id.tipTextView);
        // 设置加载信息
        tipTextView.setText(msg);
        // 创建自定义样式dialog
        Dialog loadingDialog = new Dialog(context, R.style.MyDialogStyle);
        // 是否可以按“返回键”消失
        loadingDialog.setCancelable(true);
        // 点击加载框以外的区域
        loadingDialog.setCanceledOnTouchOutside(false);
        // 设置布局
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        /**
         *将显示Dialog的方法封装在这里面
         */
        Window window = loadingDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setGravity(Gravity.CENTER);
        window.setAttributes(lp);
        window.setWindowAnimations(R.style.PopWindowAnimStyle);
        loadingDialog.show();
        dialog=loadingDialog;
    }

    /**
     *  从相册和拍照中选择图片  对话框
     * @param listener
     */
    public  void createPhotoDialog(@Nullable String titleStr, PhotoListener listener) {
        Dialog loadingDialog = new Dialog(context, R.style.MyDialogStyle);
        LayoutInflater inflater = LayoutInflater.from(context);
        // 得到加载view
        View v = inflater.inflate(R.layout.dialog_photo, null);
        // 加载布局
        RelativeLayout layout = v.findViewById(R.id.dialog_root);
        TextView title = v.findViewById(R.id.title);
        if(!TextUtils.isEmpty(titleStr)){
            title.setText(titleStr);
        }
        LinearLayout photo = v.findViewById(R.id.ll_photo);
        LinearLayout phone = v.findViewById(R.id.ll_phone);
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingDialog.dismiss();
                listener.photoListener();
            }
        });
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingDialog.dismiss();
                listener.phoneListener();
            }
        });
        // 创建自定义样式dialog

        // 是否可以按“返回键”消失
        loadingDialog.setCancelable(true);
        // 点击加载框以外的区域
        loadingDialog.setCanceledOnTouchOutside(true);
        // 设置布局
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        /**
         *将显示Dialog的方法封装在这里面
         */
        Window window = loadingDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setGravity(Gravity.CENTER);
        window.setAttributes(lp);
        window.setWindowAnimations(R.style.PopWindowAnimStyle);
        loadingDialog.show();
        dialog=loadingDialog;
    }
    /**
     *   android 信息对话框
     * @param title
     * @param message
     * @param onClickListener
     * @return
     */
    public  AlertDialog.Builder getMessageDialog(String title , String message, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        /*消息内容*/
        builder.setMessage(message);
        /*确定事件监听*/
        builder.setPositiveButton("确定", onClickListener);
        /*取消事件（可以监听或不监听（关闭窗口））*/
        builder.setNegativeButton("取消", null);
        return builder;
    }
    /**
     *   android 信息对话框
     * @param title
     * @param message
     * @param onClickListener
     * @return
     */
    public  AlertDialog.Builder createAndroidMessage(String title , String message, String btn,DialogInterface.OnClickListener onClickListener,int color) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        /*消息内容*/
        builder.setMessage(message);
        /*确定事件监听*/
        AlertDialog alertDialog = builder.setPositiveButton(btn, onClickListener).create();
        alertDialog.show();
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(color);
        /*取消事件（可以监听或不监听（关闭窗口））*/
    /*    builder.setNegativeButton("取消", null);*/
        return builder;
    }
    public void DanXuanDialog(String[] datas, int defaultid, String title, OnStringCallBack callBack){
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle(title)//设置对话框的标题
                .setSingleChoiceItems(datas, defaultid, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        position=which;
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        callBack.onStringCallBack(String.valueOf(position+1));
                        dialog.dismiss();
                    }
                }).create();
        dialog.show();
        this.dialog=dialog;
    }
    public void CreateCustomEditDialog(String title,String btn,onButtonClickListener listener){
        Dialog loadingDialog = new Dialog(context, R.style.MyDialogStyle);
        LayoutInflater inflater = LayoutInflater.from(context);
        // 得到加载view
        View v = inflater.inflate(R.layout.dialog_custom_edittext, null);
        RelativeLayout layout=v.findViewById(R.id.re_main);
        TextView tv_title=v.findViewById(R.id.ed_title);
        tv_title.setText(title);
        EditText content=v.findViewById(R.id.ed_content);
        Button button=v.findViewById(R.id.login_next);
        button.setText(btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = content.getText().toString();
                listener.onClick(message);
                dialog.dismiss();
            }
        });
        // 是否可以按“返回键”消失
        loadingDialog.setCancelable(true);
        // 点击加载框以外的区域
        loadingDialog.setCanceledOnTouchOutside(true);
        // 设置布局
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));

        Window window = loadingDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setGravity(Gravity.CENTER);
        window.setAttributes(lp);
        window.setWindowAnimations(R.style.PopWindowAnimStyle);
        loadingDialog.show();
        dialog=loadingDialog;
    }
    public void CreateCustomViewDialog(View view,onButtonClickListener listener){
        Dialog loadingDialog = new Dialog(context, R.style.MyDialogStyle);
        // 得到加载view
        RelativeLayout layout=view.findViewById(R.id.re_main);

        // 是否可以按“返回键”消失
        loadingDialog.setCancelable(true);
        // 点击加载框以外的区域
        loadingDialog.setCanceledOnTouchOutside(true);
        // 设置布局
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));

        Window window = loadingDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setGravity(Gravity.CENTER);
        window.setAttributes(lp);
        window.setWindowAnimations(R.style.PopWindowAnimStyle);
        loadingDialog.show();
        dialog=loadingDialog;
    }
    public void EditDialog(String title,OnStringCallBack callBack){
        View view =LayoutInflater.from(context).inflate(R.layout.half_dialog_view, null);
        final EditText editText = (EditText) view.findViewById(R.id.dialog_edit);
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle(title)//设置对话框的标题
                .setView(view)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String content = editText.getText().toString();
                        callBack.onStringCallBack(content);
                        dialog.dismiss();
                    }
                }).create();
        dialog.show();
    }
    public void twoEditDialog(String title,OnStringCallBack callBack){
        View view =LayoutInflater.from(context).inflate(R.layout.dialog_twoedit, null);
        final EditText word = (EditText) view.findViewById(R.id.ed_word);
        final EditText password = (EditText) view.findViewById(R.id.ed_password);
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle(title)//设置对话框的标题
                .setView(view)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String content = word.getText().toString();
                        String passwords = password.getText().toString();
                        callBack.onTwoEditPassword(content,passwords);
                        dialog.dismiss();
                    }
                }).create();
        dialog.show();
    }

    /**
     * 高仿IOS 底部列表对话框
     * @param list
     * @param listener
     */
    public void createIosList(String[] list,OnItemClickListener listener){
        new AlertView(
                null,
                null,
                "取消",
                null,
                list,
                context,
                AlertView.Style.ActionSheet,
                listener).show();
    }
    public void createIosSheetImage(String[] list,OnItemClickListener listener){
        new AlertView(
                null,
                null,
                "取消",
                null,
                list,
                context,
                AlertView.Style.ActionSheetImage,
                listener).show();
    }
    public void createIosMessage(String title,String content,String btn){
        new AlertView(title, content, null, new String[]{btn}, null, context, AlertView.Style.Alert, null).show();

    }
    public void createEditDialog(){

    }
    public void createListDialog(){

    }
    /**
     * 关闭dialog
     */
    public  void closeDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public interface onButtonClickListener{
        void onClick(String message);
    }


}
