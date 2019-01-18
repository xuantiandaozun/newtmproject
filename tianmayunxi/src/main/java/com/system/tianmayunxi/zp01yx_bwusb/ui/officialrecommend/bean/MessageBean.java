package com.system.tianmayunxi.zp01yx_bwusb.ui.officialrecommend.bean;

import java.util.List;

public class MessageBean {

    /**
     * total_page : 1
     * now_page : 1
     * list : [{"create_time":"24分钟前","type":1,"member_nickname":null,"head_pic":null,"content":"555555","image":null,"aid":null}]
     */

    private int total_page;
    private int now_page;
    private List<ListBean> list;

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

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * create_time : 24分钟前
         * type : 1
         * member_nickname : null
         * head_pic : null
         * content : 555555
         * image : null
         * aid : null
         */

        private String create_time;
        private int type;
        private String member_nickname;
        private String head_pic;
        private String content;
        private String image;
        private String aid;

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getAid() {
            return aid;
        }

        public void setAid(String aid) {
            this.aid = aid;
        }
    }
}
