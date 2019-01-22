package com.system.myproject.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by 18081 on 2018/5/26.
 */

public class ToastUtil {
    public static final int TYPE_PROGRESS_BAR=0;


    public static void showSnack(Context context, String content) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();


    }

    public static void showSnack(Context context, String content, int type) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }

    public static void showSnack(Context context, String content, int type, int dourtion) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }

    public static void toast(Context context, String value) {
        Toast.makeText(context, value, Toast.LENGTH_SHORT).show();
    }

    public static void cancel() {
    }
}
