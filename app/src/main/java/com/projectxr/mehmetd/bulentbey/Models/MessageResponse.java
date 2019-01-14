package com.projectxr.mehmetd.bulentbey.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MessageResponse implements Serializable {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("ticket")


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}