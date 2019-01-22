package com.system.myproject;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;

import com.system.myproject.utils.FileUtils;

import java.io.File;


public class Constants {

    //百度地图预载
    private static String BAIDU_MAP_PICTURE="http://api.map.baidu.com/staticimage/v2?ak=UtYkbD5ewdkYGliYOuOLUd8uZxlC99zK"
            +"&center=%s&width=800&height=600&zoom=16&markerStyles=l,1,0xFF0000&markers=%s";


    public static int NOTI_FLAG_MAIN = 1000;
    public static int NOTI_FLAG_PUSH = 101;

    private static String V_SERVER_NEWIP = "http://222.161.197.93:8071/";
   // private static String V_SERVER_IP = "http://222.161.197.93:7000";
    private static String V_SERVER_IMAGE_ICON = "http://222.161.197.93:8070/";
    private static String V_NEW_SERVER_IP = "http://222.161.197.93:8091";// "http://61.138.131.166/api";//
    /*附件*/
    private static String V_SERVER_DOWNLOAD = "http://222.161.197.93:7999/TempUpload2/"; /* 下载路径 */

    public static String getURL(Context context) {
        return V_SERVER_NEWIP + getMetaValue(context, "smart_city_key");
    }


    /**
     * 根据经纬度返回静态图地址
     * @param longitude
     * @param latitude
     * @return
     */
    public static String getBaiduMapPicture(String longitude, String latitude){
        String center= String.format("%s,%s",longitude,latitude);
        return String.format(BAIDU_MAP_PICTURE,center,center);
    }

    /**
     * 获取Mainifest.xml的值
     * @param context
     * @param metaKey
     * @return
     */
    public static String getMetaValue(Context context, String metaKey) {
        Bundle metaData = null;
        String keyValue = "";
        if (context == null || metaKey == null) {
            return null;
        }
        try {
            ApplicationInfo ai = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(),
                            PackageManager.GET_META_DATA);
            if (null != ai) {
                metaData = ai.metaData;
            }
            if (null != metaData) {
                keyValue = metaData.getString(metaKey);
            }
        } catch (PackageManager.NameNotFoundException e) {

        }
        if(TextUtils.isEmpty(keyValue)){
            return "";
        }else{
            return keyValue.replace("#","");
        }

    }

    public static String getWorkFlowFilePath(String url) {

        return V_SERVER_DOWNLOAD + url;
    }

    public static String getServerImageIcon(String url) {
        if (url.contains("~/")) {
            url = url.replace("~/", "").replace("~", "");
        }
        return V_SERVER_IMAGE_ICON + url;
    }

    public static String getWorkFlowUploadUrl(String key) {
        return "http://222.161.197.93:8070/" + key + "/workflowmobile/AttachmentUpload";

    }

    public static String getUploadUrl(String key) {
        return "http://222.161.197.93:8070/" + key + "/fileuploadmobile/AttachmentUpload";

    }


    /**
     * 文件上传
     */
    public static String URL_FILE_UPLOAD = "http://222.161.197.93/zhcs/API/MobileFileUpload.aspx";

    /***************************API V1 START********************************************/

    /**
     * 用户登录
     */
    private static String URL_LOGING =  "/api/v1/login"; // 用户登录

    public static String getUrlLogin(Context context) {
        return getURL(context) + URL_LOGING;
    }

    /**
     * 字典表
     * /api/v1/zidianbiao?token=
     */
    private static String URL_DICTIONARY =  "/api/v1/zidianbiao";

    public static String getUrlDictioniary(Context context) {
        return getURL(context) + URL_DICTIONARY;
    }

    /**
     * 桌面
     */
    private static String URL_DESKTOP = "/api/v1/desktop";

    public static String getUrlDesktop(Context context) {
        return getURL(context) + URL_DESKTOP;
    }

    /**
     * 用户
     */
    private static String URL_USER = "/api/v1/user";

    public static String getUrlUser(Context context) {
        return getURL(context) + URL_USER;
    }

    /**
     * 意见反馈
     */
    private static String URL_FEEDBACK = "/api/v1/feedback";

    public static String getUrlFeedBack(Context context) {
        return getURL(context) + URL_FEEDBACK;
    }

    /**
     * 公共通讯录
     */
    private static String URL_COMMUNICATION ="/api/v1/communication";

    public static String getUrlCommunication(Context context) {
        return getURL(context) + URL_COMMUNICATION;
    }

    /**
     * 个人通讯录
     */
    private static String URL_PERSON_COMMUNICATION =  "/api/v1/personcommunication";

    public static String getUrlPersonCommunication(Context context) {
        return getURL(context) + URL_PERSON_COMMUNICATION;
    }

    /**
     * 便民通讯录
     */
    private static String URL_ADDRESSBOOK = "/api/v1/addressbook";

    public static String getUrlAddressBook(Context context) {
        return getURL(context) + URL_ADDRESSBOOK;
    }

    /**
     * 工作笔记
     */
    private static String URL_NOTES = "/api/v1/notes";

    public static String getUrlNotes(Context context) {
        return getURL(context) + URL_NOTES;
    }

    /**
     * 短消息
     */
    private static String URL_MESSAGE =  "/api/v1/message";

    public static String getUrlMessage(Context context) {
        return getURL(context) + URL_MESSAGE;
    }

    /**
     * GPS
     */
    private static String URL_GPS =  "/api/v1/gps";

    public static String getUrlGPS(Context context) {
        return getURL(context) + URL_GPS;
    }


    /**
     * 获取频道分类
     * api/columntype?token=&osdept=100144
     */
    private static String URL_COLUMN_TYPE = "/api/v1/columntype";

    public static String getUrlColumnType(Context context) {
        return getURL(context) + URL_COLUMN_TYPE;
    }

    /**
     * 获取频道
     * api/column?token=&tid=579C2C42-F623-4AEE-8AB1-CDFAB68F7C4E
     */
    private static String URL_COLUMN = "/api/v1/column";

    public static String getUrlColumn(Context context) {
        return getURL(context) + URL_COLUMN;
    }

    /**
     * 获取新闻频道
     * api/information?token=&column=&page=&size=
     */
    private static String URL_INFORMATION =  "/api/v1/information";

    public static String getUrlInformation(Context context) {
        return getURL(context) + URL_INFORMATION;
    }

    /**
     * 服务检查
     * api/service?token=
     */
    private static String URL_SERVICE =  "/api/v1/service";

    public static String getUrlService(Context context) {
        return getURL(context) + URL_SERVICE;
    }


    /***************************API V1 END********************************************/


    /***************************API V1 WORKFLOW START********************************************/

    /**
     * 工作流EventType
     */
    private static String URL_WORKFLOW_EVENT_TYPE = "/api/v1/workfloweventtype";

    public static String getUrlWorkflowEventType(Context context) {
        return getURL(context) + URL_WORKFLOW_EVENT_TYPE;
    }


    /**
     * 工作流-EVENT
     * api/v1/wokflow?token=&type=&page=&size=
     */
    private static String URL_WORKFLOW =  "/api/v1/workflow";

    public static String getUrlWorkflow(Context context) {
        return getURL(context) + URL_WORKFLOW;
    }

    /**
     * 工作流-EVENT
     * api/v1/wokflowevent?token=&type=&page=&size=
     */
    private static String URL_WORKFLOW_EVENT =  "/api/v1/workflowevent";

    public static String getUrlWorkflowEvent(Context context) {
        return getURL(context) + URL_WORKFLOW_EVENT;
    }

    /***************************API V1 WORKFLOW END********************************************/

    /**
     * 创建缓存文件夹
     */
    public static void createCacheDirectory() {
        String path = FileUtils.getSdCardPath() + "/SmartCity";
        File dir = new File(path);
        if (!dir.exists())
            dir.mkdir();
        String pathImage = FileUtils.getSdCardPath() + "/SmartCity/Image";
        dir = new File(pathImage);
        if (!dir.exists())
            dir.mkdir();
        String pathVideo = FileUtils.getSdCardPath() + "/SmartCity/Video";
        dir = new File(pathVideo);
        if (!dir.exists())
            dir.mkdir();
        String pathAudio = FileUtils.getSdCardPath() + "/SmartCity/Audio";
        dir = new File(pathAudio);
        if (!dir.exists())
            dir.mkdir();
        String pathOther = FileUtils.getSdCardPath() + "/SmartCity/Other";
        dir = new File(pathOther);
        if (!dir.exists())
            dir.mkdir();
    }
    public static String getCachePath()
    {
        return  FileUtils.getSdCardPath() + "/SmartCity";
    }
    public static String getCacheAudioPath()
    {
        return  FileUtils.getSdCardPath() + "/SmartCity/Audio";
    }
    public static String getCacheImagePath()
    {
        return  FileUtils.getSdCardPath() + "/SmartCity/Image";
    }
    public static String getCacheVideoPath()
    {
        return  FileUtils.getSdCardPath() + "/SmartCity/Video";
    }
    public static String getCacheOtherPath()
    {
        return  FileUtils.getSdCardPath() + "/SmartCity/Other";
    }

}
