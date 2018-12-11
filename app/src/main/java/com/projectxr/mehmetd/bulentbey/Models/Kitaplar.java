package com.projectxr.mehmetd.bulentbey.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Kitaplar implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("kitap_adi")
    @Expose
    private String kitapAdi;
    @SerializedName("kitap_resmi")
    @Expose
    private String kitapResmi;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    private final static long serialVersionUID = -5103825650644636496L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Kitaplar withId(Integer id) {
        this.id = id;
        return this;
    }

    public String getKitapAdi() {
        return kitapAdi;
    }

    public void setKitapAdi(String kitapAdi) {
        this.kitapAdi = kitapAdi;
    }

    public Kitaplar withKitapAdi(String kitapAdi) {
        this.kitapAdi = kitapAdi;
        return this;
    }

    public String getKitapResmi() {
        return kitapResmi;
    }

    public void setKitapResmi(String kitapResmi) {
        this.kitapResmi = kitapResmi;
    }

    public Kitaplar withKitapResmi(String kitapResmi) {
        this.kitapResmi = kitapResmi;
        return this;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Kitaplar withCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Kitaplar withUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

}