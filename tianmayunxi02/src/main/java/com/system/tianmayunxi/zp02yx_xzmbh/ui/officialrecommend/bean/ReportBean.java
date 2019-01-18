package com.system.tianmayunxi.zp02yx_xzmbh.ui.officialrecommend.bean;

public class ReportBean {
    private String content;
    private boolean isCheck;

    public ReportBean(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}
