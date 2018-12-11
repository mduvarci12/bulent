package com.projectxr.mehmetd.bulentbey.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class PartResponse implements Serializable
{

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("kitap")
    @Expose
    private List<Kitap> kitap = null;
    private final static long serialVersionUID = 1304321039222872087L;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PartResponse withStatus(String status) {
        this.status = status;
        return this;
    }

    public List<Kitap> getKitap() {
        return kitap;
    }

    public void setKitap(List<Kitap> kitap) {
        this.kitap = kitap;
    }

    public PartResponse withKitap(List<Kitap> kitap) {
        this.kitap = kitap;
        return this;
    }

}