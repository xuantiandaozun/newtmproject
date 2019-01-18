package com.system.tianmayunxi.zp02yx_xzmbh.ui.alltheme.bean;

import java.util.List;

public class AllThemBean {

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 7
         * image : /uploads/181204/45733213542951753472961795782318349383422500.jpg
         * title : 啊发大水
         * is_sub : false
         */

        private int id;
        private String image;
        private String title;
        private boolean is_sub;

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

        public boolean isIs_sub() {
            return is_sub;
        }

        public void setIs_sub(boolean is_sub) {
            this.is_sub = is_sub;
        }
    }
}
