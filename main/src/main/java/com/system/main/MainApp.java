package com.system.main;

import com.google.gson.Gson;
import com.tenma.ventures.bean.TMBaseConfig;
import com.tenma.ventures.bean.utils.TMSharedPUtil;
import com.tenma.ventures.config.TMServerConfig;
import com.tenma.ventures.log.TMLog;
import com.tenma.ventures.share.app.TMShareApp;

import java.io.IOException;
import java.io.InputStream;

public class MainApp extends TMShareApp {
    @Override
    protected void onPreCreate() {
        super.onPreCreate();
        TMLog.setDebug(true);
        initBaseConfig();
    }

    /**
     * 读取BaseConfig并初始化
     */
    private void initBaseConfig() {
        try {
            InputStream is = getAssets().open("TMBaseConfig.geojson");
            int size = is.available();
            // Read the entire asset into a local byte buffer.
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            // Convert the buffer into a string.
            String baseConfig = new String(buffer, "utf-8");
            // Finally stick the string into the text view.
            Gson gson = new Gson();
            TMBaseConfig tmBaseConfig = gson.fromJson(baseConfig, TMBaseConfig.class);
            TMSharedPUtil.saveTMBaseConfig(this, baseConfig);
            TMServerConfig.BASE_URL = tmBaseConfig.getDomain();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();


    }
}
