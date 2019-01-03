package com.indroapp.foodkitchen.model;

public class PesananModel {
    private Integer pesananId;
    private Integer noMeja;
    private String pesananTgl;
    private Integer pesananTotal;
    private Integer pesananStatus;

    public Integer getPesananId() {
        return pesananId;
    }

    public void setPesananId(Integer pesananId) {
        this.pesananId = pesananId;
    }

    public Integer getNoMeja() {
        return noMeja;
    }

    public void setNoMeja(Integer noMeja) {
        this.noMeja = noMeja;
    }

    public String getPesananTgl() {
        return pesananTgl;
    }

    public void setPesananTgl(String pesananTgl) {
        this.pesananTgl = pesananTgl;
    }

    public Integer getPesananTotal() {
        return pesananTotal;
    }

    public void setPesananTotal(Integer pesananTotal) {
        this.pesananTotal = pesananTotal;
    }

    public Integer getPesananStatus() {
        return pesananStatus;
    }

    public void setPesananStatus(Integer pesananStatus) {
        this.pesananStatus = pesananStatus;
    }
}
