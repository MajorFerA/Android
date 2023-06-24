package com.qgj.shoping.network.model;

import com.google.gson.annotations.SerializedName;

public class ShoppingModel {
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

    @SerializedName("receiver")
    private String receiver;

    @SerializedName("phone")
    private String phone;

    @SerializedName("shippingaddr")
    private String shippingaddr;

    @SerializedName("score")
    private int score;
    @SerializedName("evaluation")
    private String evaluation;

    @SerializedName("oid")
    private int oid;

    @SerializedName("content")
    private String content;

    @SerializedName("code")
    private int code;
    @SerializedName("data")
    private boolean data;
    @SerializedName("msg")
    private String msg;

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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String time) {
        this.content = content;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int gid) {
        this.oid = oid;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isData() {
        return data;
    }

    public void setData(boolean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
