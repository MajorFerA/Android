package com.qgj.shoping.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GoodModel {

    @SerializedName("code")
    private int code;
    @SerializedName("data")
    private List<DataDTO> data;
    @SerializedName("msg")
    private String msg;

    @Override
    public String toString() {
        return "GoodModel{" +
                "code=" + code +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DataDTO> getData() {
        return data;
    }

    public void setData(List<DataDTO> data) {
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

        @Override
        public String toString() {
            return "DataDTO{" +
                    "gid=" + gid +
                    ", goodname='" + goodname + '\'' +
                    ", price=" + price +
                    ", info='" + info + '\'' +
                    ", img='" + img + '\'' +
                    '}';
        }

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
