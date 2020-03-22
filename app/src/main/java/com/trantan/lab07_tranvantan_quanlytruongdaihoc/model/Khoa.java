package com.trantan.lab07_tranvantan_quanlytruongdaihoc.model;

public class Khoa {
    private int id;
    private String tenTruongHoc;
    private String toaNha;
    private String tenKhoa;
    private int soDienThoai;

    public Khoa(int id, String tenTruongHoc, String toaNha, String tenKhoa, int soDienThoai) {
        this.id = id;
        this.tenTruongHoc = tenTruongHoc;
        this.toaNha = toaNha;
        this.tenKhoa = tenKhoa;
        this.soDienThoai = soDienThoai;
    }

    public Khoa(String tenTruongHoc, String toaNha, String tenKhoa, int soDienThoai) {
        this.tenTruongHoc = tenTruongHoc;
        this.toaNha = toaNha;
        this.tenKhoa = tenKhoa;
        this.soDienThoai = soDienThoai;
    }

    public Khoa() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenTruongHoc() {
        return tenTruongHoc;
    }

    public void setTenTruongHoc(String tenTruongHoc) {
        this.tenTruongHoc = tenTruongHoc;
    }

    public String getToaNha() {
        return toaNha;
    }

    public void setToaNha(String toaNha) {
        this.toaNha = toaNha;
    }

    public String getTenKhoa() {
        return tenKhoa;
    }

    public void setTenKhoa(String tenKhoa) {
        this.tenKhoa = tenKhoa;
    }

    public int getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(int soDienThoai) {
        this.soDienThoai = soDienThoai;
    }
}
