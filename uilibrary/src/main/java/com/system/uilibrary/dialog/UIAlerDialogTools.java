package com.system.uilibrary.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.aries.ui.view.radius.RadiusEditText;
import com.aries.ui.widget.action.sheet.UIActionSheetDialog;
import com.aries.ui.widget.alert.UIAlertDialog;
import com.aries.ui.widget.progress.UIProgressDialog;
import com.system.myproject.utils.SizeUtil;
import com.system.uilibrary.R;

import java.util.List;

public class UIAlerDialogTools {
    private Context context;
    private static UIAlerDialogTools dialogUtils;
    private DialogText dialogText;

    public static UIAlerDialogTools getInstance(){
        if(dialogUtils==null){
            dialogUtils=new UIAlerDialogTools();
        }
        return dialogUtils;
    }
    public  void initContext(Context context){
        this.context =context;
    }
    public DialogText setDialogText(String title, String message, DialogInterface.OnClickListener listener) {
        dialogText = new DialogText(title, message, listener);
        return dialogText;
    }
    public DialogText setDialogText(String title) {
        dialogText = new DialogText(title);
        return dialogText;
    }
    public DialogText setDialogText(String title, String sheetbtn, List<CharSequence> items, UIActionSheetDialog.OnItemClickListener mOnItemClickListener) {
        dialogText = new DialogText(title,sheetbtn,items,mOnItemClickListener);
        return dialogText;
    }
    public DialogText setDialogText(String title, int res, int radiusResource) {
        dialogText = new DialogText(title,res,radiusResource);
        return dialogText;
    }
    public DialogText setDialog(DialogText dialogText) {
        this.dialogText = dialogText;
        return dialogText;
    }

    public void createIosDialog() {
        if(dialogText!=null){
            new UIAlertDialog.DividerIOSBuilder(context)
                    .setTitle(dialogText.getTitle())
                    .setTitleTextColorResource(dialogText.getTitleTextColor())
                    .setMessage(dialogText.getMessage())
                    .setMessageTextColorResource(dialogText.getMessageTextColor())
                    .setNegativeButton(dialogText.getLeftbtn(), dialogText.getOnAlertClick())
                    .setNegativeButtonTextColorResource(dialogText.getLeftbtnTextColor())
                    .setPositiveButton(dialogText.getRightbtn(), dialogText.getOnAlertClick())
                    .setMinHeight(SizeUtil.dp2px(160))
                    .setPositiveButtonTextColorResource(dialogText.getRightbtnTextColor())
                    .setNeutralButton(dialogText.getCenterbtn(), dialogText.getOnAlertClick())
                    .setNeutralButtonTextColorResource(dialogText.getCenterbtnTextColor())
                    .create()
                    .show();
        }
        dialogText=null;
    }
    public void createQQDialog() {
        if(dialogText!=null){
            new UIAlertDialog.DividerQQBuilder(context)
                    .setTitle(dialogText.getTitle())
                    .setTitleTextColorResource(dialogText.getTitleTextColor())
                    .setMessage(dialogText.getMessage())
                    .setMessageTextColorResource(dialogText.getMessageTextColor())
                    .setNegativeButton(dialogText.getLeftbtn(), dialogText.getOnAlertClick())
                    .setNegativeButtonTextColorResource(dialogText.getLeftbtnTextColor())
                    .setPositiveButton(dialogText.getRightbtn(), dialogText.getOnAlertClick())
                    .setPositiveButtonTextColorResource(dialogText.getRightbtnTextColor())
                    .setNeutralButton(dialogText.getCenterbtn(), dialogText.getOnAlertClick())
                    .setNeutralButtonTextColorResource(dialogText.getCenterbtnTextColor())
                    .create()
                    .show();
        }
        dialogText=null;
    }

    public void createEdittext(onDataClickListener listener) {
        if(dialogText!=null){
            final RadiusEditText editText = new RadiusEditText(context);
            ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                params.topMargin = SizeUtil.dp2px(12);
            editText.getDelegate()
                    .setTextColor(Color.GRAY)
                    .setRadius(6f)
                    .setBackgroundColor(Color.WHITE)
                    .setStrokeColor(Color.GRAY)
                    .setStrokeWidth(SizeUtil.dp2px(0.5f));
            editText.setMinHeight(SizeUtil.dp2px(40));
            editText.setGravity(Gravity.CENTER_VERTICAL);
            editText.setPadding(SizeUtil.dp2px(12), 0, SizeUtil.dp2px(12), 0);
            editText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
            editText.setHint("请输入内容");
            editText.setLayoutParams(params);

            new UIAlertDialog.DividerIOSBuilder(context)
                    .setTitle(dialogText.getTitle())
                    .setTitleTextColorResource(dialogText.getTitleTextColor())
                    .setView(editText)
                    .setNegativeButton(R.string.cancel, null)
                    .setPositiveButton(R.string.ensure, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String text = editText.getText().toString().trim();
                            if (TextUtils.isEmpty(text)) {
                                return;
                            }
                            listener.onDataItem(text);
                        }
                    })
                    .create()
                    .show();
        }
        dialogText=null;
    }
    public void createView(onItemClickListener listener, View view) {
        if(dialogText!=null){
            UIAlertDialog uiAlertDialog = new UIAlertDialog.DividerIOSBuilder(context)
                    .setView(view)
                    .setNegativeButton(R.string.cancel, null)
                  //  .setPositiveButtonTextColorResource(dialogText.getRightbtnTextColor())
                    .setPositiveButton(R.string.ensure, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            listener.onClickListener();
                        }
                    })
                    .create();
            Button button = uiAlertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
         //   button.setBackgroundResource(R.color.red);
            uiAlertDialog.show();

        }
        dialogText=null;
    }

    public void createNormalSheet() {
        if(dialogText!=null){
            new UIActionSheetDialog.ListSheetBuilder(context)
                    .addItems(dialogText.getItems())
                    .setItemsTextColorResource(dialogText.getItemTextColor())
                    .setTitle(dialogText.getTitle())
                    .setCancel(dialogText.getSheetbtn())
                    .setCancelTextColorResource(dialogText.getSheetbtnTextColor())
                    .setOnItemClickListener(dialogText.getmOnItemClickListener())
                    .create()
                    .show();
        }
        dialogText=null;
    }
    public void createIosSheet(){
        if(dialogText!=null){
            new UIActionSheetDialog.ListIOSBuilder(context)
                    .addItems(dialogText.getItems())
                    .setItemsTextColorResource(dialogText.getItemTextColor())
                    .setTitle(dialogText.getTitle())
                    .setCancel(dialogText.getSheetbtn())
                    .setCancelTextColorResource(dialogText.getSheetbtnTextColor())
                    .setOnItemClickListener(dialogText.getmOnItemClickListener())
                    .create()
                    .setAlpha(1f)
                    .show();
        }
        dialogText=null;
    }

    public UIProgressDialog createLoading() {
        UIProgressDialog uiProgressDialog = null;
        if (dialogText != null) {
             uiProgressDialog = new UIProgressDialog.NormalBuilder(context)
                    .setMessage(dialogText.getTitle())
                    .setIndeterminateDrawable(dialogText.getRes())
                    .setBackgroundRadiusResource(dialogText.getRadiusResource())
                    .create();

        }
        dialogText=null;

        return uiProgressDialog;
    }



    public interface  onDataClickListener{
        void onDataItem(String message);
    }
    public interface  onItemClickListener{
        void onClickListener();
    }

    public static class DialogText{
        private String title;
        private String message;
        private String leftbtn="确定";
        private String rightbtn="取消";
        private String centerbtn;
        private String sheetbtn;
        private List<CharSequence> items;
        private DialogInterface.OnClickListener onAlertClick;
        private UIActionSheetDialog.OnItemClickListener mOnItemClickListener;
        private int res;
        private  int RadiusResource;
        private int titleTextColor=R.color.black ;
        private int messageTextColor=R.color.black ;
        private int leftbtnTextColor=R.color.black ;
        private int rightbtnTextColor=R.color.black ;
        private int centerbtnTextColor=R.color.black ;
        private int sheetbtnTextColor=R.color.black ;
        private int itemTextColor=R.color.black ;

        public DialogText() {
        }

        public DialogText(String title, String message, String leftbtn, DialogInterface.OnClickListener onAlertClick) {
            this.title = title;
            this.message = message;
            this.leftbtn = leftbtn;
            this.onAlertClick = onAlertClick;
        }

        public DialogText(String title, String sheetbtn, List<CharSequence> items, UIActionSheetDialog.OnItemClickListener mOnItemClickListener) {
            this.title = title;
            this.sheetbtn = sheetbtn;
            this.items = items;
            this.mOnItemClickListener = mOnItemClickListener;
        }

        public DialogText(String title, int res, int radiusResource) {
            this.title = title;
            this.res = res;
            RadiusResource = radiusResource;
        }

        public int getRadiusResource() {
            return RadiusResource;
        }

        public void setRadiusResource(int radiusResource) {
            RadiusResource = radiusResource;
        }

        public int getRes() {
            return res;
        }

        public void setRes(int res) {
            this.res = res;
        }

        public String getSheetbtn() {
            return sheetbtn;
        }

        public void setSheetbtn(String sheetbtn) {
            this.sheetbtn = sheetbtn;
        }

        public int getSheetbtnTextColor() {
            return sheetbtnTextColor;
        }

        public void setSheetbtnTextColor(int sheetbtnTextColor) {
            this.sheetbtnTextColor = sheetbtnTextColor;
        }

        public int getItemTextColor() {
            return itemTextColor;
        }

        public void setItemTextColor(int itemTextColor) {
            this.itemTextColor = itemTextColor;
        }

        public List<CharSequence> getItems() {
            return items;
        }

        public void setItems(List<CharSequence> items) {
            this.items = items;
        }

        public DialogText(String title, String message, DialogInterface.OnClickListener onAlertClick) {
            this.title = title;
            this.message = message;
            this.onAlertClick = onAlertClick;
        }

        public UIActionSheetDialog.OnItemClickListener getmOnItemClickListener() {
            return mOnItemClickListener;
        }

        public void setmOnItemClickListener(UIActionSheetDialog.OnItemClickListener mOnItemClickListener) {
            this.mOnItemClickListener = mOnItemClickListener;
        }

        public DialogText(String title) {
            this.title=title;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getLeftbtn() {
            return leftbtn;
        }

        public void setLeftbtn(String leftbtn) {
            this.leftbtn = leftbtn;
        }

        public String getRightbtn() {
            return rightbtn;
        }

        public void setRightbtn(String rightbtn) {
            this.rightbtn = rightbtn;
        }

        public String getCenterbtn() {
            return centerbtn;
        }

        public void setCenterbtn(String centerbtn) {
            this.centerbtn = centerbtn;
        }

        public DialogInterface.OnClickListener getOnAlertClick() {
            return onAlertClick;
        }

        public void setOnAlertClick(DialogInterface.OnClickListener onAlertClick) {
            this.onAlertClick = onAlertClick;
        }

        public int getTitleTextColor() {
            return titleTextColor;
        }

        public void setTitleTextColor(int titleTextColor) {
            this.titleTextColor = titleTextColor;
        }

        public int getMessageTextColor() {
            return messageTextColor;
        }

        public void setMessageTextColor(int messageTextColor) {
            this.messageTextColor = messageTextColor;
        }

        public int getLeftbtnTextColor() {
            return leftbtnTextColor;
        }

        public void setLeftbtnTextColor(int leftbtnTextColor) {
            this.leftbtnTextColor = leftbtnTextColor;
        }

        public int getRightbtnTextColor() {
            return rightbtnTextColor;
        }

        public void setRightbtnTextColor(int rightbtnTextColor) {
            this.rightbtnTextColor = rightbtnTextColor;
        }

        public int getCenterbtnTextColor() {
            return centerbtnTextColor;
        }

        public void setCenterbtnTextColor(int centerbtnTextColor) {
            this.centerbtnTextColor = centerbtnTextColor;
        }


        public UIAlerDialogTools getTools() {
            return dialogUtils;
        }
    }
}
