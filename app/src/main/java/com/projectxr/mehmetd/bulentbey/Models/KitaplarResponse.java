package com.projectxr.mehmetd.bulentbey.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class KitaplarResponse implements Serializable
{

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("kitaplar")
    @Expose
    private List<Kitaplar> kitaplar = null;
    private final static long serialVersionUID = -9122479780219137695L;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public KitaplarResponse withStatus(String status) {
        this.status = status;
        return this;
    }

    public List<Kitaplar> getKitaplar() {
        return kitaplar;
    }

    public void setKitaplar(List<Kitaplar> kitaplar) {
        this.kitaplar = kitaplar;
    }

    public KitaplarResponse withKitaplar(List<Kitaplar> kitaplar) {
        this.kitaplar = kitaplar;
        return this;
    }

}