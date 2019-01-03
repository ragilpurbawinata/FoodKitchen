package com.indroapp.foodkitchen.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailModel {
    @SerializedName("makanan")
    @Expose
    private String makanan;
    @SerializedName("jml_makanan")
    @Expose
    private String jmlMakanan;
    @SerializedName("minuman")
    @Expose
    private String minuman;
    @SerializedName("jml_minuman")
    @Expose
    private String jmlMinuman;

    public String getMakanan() {
        return makanan;
    }

    public void setMakanan(String makanan) {
        this.makanan = makanan;
    }

    public String getJmlMakanan() {
        return jmlMakanan;
    }

    public void setJmlMakanan(String jmlMakanan) {
        this.jmlMakanan = jmlMakanan;
    }

    public String getMinuman() {
        return minuman;
    }

    public void setMinuman(String minuman) {
        this.minuman = minuman;
    }

    public String getJmlMinuman() {
        return jmlMinuman;
    }

    public void setJmlMinuman(String jmlMinuman) {
        this.jmlMinuman = jmlMinuman;
    }
}
