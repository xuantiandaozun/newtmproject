package com.system.tianmayunxi.zp01yx_bwusb.ui.officialrecommend.bean;

import java.util.List;

public class GoodsDetailBean {

    /**
     * id : 4
     * goods_name : 测试
     * goods_content : <p>阿凡达所发生的</p>
     * score : 10
     * is_virtual : 0
     * image_list : [{"image":"/uploads/181214/1324301651162104249627187194190478182474472259.jpg"},{"image":"/uploads/181214/259369296173277427081188892497615527504408.jpg"}]
     */

    private int id;
    private String goods_name;
    private String goods_content;
    private int score;
    private int is_virtual;
    private List<ImageListBean> image_list;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getGoods_content() {
        return goods_content;
    }

    public void setGoods_content(String goods_content) {
        this.goods_content = goods_content;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getIs_virtual() {
        return is_virtual;
    }

    public void setIs_virtual(int is_virtual) {
        this.is_virtual = is_virtual;
    }

    public List<ImageListBean> getImage_list() {
        return image_list;
    }

    public void setImage_list(List<ImageListBean> image_list) {
        this.image_list = image_list;
    }

    public static class ImageListBean {
        /**
         * image : /uploads/181214/1324301651162104249627187194190478182474472259.jpg
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
