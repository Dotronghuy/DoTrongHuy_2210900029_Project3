package com.example.DTH_2210900029.entity;

import java.util.Date;

public class ChamCong {
    private int id;
    private int idNhanVien;
    private Date ngayLam;
    private int soGioLam;

    public ChamCong(int id, int idNhanVien, Date ngayLam, int soGioLam) {
        this.id = id;
        this.idNhanVien = idNhanVien;
        this.ngayLam = ngayLam;
        this.soGioLam = soGioLam;
    }

    public int getId() { return id; }
    public int getIdNhanVien() { return idNhanVien; }
    public Date getNgayLam() { return ngayLam; }
    public int getSoGioLam() { return soGioLam; }
}

