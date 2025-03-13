package com.example.DTH_2210900029.entity;

import java.sql.Date;
import java.sql.Time;

public class ChamCong {
    private int id;
    private int idNhanVien;
    private Date ngayLam;
    private Time gioVao;
    private Time gioRa;
    private double tongGio;

    public ChamCong(int id, int idNhanVien, Date ngayLam, Time gioVao, Time gioRa, double tongGio) {
        this.id = id;
        this.idNhanVien = idNhanVien;
        this.ngayLam = ngayLam;
        this.gioVao = gioVao;
        this.gioRa = gioRa;
        this.tongGio = tongGio;
    }

    public int getId() { return id; }
    public int getIdNhanVien() { return idNhanVien; }
    public Date getNgayLam() { return ngayLam; }
    public Time getGioVao() { return gioVao; }
    public Time getGioRa() { return gioRa; }
    public double getTongGio() { return tongGio; }
}
