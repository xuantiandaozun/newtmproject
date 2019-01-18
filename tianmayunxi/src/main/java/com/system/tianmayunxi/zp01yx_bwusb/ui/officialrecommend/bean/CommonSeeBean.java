package com.system.tianmayunxi.zp01yx_bwusb.ui.officialrecommend.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

import static com.system.tianmayunxi.zp01yx_bwusb.ui.officialrecommend.adapter.officAdapter.DATA_TYPE1;


public class CommonSeeBean implements MultiItemEntity {
    @Override
    public int getItemType() {
        return DATA_TYPE1;
    }
    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 9
         * title : 测试
         * image : /uploads/181204/464359217221081633491344214963915131585029791.jpg
         */

        private int id;
        private String title;
        private String image;

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

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}
