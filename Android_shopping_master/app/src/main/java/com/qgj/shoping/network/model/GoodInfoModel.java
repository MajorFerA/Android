package com.qgj.shoping.network.model;

import com.google.gson.annotations.SerializedName;

public class GoodInfoModel {

    @SerializedName("code")
    private int code;
    @SerializedName("data")
    private DataDTO data;
    @SerializedName("msg")
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataDTO {
        @SerializedName("gid")
        private int gid;
        @SerializedName("goodname")
        private String goodname;
        @SerializedName("price")
        private double price;
        @SerializedName("info")
        private String info;
        @SerializedName("img")
        private String img;

        public int getGid() {
            return gid;
        }

        public void setGid(int gid) {
            this.gid = gid;
        }

        public String getGoodname() {
            return goodname;
        }

        public void setGoodname(String goodname) {
            this.goodname = goodname;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }
}
