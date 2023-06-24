package com.qgj.shoping.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllModel {

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
        @SerializedName("id")
        private int id;
        @SerializedName("uid")
        private int uid;
        @SerializedName("gid")
        private int gid;
        @SerializedName("time")
        private String time;
        @SerializedName("status")
        private String status;
        @SerializedName("num")
        private int num;
        @SerializedName("price")
        private double price;

        @SerializedName("total")
        private double total;

        @SerializedName("goodname")
        private String goodname;

        @SerializedName("img")
        private String img;

        @SerializedName("receiver")
        private String receiver;

        @SerializedName("phone")
        private String phone;
        @SerializedName("shippingaddr")
        private String shippingaddr;

        @SerializedName("ostate")
        private Integer ostate;

        @SerializedName("score")
        private Integer score;

        @SerializedName("evaluation")
        private String evaluation;

        @SerializedName("evaluatetime")
        private String evaluatetime;


        public String getGoodname() {
            return goodname;
        }

        public void setGoodname(String goodname) {
            this.goodname = goodname;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

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

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
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

        public String getReceiver() {
            return receiver;
        }

        public void setReceiver(String receiver) {
            this.receiver = receiver;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getShippingaddr() {
            return shippingaddr;
        }

        public void setShippingaddr(String shippingaddr) {
            this.shippingaddr = shippingaddr;
        }

        public Integer getOstate() {
            return ostate;
        }

        public void setOstate(Integer ostate) {
            this.ostate = ostate;
        }

        public Integer getScore() {
            return score;
        }

        public void setScore(Integer score) {
            this.score = score;
        }

        public String getEvaluation() {
            return evaluation;
        }

        public void setEvaluation(String evaluation) {
            this.evaluation = evaluation;
        }

        public String getEvaluatetime() {
            return evaluatetime;
        }

        public void setEvaluatetime(String evaluatetime) {
            this.evaluatetime = evaluatetime;
        }
    }
}
