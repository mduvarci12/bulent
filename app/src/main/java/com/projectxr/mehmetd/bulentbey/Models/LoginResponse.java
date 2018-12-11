package com.projectxr.mehmetd.bulentbey.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginResponse implements Serializable
{

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("key")
    @Expose
    private String key;
    private final static long serialVersionUID = -5172921861899021549L;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LoginResponse withStatus(String status) {
        this.status = status;
        return this;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public LoginResponse withKey(String key) {
        this.key = key;
        return this;
    }

}