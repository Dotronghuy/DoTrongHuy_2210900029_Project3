package com.example.DTH_2210900029.entity;

public class PhanCong {
    private int id;
    private int idNhanVien;
    private int idCongViec;
    private String tenNhanVien;
    private String tenCongViec;
    private String ngayBatDau;
    private String ngayKetThuc;

    public PhanCong(int id, int idNhanVien, int idCongViec, String tenNhanVien, String tenCongViec, String ngayBatDau, String ngayKetThuc) {
        this.id = id;
        this.idNhanVien = idNhanVien;
        this.idCongViec = idCongViec;
        this.tenNhanVien = tenNhanVien;
        this.tenCongViec = tenCongViec;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
    }

    public int getId() { return id; }
    public int getIdNhanVien() { return idNhanVien; }
    public int getIdCongViec() { return idCongViec; }
    public String getTenNhanVien() { return tenNhanVien; }
    public String getTenCongViec() { return tenCongViec; }
    public String getNgayBatDau() { return ngayBatDau; }
    public String getNgayKetThuc() { return ngayKetThuc; }
}
