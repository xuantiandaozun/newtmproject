package com.system.tianmayunxi.zp01yx_bwusb.ui.officialrecommend.bean;

import java.util.List;

public class ArticleBean {

    /**
     * total_page : 1
     * now_page : 1
     * list : [{"id":1,"title":"测试22","content":"测算测算","member_nickname":null,"head_pic":null,"theme_title":"阿达","create_at":"23小时前","like_num":0,"msg_num":1,"theme_image":"/uploads/181204/394400371539525325523494504181872311978248644.jpg","image":["图片1","图片2"]}]
     */

    private int total_page;
    private int now_page;
    private List<TieZiBean> list;

    public int getTotal_page() {
        return total_page;
    }

    public void setTotal_page(int total_page) {
        this.total_page = total_page;
    }

    public int getNow_page() {
        return now_page;
    }

    public void setNow_page(int now_page) {
        this.now_page = now_page;
    }

    public List<TieZiBean> getList() {
        return list;
    }

    public void setList(List<TieZiBean> list) {
        this.list = list;
    }

}
