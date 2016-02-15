package com.example.administrator.shikee_test.myAdaper;

/**
 * Created by lt413 on 2016/2/15.
 */
public class Item_data {

    private String albnm;
    private String msg;
    private String imgUrl;

    public Item_data(String albnm,String msg,String imgUrl)
    {
        this.albnm=albnm;
        this.msg=msg;
        this.imgUrl =imgUrl;
    }

    public String getAlbnm() {
        return albnm;
    }

    public String getMsg() {
        return msg;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
