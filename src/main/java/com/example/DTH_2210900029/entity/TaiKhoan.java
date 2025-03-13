package com.example.DTH_2210900029.entity;

public class TaiKhoan {
    private String user;
    private String password;
    private String vaiTro;


    public TaiKhoan(String user, String password, String vaiTro) {
        this.user = user;
        this.password = password;
        this.vaiTro = vaiTro;
    }

    public TaiKhoan(){}

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
    }
}
