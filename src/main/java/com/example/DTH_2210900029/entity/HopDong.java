package com.example.DTH_2210900029.entity;

import java.util.Date;

public class HopDong {
    private int id;
    private int idNhanVien;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private String loaiHopDong;

    public HopDong(int id, int idNhanVien, Date ngayBatDau, Date ngayKetThuc, String loaiHopDong) {
        this.id = id;
        this.idNhanVien = idNhanVien;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.loaiHopDong = loaiHopDong;
    }

    public int getId() { return id; }
    public int getIdNhanVien() { return idNhanVien; }
    public Date getNgayBatDau() { return ngayBatDau; }
    public Date getNgayKetThuc() { return ngayKetThuc; }
    public String getLoaiHopDong() { return loaiHopDong; }
}

