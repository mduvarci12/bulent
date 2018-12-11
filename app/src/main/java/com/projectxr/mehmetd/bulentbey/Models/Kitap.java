package com.projectxr.mehmetd.bulentbey.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Kitap implements Serializable
{

    @SerializedName("kitap_adi")
    @Expose
    private String kitapAdi;
    @SerializedName("kitap_url")
    @Expose
    private String kitapUrl;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("book_id")
    @Expose
    private Integer bookId;
    private final static long serialVersionUID = 3190361467154251858L;

    public String getKitapAdi() {
        return kitapAdi;
    }

    public void setKitapAdi(String kitapAdi) {
        this.kitapAdi = kitapAdi;
    }

    public Kitap withKitapAdi(String kitapAdi) {
        this.kitapAdi = kitapAdi;
        return this;
    }

    public String getKitapUrl() {
        return kitapUrl;
    }

    public void setKitapUrl(String kitapUrl) {
        this.kitapUrl = kitapUrl;
    }

    public Kitap withKitapUrl(String kitapUrl) {
        this.kitapUrl = kitapUrl;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Kitap withId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Kitap withBookId(Integer bookId) {
        this.bookId = bookId;
        return this;
    }

}