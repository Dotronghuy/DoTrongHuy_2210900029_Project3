package com.example.DTH_2210900029.entity;

import java.math.BigDecimal;

public class Luong {
    private int id;
    private int idNhanVien;
    private BigDecimal luongCoBan;
    private BigDecimal thuong;
    private BigDecimal phuCap;
    private BigDecimal khauTru;
    private BigDecimal tongLuong;
    private int thang;
    private int nam;
    private BigDecimal tongGioLam;

    public Luong(int id, int idNhanVien, BigDecimal luongCoBan, BigDecimal thuong, BigDecimal phuCap,
                 BigDecimal khauTru, BigDecimal tongLuong, int thang, int nam, BigDecimal tongGioLam) {
        this.id = id;
        this.idNhanVien = idNhanVien;
        this.luongCoBan = luongCoBan;
        this.thuong = thuong;
        this.phuCap = phuCap;
        this.khauTru = khauTru;
        this.tongLuong = tongLuong;
        this.thang = thang;
        this.nam = nam;
        this.tongGioLam = tongGioLam;
    }

    public int getId() { return id; }
    public int getIdNhanVien() { return idNhanVien; }
    public BigDecimal getLuongCoBan() { return luongCoBan; }
    public BigDecimal getThuong() { return thuong; }
    public BigDecimal getPhuCap() { return phuCap; }
    public BigDecimal getKhauTru() { return khauTru; }
    public BigDecimal getTongLuong() { return tongLuong; }
    public int getThang() { return thang; }
    public int getNam() { return nam; }
    public BigDecimal getTongGioLam() { return tongGioLam; }
}
