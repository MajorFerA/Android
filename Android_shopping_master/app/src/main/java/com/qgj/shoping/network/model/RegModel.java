package com.qgj.shoping.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RegModel {

    @SerializedName("code")
    private int code;
    @SerializedName("data")
    private List<DataDTO> data;
    @SerializedName("msg")
    private Object msg;

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

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public static class DataDTO {
        @SerializedName("id")
        private int id;
        @SerializedName("username")
        private String username;
        @SerializedName("password")
        private String password;

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
    }
}
