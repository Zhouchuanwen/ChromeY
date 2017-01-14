package com.alan.bean;

/**
 * Created by alan on 17/1/11.
 */
public enum Types {

    /**
     * 网站类型
     */
    Life(1,"新闻"),

    Tech(2,"科技"),

    IT(3,"IT"),

    Spare(4,"娱乐"),

    Share(5,"社交"),

    Seeing(6,"看见"),

    Others(7,"其它");

    private int code;

    private String msg;

    Types(int code, String msg) {
        this.code=code;
        this.msg=msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
