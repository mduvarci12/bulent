package com.projectxr.mehmetd.bulentbey.Models;

public class KitapItem {

    String kitapAdı;
    String fotoUrl;
    int id;

    public KitapItem(String kitapAdı, String fotoUrl, int id) {
        this.kitapAdı = kitapAdı;
        this.fotoUrl = fotoUrl;
        this.id = id;
    }

    public String getKitapAdı() {
        return kitapAdı;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public int getId() {
        return id;
    }
}
