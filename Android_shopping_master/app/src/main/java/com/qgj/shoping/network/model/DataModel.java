package com.qgj.shoping.network.model;

import com.google.gson.annotations.SerializedName;

public class DataModel {


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
        @SerializedName("id")
        private int id;
        @SerializedName("username")
        private String username;
        @SerializedName("password")
        private String password;
        @SerializedName("total")
        private double total;
        @SerializedName("phone")
        private String phone;
        @SerializedName("shippingaddr")
        private String shippingaddr;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public double getTotal() {
            return total;
        }

        public void setTotal(double total) {
            this.total = total;
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
    }
}
