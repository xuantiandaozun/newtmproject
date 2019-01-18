package com.system.tianmayunxi.zp02yx_xzmbh.ui.officialrecommend.bean;

import java.util.List;

public class ArticMsgBean {

    /**
     * total_page : 1
     * now_page : 1
     * list : [{"id":11,"create_time":"4小时前","num":0,"content":"555555","member_nickname":null,"head_pic":null,"like_num":0,"pid":0,"land_id":1,"is_landlord":true}]
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
         * id : 11
         * create_time : 4小时前
         * num : 0
         * content : 555555
         * member_nickname : null
         * head_pic : null
         * like_num : 0
         * pid : 0
         * land_id : 1
         * is_landlord : true
         */

        private int id;
        private String create_time;
        private int num;
        private String content;
        private String member_nickname;
        private String head_pic;
        private int like_num;
        private int pid;
        private int land_id;
        private boolean is_landlord;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
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

        public int getLike_num() {
            return like_num;
        }

        public void setLike_num(int like_num) {
            this.like_num = like_num;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public int getLand_id() {
            return land_id;
        }

        public void setLand_id(int land_id) {
            this.land_id = land_id;
        }

        public boolean isIs_landlord() {
            return is_landlord;
        }

        public void setIs_landlord(boolean is_landlord) {
            this.is_landlord = is_landlord;
        }
    }
}
