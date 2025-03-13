package com.example.DTH_2210900029.entity;

public class PhanCong {
    private int id;
    private int idNhanVien;
    private int idCongViec;
    private String ngayBatDau;
    private String ngayKetThuc;
    private String tenNhanVien;
    private String tenCongViec;

    public PhanCong(int id, int idNhanVien, String tenNhanVien, int idCongViec, String tenCongViec, String ngayBatDau, String ngayKetThuc) {
        this.id = id;
        this.idNhanVien = idNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.idCongViec = idCongViec;
        this.tenCongViec = tenCongViec;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
    }

    public int getId() { return id; }
    public int getIdNhanVien() { return idNhanVien; }
    public String getTenNhanVien() { return tenNhanVien; }
    public int getIdCongViec() { return idCongViec; }
    public String getTenCongViec() { return tenCongViec; }
    public String getNgayBatDau() { return ngayBatDau; }
    public String getNgayKetThuc() { return ngayKetThuc; }
}
