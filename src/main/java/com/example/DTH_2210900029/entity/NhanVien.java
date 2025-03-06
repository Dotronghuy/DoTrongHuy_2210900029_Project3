package com.example.DTH_2210900029.entity;

import java.util.Date;

public class NhanVien {
    private int id;
    private String hoTen;
    private Date ngaySinh;
    private String gioiTinh;
    private String sdt;
    private String email;
    private String chucVu;

    // Constructor đầy đủ
    public NhanVien(int id, String hoTen, Date ngaySinh, String gioiTinh, String sdt, String email, String chucVu) {
        this.id = id;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.sdt = sdt;
        this.email = email;
        this.chucVu = chucVu;
    }

    public int getId() { return id; }
    public String getHoTen() { return hoTen; }
    public Date getNgaySinh() { return ngaySinh; }
    public String getGioiTinh() { return gioiTinh; }
    public String getSdt() { return sdt; }
    public String getEmail() { return email; }
    public String getChucVu() { return chucVu; }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }
}
