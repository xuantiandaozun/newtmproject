package com.system.tmhsdl.zp01hxdl_vjflt.ui.dzs.bean;

public class IconBean {
    private String name;
    private String icon;
    private String router;

    public IconBean(String name) {
        this.name = name;
    }

    public IconBean(String name, String icon, String router) {
        this.name = name;
        this.icon = icon;
        this.router = router;
    }

    public IconBean(String name, String icon) {
        this.name = name;
        this.icon = icon;
    }

    public IconBean() {
    }

    public String getRouter() {
        return router;
    }

    public void setRouter(String router) {
        this.router = router;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
