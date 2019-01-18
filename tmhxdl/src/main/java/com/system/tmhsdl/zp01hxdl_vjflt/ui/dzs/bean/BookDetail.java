package com.system.tmhsdl.zp01hxdl_vjflt.ui.dzs.bean;

import java.util.List;

public class BookDetail {

    /**
     * title : 测试电子书
     * price : 15.00
     * is_buy : false
     * ad : /uploads/181221/42850159532502290147834642930322463181123122.jpg
     * image_list : [{"image":"/uploads/181220/2645224729233453138445654719753629132930161.jpg"},{"image":"/uploads/181220/2645224729233453138445654719753629132930161.jpg"},{"image":"/uploads/181220/2645224729233453138445654719753629132930161.jpg"},{"image":"/uploads/181220/2645224729233453138445654719753629132930161.jpg"},{"image":"/uploads/181220/2645224729233453138445654719753629132930161.jpg"}]
     */

    private String title;
    private String price;
    private boolean is_buy;
    private String ad;
    private List<ImageListBean> image_list;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isIs_buy() {
        return is_buy;
    }

    public void setIs_buy(boolean is_buy) {
        this.is_buy = is_buy;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public List<ImageListBean> getImage_list() {
        return image_list;
    }

    public void setImage_list(List<ImageListBean> image_list) {
        this.image_list = image_list;
    }

    public static class ImageListBean {
        /**
         * image : /uploads/181220/2645224729233453138445654719753629132930161.jpg
         */

        private String image;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}
