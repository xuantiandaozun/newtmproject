package com.system.main;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.system.main.R;

import com.system.tianmayunxi.zp02yx_xzmbh.ui.main.MainFragment;
import com.system.tmhsdl.zp01hxdl_vjflt.ui.main.AppRootFragment;
import com.tenma.ventures.base.TMActivity;
import com.tenma.ventures.base.TMFragment;
import com.tenma.ventures.base.TMWebFragment;
import com.tenma.ventures.bean.TMBaseConfig;
import com.tenma.ventures.bean.utils.TMSharedPUtil;
import com.tenma.ventures.config.TMConstant;
import com.tenma.ventures.config.TMServerConfig;
import com.tenma.ventures.widget.ProhibitSlideViewPager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends TMActivity implements BottomNavigationBar.OnTabSelectedListener {
    public static Fragment f = null;
    private List<Fragment> fragments = new ArrayList<>();
    private int currentTabIndex;
    private String tabBgColor;
    private String tabSelTextColor;
    private String tabUnSelTextColor;

    private BottomNavigationBar mBottomNavigationBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TMBaseConfig tmBaseConfig = TMSharedPUtil.getTMBaseConfig(this);
        if (null != tmBaseConfig) {
            TMServerConfig.BASE_URL = tmBaseConfig.getDomain();
            getConfig();
        }
        init();

        setContentView(R.layout.activity_main);
        mBottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom);
        BottomNavigationItem item01 = new BottomNavigationItem(R.mipmap.ic_launcher, "云息-标准");
        item01.setInactiveIconResource(R.mipmap.ic_launcher);
        BottomNavigationItem item02 = new BottomNavigationItem(R.mipmap.ic_launcher, "云息-高级");
        item02.setInactiveIconResource(R.mipmap.ic_launcher);
        BottomNavigationItem item03 = new BottomNavigationItem(R.mipmap.ic_launcher, "云息-地理");
        item03.setInactiveIconResource(R.mipmap.ic_launcher);

        mBottomNavigationBar
                .addItem(item01)
                .addItem(item02)
                .addItem(item03)
                .setFirstSelectedPosition(0)//设置默认选择item
                .initialise();//初始化

       Fragment fragment01 = new com.system.tianmayunxi.zp01yx_bwusb.ui.main.MainFragment();
        fragments.add(fragment01);
        Fragment fragment02 = new MainFragment();
        fragments.add(fragment02);
        Fragment fragment03 = new AppRootFragment();
        fragments.add(fragment03);
        mBottomNavigationBar.setTabSelectedListener(this);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.content, fragment01);
        transaction.show(fragment01).commitAllowingStateLoss();
    }

    @SuppressWarnings("ConstantConditions")
    private void init() {

        try {
            FileInputStream is = openFileInput(TMConstant.Config.CONFIG_FILE_NAME);
            int size = is.available();
            // Read the entire asset into a local byte buffer.
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            // Convert the buffer into a string.
            String text = new String(buffer, "utf-8");
            // Finally stick the string into the text view.

            Gson gson = new Gson();
            JsonObject moduleJson = gson.fromJson(text, JsonObject.class);

            JsonObject configJson = moduleJson.getAsJsonObject("config");
            tabBgColor = "#" + configJson.get("backgroundColor").getAsString();
            tabSelTextColor = "#" + configJson.get("themeColor").getAsString();
            tabUnSelTextColor = "#" + configJson.get("contentColor").getAsString();
            String titleTextColor = "#" + configJson.get("titleTextColor").getAsString();
            String nightThemeColor = "#" + configJson.get("nightThemeColor").getAsString();

            TMSharedPUtil.saveTMTitleTextColor(this, titleTextColor);
            TMSharedPUtil.saveTMThemeColor(this, tabSelTextColor);
            TMSharedPUtil.saveTMNightThemeColor(this, nightThemeColor);


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void getConfig() {
        try {
            InputStream is = getAssets().open("TMContentConfig.json");
            int size = is.available();
            // Read the entire asset into a local byte buffer.
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            // Convert the buffer into a string.
            String baseConfig = new String(buffer, "utf-8");
            // Finally stick the string into the text view.

            File configFile = new File(getFilesDir(), TMConstant.Config.CONFIG_FILE_NAME);
            if (configFile.exists()) {
                configFile.deleteOnExit();
            }
            FileOutputStream outputStream;

            outputStream = openFileOutput(TMConstant.Config.CONFIG_FILE_NAME, Context.MODE_PRIVATE);
            outputStream.write(baseConfig.getBytes());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTabSelected(int position) {
        if (currentTabIndex != position) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.hide(fragments.get(currentTabIndex));
            if (!fragments.get(position).isAdded()) {
                transaction.add(R.id.content, fragments.get(position));
            }
            transaction.show(fragments.get(position)).commitAllowingStateLoss();
            currentTabIndex = position;
        }
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
