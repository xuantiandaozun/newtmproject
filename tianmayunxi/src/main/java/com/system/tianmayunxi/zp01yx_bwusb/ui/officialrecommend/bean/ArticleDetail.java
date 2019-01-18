package com.system.tianmayunxi.zp01yx_bwusb.ui.officialrecommend.bean;

import java.util.List;

public class ArticleDetail {

    /**
     * id : 1
     * title : 测试22
     * content : 测算测算
     * create_at : 23小时前
     * like_num : 1
     * msg_num : 1
     * theme_title : 阿达
     * member_nickname : null
     * head_pic : null
     * images : [{"aid":1,"image":"图片2"},{"aid":1,"image":"图片1"}]
     */

    private int id;
    private int tid;
    private String title;
    private String content;
    private String create_at;
    private String theme_image;
    private int like_num;
    private int msg_num;
    private String theme_title;
    private String member_nickname;
    private String head_pic;
    private List<ImagesBean> images;

    public String getTheme_image() {
        return theme_image;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public void setTheme_image(String theme_image) {
        this.theme_image = theme_image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreate_at() {
        return create_at;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }

    public int getLike_num() {
        return like_num;
    }

    public void setLike_num(int like_num) {
        this.like_num = like_num;
    }

    public int getMsg_num() {
        return msg_num;
    }

    public void setMsg_num(int msg_num) {
        this.msg_num = msg_num;
    }

    public String getTheme_title() {
        return theme_title;
    }

    public void setTheme_title(String theme_title) {
        this.theme_title = theme_title;
    }

    public String getMember_nickname() {
        return member_nickname;
    }

    public void setMember_nickname(String member_nickname) {
        this.member_nickname = member_nickname;
    }

    public String getHead_pic() {
        return head_pic;
    }

    public void setHead_pic(String head_pic) {
        this.head_pic = head_pic;
    }

    public List<ImagesBean> getImages() {
        return images;
    }

    public void setImages(List<ImagesBean> images) {
        this.images = images;
    }

    public static class ImagesBean {
        /**
         * aid : 1
         * image : 图片2
         */

        private int aid;
        private String image;

        public int getAid() {
            return aid;
        }

        public void setAid(int aid) {
            this.aid = aid;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}
