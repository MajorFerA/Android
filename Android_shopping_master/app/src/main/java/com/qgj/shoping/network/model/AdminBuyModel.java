package com.qgj.shoping.network.model;

import com.google.gson.annotations.SerializedName;

public class AdminBuyModel {

    @SerializedName("uid")
    private int uid;
    @SerializedName("gid")
    private int gid;
    @SerializedName("num")
    private int num;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
