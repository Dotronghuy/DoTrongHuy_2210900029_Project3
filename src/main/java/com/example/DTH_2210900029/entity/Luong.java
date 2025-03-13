package com.example.DTH_2210900029.entity;

import java.math.BigDecimal;

public class Luong {
    private int id;
    private int idNhanVien;
    private BigDecimal luongCoBan;
    private BigDecimal phuCap;
    private BigDecimal khauTru;
    private int thang;
    private int nam;
    private BigDecimal tongGioLam;

    public Luong(int id, int idNhanVien, BigDecimal luongCoBan, BigDecimal phuCap,
                 BigDecimal khauTru, int thang, int nam, BigDecimal tongGioLam) {
        this.id = id;
        this.idNhanVien = idNhanVien;
        this.luongCoBan = luongCoBan;
        this.phuCap = phuCap;
        this.khauTru = khauTru;
        this.thang = thang;
        this.nam = nam;
        this.tongGioLam = tongGioLam;
    }

    public int getId() { return id; }
    public int getIdNhanVien() { return idNhanVien; }
    public BigDecimal getLuongCoBan() { return luongCoBan; }
    public BigDecimal getPhuCap() { return phuCap; }
    public BigDecimal getKhauTru() { return khauTru; }
    public int getThang() { return thang; }
    public int getNam() { return nam; }
    public BigDecimal getTongGioLam() { return tongGioLam; }
}