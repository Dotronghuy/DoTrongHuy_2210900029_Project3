package com.example.DTH_2210900029.entity;

import java.sql.Date;

public class CongViec {
    private int id;
    private String tenCongViec;
    private String moTa;
    private Date ngayGiao;
    private Date hanHoanThanh;
    private String trangThai;

    public CongViec(int id, String tenCongViec, String moTa, Date ngayGiao, Date hanHoanThanh, String trangThai) {
        this.id = id;
        this.tenCongViec = tenCongViec;
        this.moTa = moTa;
        this.ngayGiao = ngayGiao;
        this.hanHoanThanh = hanHoanThanh;
        this.trangThai = trangThai;
    }

    // Getter và Setter
    public int getId() { return id; }
    public String getTenCongViec() { return tenCongViec; }
    public String getMoTa() { return moTa; }
    public Date getNgayGiao() { return ngayGiao; }
    public Date getHanHoanThanh() { return hanHoanThanh; }
    public String getTrangThai() { return trangThai; }

    // Hàm để lấy class CSS theo trạng thái
    public String getTrangThaiCss() {
        switch (trangThai.toLowerCase()) {
            case "đang thực hiện": return "in-progress";
            case "hoàn thành": return "completed";
            case "quá hạn": return "overdue";
            default: return "unknown";
        }
    }
}
