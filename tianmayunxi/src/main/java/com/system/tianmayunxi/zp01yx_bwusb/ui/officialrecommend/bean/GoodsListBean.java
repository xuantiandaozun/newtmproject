package com.system.tianmayunxi.zp01yx_bwusb.ui.officialrecommend.bean;

import java.util.List;

public class GoodsListBean {


    /**
     * total_page : 1
     * now_page : 1
     * list : [{"id":4,"goods_name":"测试","image":"/uploads/181214/12320720438736343029226931430352331396287517370.jpg","score":10,"is_virtual":0}]
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
         * id : 4
         * goods_name : 测试
         * image : /uploads/181214/12320720438736343029226931430352331396287517370.jpg
         * score : 10
         * is_virtual : 0
         */

        private int id;
        private String goods_name;
        private String image;
        private int score;
        private int is_virtual;

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

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
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
    }
}
