package com.system.tianmayunxi.zp02yx_xzmbh.ui.officialrecommend.bean;

import java.util.List;

public class ListSignBean {

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * is_sign : true
         * day : 1
         * score : 2
         */

        private boolean is_sign;
        private int day;
        private int score;

        public boolean isIs_sign() {
            return is_sign;
        }

        public void setIs_sign(boolean is_sign) {
            this.is_sign = is_sign;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }
    }
}
