package com.trantan.lab07_tranvantan_quanlytruongdaihoc.model;

public class TruongDaiHoc {
    private int id;
    private String maTruong;
    private String tenTruong;

    public TruongDaiHoc(int id, String maTruong, String tenTruong) {
        this.id = id;
        this.maTruong = maTruong;
        this.tenTruong = tenTruong;
    }

    public TruongDaiHoc(String maTruong, String tenTruong) {
        this.maTruong = maTruong;
        this.tenTruong = tenTruong;
    }

    public TruongDaiHoc() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaTruong() {
        return maTruong;
    }

    public void setMaTruong(String maTruong) {
        this.maTruong = maTruong;
    }

    public String getTenTruong() {
        return tenTruong;
    }

    public void setTenTruong(String tenTruong) {
        this.tenTruong = tenTruong;
    }
}
