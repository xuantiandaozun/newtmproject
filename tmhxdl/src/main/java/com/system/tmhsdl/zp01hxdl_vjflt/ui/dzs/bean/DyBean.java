package com.system.tmhsdl.zp01hxdl_vjflt.ui.dzs.bean;

public class DyBean {

    /**
     * id : 1
     * price : 98.00
     * image : /uploads/181220/2011581253226540042222245744212480218357417386.jpg
     */

    private int id;
    private String price;
    private String image;
    private String start_time;
    private String end_time;
    private boolean is_buy;

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public boolean isIs_buy() {
        return is_buy;
    }

    public void setIs_buy(boolean is_buy) {
        this.is_buy = is_buy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
