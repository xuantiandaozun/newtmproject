package com.system.tmhsdl.zp01hxdl_vjflt.ui.dzs.bean;

import java.util.List;

public class InssBean {

    /**
     * total_page : 1
     * now_page : 1
     * list : [{"id":3,"image":"/uploads/181219/286371412508380432292521525557236320243404401.jpg","issn":"is123465","title":"测试"},{"id":2,"image":"/uploads/181219/286371412508380432292521525557236320243404401.jpg","issn":"is123465","title":"测试"},{"id":1,"image":"/uploads/181219/286371412508380432292521525557236320243404401.jpg","issn":"is123465","title":"测试"}]
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
         * id : 3
         * image : /uploads/181219/286371412508380432292521525557236320243404401.jpg
         * issn : is123465
         * title : 测试
         */

        private int id;
        private String image;
        private String issn;
        private String title;
        private int create_time;

        public int getCreate_time() {
            return create_time;
        }

        public void setCreate_time(int create_time) {
            this.create_time = create_time;
        }

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

        public String getIssn() {
            return issn;
        }

        public void setIssn(String issn) {
            this.issn = issn;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
