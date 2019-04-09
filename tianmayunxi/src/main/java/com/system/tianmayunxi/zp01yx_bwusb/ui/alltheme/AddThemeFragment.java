package com.system.tianmayunxi.zp01yx_bwusb.ui.alltheme;

import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.awen.photo.photopick.bean.PhotoResultBean;
import com.awen.photo.photopick.controller.PhotoPickConfig;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.system.myproject.base.MVPBaseFragment;
import com.system.myproject.utils.GsonUtil;
import com.system.myproject.utils.ToastUtil;
import com.system.tianmayunxi.zp01yx_bwusb.R;
import com.system.tianmayunxi.zp01yx_bwusb.R2;
import com.system.tianmayunxi.zp01yx_bwusb.TmyxRouterConfig;
import com.system.tianmayunxi.zp01yx_bwusb.adapter.AddImagePhotoAdapter;
import com.system.tianmayunxi.zp01yx_bwusb.bean.EventCallBackBean;
import com.system.tianmayunxi.zp01yx_bwusb.bean.TMBaseResoultEntity;
import com.system.tianmayunxi.zp01yx_bwusb.ui.alltheme.bean.IvBean;
import com.system.tianmayunxi.zp01yx_bwusb.ui.alltheme.contract.AllThemeContract;
import com.system.tianmayunxi.zp01yx_bwusb.ui.alltheme.presenter.AllThemePresenter;
import com.system.tianmayunxi.zp01yx_bwusb.ui.officialrecommend.bean.CommonSeeBean;
import com.system.uilibrary.views.attachment.bean.AttachmentEntity;
import com.system.uilibrary.views.titlebar.TitleBarView;
import com.tenma.ventures.bean.utils.TMSharedPUtil;
import com.zxy.tiny.Tiny;
import com.zxy.tiny.callback.FileCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static com.system.myproject.utils.GsonUtil.GsonString;

@Route(path = TmyxRouterConfig.TMYX_ADDZT)
public class AddThemeFragment extends MVPBaseFragment<AllThemeContract.View, AllThemePresenter>
        implements AllThemeContract.View {
    @BindView(R2.id.titleBar)
    TitleBarView titleBar;
    @BindView(R2.id.mlist)
    RecyclerView mlist;
    @BindView(R2.id.ed_title)
    EditText ed_title;
    @BindView(R2.id.ed_content)
    EditText ed_content;
    @BindView(R2.id.tv_theme)
    TextView tv_theme;
    @BindView(R2.id.tv_title)
    TextView tv_title;
    @BindView(R2.id.tv_content)
    TextView tv_content;
    @Autowired(name = "params")
    public String params;
    private AddImagePhotoAdapter mAdapter;
    private CommonSeeBean.ListBean listBean;
    private int themeColor;
    private int textcolor;


    @Override
    protected AllThemePresenter createPresenter() {
        return new AllThemePresenter();
    }

    @Override
    protected void init() {
        HashMap<String, Object> filter = getHashMapByParams(params);
        if (filter != null && filter.containsKey("detail")) {
            String detail = filter.get("detail").toString();
            listBean = GsonUtil.GsonToBean(detail, CommonSeeBean.ListBean.class);
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_addtheme_zp01yx_bwusb;
    }

    @Override
    protected void initDatas() {
        themeColor = Color.parseColor(TMSharedPUtil.getTMThemeColor(getActivity()));
        textcolor = Color.parseColor(TMSharedPUtil.getTMTitleTextColor(getActivity()));
        titleBar.setBackgroundColor(themeColor);
        titleBar.setTitleMainTextColor(textcolor);

        titleBar.setTitleMainText(listBean.getTitle())
                .setLeftTextDrawable(R.mipmap.icon_nav_back)
                .setRightText("发布")
                .setOnRightTextClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String title = ed_title.getText().toString();
                        String content = ed_content.getText().toString();
                        if (TextUtils.isEmpty(title)) {
                            ToastUtil.showSnack(getContext(), "标题不能为空");
                            return;
                        }
                        if (TextUtils.isEmpty(content)) {
                            ToastUtil.showSnack(getContext(), "内容不能为空");
                            return;
                        }
                        List<AttachmentEntity> data = mAdapter.getData();
                        if(data==null||data.size()==0){
                            ToastUtil.showSnack(getContext(), "请选择图片");
                            return;
                        }
                        setTiny(data);

                        //  pushArticle();
                    }
                })
                .setOnLeftTextClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(getPreFragment()!=null){
                            pop();
                        }else {
                            getActivity().finish();
                        }
                    }
                });
        GridLayoutManager mGrdiLayoutManager = new GridLayoutManager(getThisContext(), 3);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.add_themeiv_zp01yx_bwusb, null);
        TextView number = view.findViewById(R.id.tv_number);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int size = mAdapter.getData().size();
                if(size<20){
                    int num=20-size;
                    new PhotoPickConfig.Builder(getActivity()).pickMode(PhotoPickConfig.MODE_MULTIP_PICK) //多选，这里有单选和多选
                            .maxPickSize(num) //最多可选15张
                            .showCamera(true) //是否展示拍照icon,默认展示
                            .clipPhoto(false) //是否选完图片进行图片裁剪，默认是false,如果这里设置成true,就算设置了是多选也会被配置成单选
                            .spanCount(3) //图库的列数，默认3列，这个数建议不要太大
                            .showGif(false)//default true //是否展示gif
                            .setOnPhotoResultCallback(new PhotoPickConfig.Builder.OnPhotoResultCallback() {
                                @Override
                                public void onResult(PhotoResultBean photoResultBean) {
                                    ArrayList<String> photoLists = photoResultBean.getPhotoLists();
                                    AttachmentEntity entity = null;
                                    List<AttachmentEntity> mdata = new ArrayList<>();
                                    for (int i = 0; i < photoLists.size(); i++) {
                                        entity = new AttachmentEntity();
                                        entity.setLocalPath(photoLists.get(i));
                                        mdata.add(entity);
                                    }
                                    mAdapter.addData(mdata);
                                    number.setText(mAdapter.getData().size()+"/20");

                                }
                            }) //设置数据回调，如果不想在Activity通过onActivityResult()获取回传的数据，可实现此接口
                            .build();
                }else {
                    ToastUtil.showSnack(getContext(),"最多只能选20张图片");
                }

            }
        });
        mlist.setLayoutManager(mGrdiLayoutManager);
        mAdapter = new AddImagePhotoAdapter();
        mAdapter.setListener(new AddImagePhotoAdapter.onRemoveListener() {
            @Override
            public void onListener() {
                number.setText(mAdapter.getData().size()+"/20");
            }
        });
        mAdapter.addFooterView(view);
        mAdapter.setFooterViewAsFlow(true);
        mlist.setAdapter(mAdapter);
        tv_theme.setText("#" + listBean.getTitle() + "#");
        ed_title.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                String s1 = ed_title.getText().toString();
                tv_title.setText(s1.length()+"/30");
            }
        });
        ed_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String s1 = ed_content.getText().toString();
                tv_content.setText(s1.length()+"/30");
            }
        });

    }

    private void uploadIv(List<String> data) {

        Observable<TMBaseResoultEntity<Object>>[] observables = new Observable[data.size()];
        String tmToken = TMSharedPUtil.getTMToken(getContext());

        for (int i = 0; i < data.size(); i++) {

            File uploadFile = new File(data.get(i));
            MultipartBody.Builder builder = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)//表单类型
                    .addFormDataPart("token", tmToken);
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), uploadFile);
            builder.addFormDataPart("file", uploadFile.getName(), requestBody);
            List<MultipartBody.Part> part = builder.build().parts();
            Observable<TMBaseResoultEntity<Object>> observable = mPresenter.uploadIv(part);
            observables[i] = observable;
        }
        mPresenter.allUpload(observables);

    }

    private void setTiny(List<AttachmentEntity> data) {
        List<String> mdata=new ArrayList<>();
        for (int i=0;i<data.size();i++){
            Tiny.FileCompressOptions options = new Tiny.FileCompressOptions();
            Tiny.getInstance().source(data.get(i).getLocalPath()).asFile().withOptions(options).compress(new FileCallback() {
                @Override
                public void callback(boolean isSuccess, String outfile) {
                    List<MultipartBody.Part> part=null;
                    if(isSuccess){
                        mdata.add(outfile);
                        if (mdata.size()==data.size()){
                            uploadIv(mdata);
                        }
                    }
                }
            });
        }

    }

    private void pushArticle(List<String> string) {
        String title = ed_title.getText().toString();
        String content = ed_content.getText().toString();

        HashMap<String, String> parms = new HashMap<>();
        parms.put("tid", listBean.getId() + "");
        parms.put("title", title);
        parms.put("content", content);
        parms.put("image", GsonString(string));
        String value = new Gson().toJson(parms);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), value);
        mPresenter.pushArticle(body);

    }

    @Override
    public void onFaild() {

    }

    @Override
    public void callBack(EventCallBackBean bean) {
        int eventNumber = bean.getEventNumber();
        HashMap<String, Object> eventData = bean.getEventData();
        Set<String> keySet = eventData.keySet();
        Iterator<String> iterator = keySet.iterator();
        switch (eventNumber) {
            case EventCallBackBean.REFRESH:
                while (iterator.hasNext()) {
                    String next = iterator.next();
                    Object object = eventData.get(next);
                    switch (next) {
                        case "":
                            break;
                    }
                }
                break;
            case EventCallBackBean.CLOSE:
                break;
            case EventCallBackBean.WHITEDATA:
                while (iterator.hasNext()) {
                    String next = iterator.next();
                    Object object = eventData.get(next);
                    switch (next) {
                        case "addSubscription":

                            break;
                        case "allUpload":
                           // List<IvBean> ivBeans = GsonUtil.GsonToList(object, IvBean.class);
                            List<String> string = null;

                            string = new Gson().fromJson(GsonString(object), (new TypeToken<List<String>>() {
                                }).getType());


                            pushArticle(string);
                            break;
                        case "pushArticle":
                            getActivity().finish();
                            break;
                        default:
                            break;
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void showMessage(int type, String message) {
        ToastUtil.showSnack(getThisContext(), message);
    }
}
