package com.system.uilibrary.views.camera;

import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;


import com.system.myproject.base.MVPBaseFragment;
import com.system.myproject.base.MVPBasePresenter;
import com.system.uilibrary.R;
import com.system.uilibrary.views.camera.listener.ClickListener;
import com.system.uilibrary.views.camera.listener.ErrorListener;
import com.system.uilibrary.views.camera.listener.JCameraListener;
import com.system.uilibrary.views.camera.util.FileUtil;

import java.io.File;
import java.util.ArrayList;

import me.yokeyword.fragmentation.ISupportFragment;


public class CameraFragment extends MVPBaseFragment {
    private JCameraView jCameraView;

    private static CameraFragment f;

    public static CameraFragment newInstance() {
        f = new CameraFragment();

        return f;
    }


    @Override
    protected MVPBasePresenter createPresenter() {
        return null;
    }

    @Override
    public void onStart() {
        super.onStart();
      /*  //全屏显示
        if (Build.VERSION.SDK_INT >= 19) {
            View decorView = getActivity().getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        } else {
            View decorView = getActivity().getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(option);
        }*/
    }

    @Override
    public void onResume() {
        Log.i("CJT", "onResume");
        super.onResume();
        jCameraView.onResume();
    }

    @Override
    protected void init() {
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_camera;
    }

    @Override
    protected void initDatas() {
        jCameraView = (JCameraView) getView().findViewById(R.id.jcameraview);
        //设置视频保存路径
        jCameraView.setSaveVideoPath(Environment.getExternalStorageDirectory().getPath() + File.separator + "JCamera");
        jCameraView.setFeatures(JCameraView.BUTTON_STATE_BOTH);
        jCameraView.setTip("轻触拍照，长按摄像");
        jCameraView.setMediaQuality(JCameraView.MEDIA_QUALITY_MIDDLE);
        jCameraView.setErrorLisenter(new ErrorListener() {
            @Override
            public void onError() {
                //错误监听
                //Toast.makeText(getThisContext(), "发生异常，请检查拍照权限！", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void AudioPermissionError() {
                Toast.makeText(getThisContext(), "给点录音权限可以?", Toast.LENGTH_SHORT).show();
            }
        });
        //JCameraView监听
        jCameraView.setJCameraLisenter(new JCameraListener() {
            @Override
            public void captureSuccess(Bitmap bitmap) {
                //获取图片bitmap
//                Log.i("JCameraView", "bitmap = " + bitmap.getWidth());
                String path = FileUtil.saveBitmap("JCamera", bitmap);
                ArrayList<String> pathList = new ArrayList<>();
                pathList.add(path);
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("file", pathList);
                setFragmentResult(ISupportFragment.RESULT_OK, bundle);
                pop();

            }

            @Override
            public void recordSuccess(String url, Bitmap firstFrame) {
                //获取视频路径
                String path = FileUtil.saveBitmap("JCamera", firstFrame);
                ArrayList<String> pathList = new ArrayList<>();
                pathList.add(path);
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("file", pathList);
                setFragmentResult(ISupportFragment.RESULT_OK, bundle);
                pop();
            }


        });
        jCameraView.setLeftClickListener(new ClickListener() {
            @Override
            public void onClick() {
                View decorView = getActivity().getWindow().getDecorView();
                decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
                pop();
            }
        });
    }

    @Override
    public void onPause() {
        Log.i("CJT", "onPause");
        super.onPause();
        jCameraView.onPause();
    }
}
