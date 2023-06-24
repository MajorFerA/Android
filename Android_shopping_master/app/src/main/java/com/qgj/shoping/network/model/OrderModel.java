package com.qgj.shoping.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderModel {

    @Override
    public String toString() {
        return "OrderModel{" +
                "code=" + code +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }

    @SerializedName("code")
    private int code;
    @SerializedName("data")
    private List<DataDTO> data;
    @SerializedName("msg")
    private String msg;

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
        @Override
        public String toString() {
            return "DataDTO{" +
                    "id=" + id +
                    ", gid=" + gid +
                    ", goodname='" + goodname + '\'' +
                    ", img='" + img + '\'' +
                    ", price=" + price +
                    ", num=" + num +
                    ", total=" + total +
                    '}';
        }

        @SerializedName("id")
        private int id;
        @SerializedName("gid")
        private int gid;

        @SerializedName("goodname")
        private String goodname;

        @SerializedName("price")
        private double price;

        @SerializedName("total")
        private double total;

        @SerializedName("img")
        private String img;
        @SerializedName("num")
        private int num;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public Double getTotal() {
            return total;
        }

        public void setTotal(Double total) {
            this.total = total;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }
    }
}
