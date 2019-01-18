package com.system.tmhsdl.zp01hxdl_vjflt.ui.dzs.bean;

import java.util.List;

public class ContentListBean {

    /**
     * total_page : 4
     * now_page : 1
     * list : [{"id":1,"image":"/uploads/181220/4925136635537047137210341116313750344545574302.jpg","title":"测试电子书-1"},{"id":2,"image":null,"title":"测试电子书-2"},{"id":3,"image":null,"title":"测试电子书-3"},{"id":4,"image":null,"title":"测试电子书-4"},{"id":5,"image":"0","title":"测试电子书-5"},{"id":6,"image":"0","title":"测试电子书-6"},{"id":7,"image":"0","title":"测试电子书-7"},{"id":8,"image":"0","title":"测试电子书-8"},{"id":9,"image":"0","title":"测试电子书-9"},{"id":10,"image":"0","title":"测试电子书-10"}]
     */

    private int total_page;
    private int now_page;
    private int count;
    private List<ListBean> list;

    public int getTotal_page() {
        return total_page;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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
         * id : 1
         * image : /uploads/181220/4925136635537047137210341116313750344545574302.jpg
         * title : 测试电子书-1
         */

        private int id;
        private String image;
        private String title;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
