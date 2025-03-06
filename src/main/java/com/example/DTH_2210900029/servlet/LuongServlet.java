package com.example.DTH_2210900029.servlet;

import com.example.DTH_2210900029.connection.DBConnection;
import com.example.DTH_2210900029.entity.Luong;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/LuongServlet")
public class LuongServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "edit":
                showEditForm(request, response);
                break;
            default:
                listLuong(request, response);
                break;
        }
    }

    private void listLuong(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Luong> luongs = new ArrayList<>();
        String sql = "SELECT * FROM DTH_2210900029_Luong";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                luongs.add(new Luong(
                        rs.getInt("DTH_2210900029_ID_BangLuong"),
                        rs.getInt("DTH_2210900029_ID_NV"),
                        rs.getBigDecimal("DTH_2210900029_LuongCoBan"),
                        rs.getBigDecimal("DTH_2210900029_Thuong"),
                        rs.getBigDecimal("DTH_2210900029_PhuCap"),
                        rs.getBigDecimal("DTH_2210900029_KhauTru"),
                        rs.getBigDecimal("DTH_2210900029_TongLuong"),
                        rs.getInt("DTH_2210900029_Thang"),
                        rs.getInt("DTH_2210900029_Nam"),
                        rs.getBigDecimal("DTH_2210900029_TongGioLam")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("luongs", luongs);
        request.getRequestDispatcher("luong.jsp").forward(request, response);
    }


    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Luong luong = null;

        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT * FROM DTH_2210900029_Luong WHERE DTH_2210900029_ID_BangLuong=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                luong = new Luong(
                        rs.getInt("DTH_2210900029_ID_BangLuong"),
                        rs.getInt("DTH_2210900029_ID_NV"),
                        rs.getBigDecimal("DTH_2210900029_LuongCoBan"),
                        rs.getBigDecimal("DTH_2210900029_Thuong"),
                        rs.getBigDecimal("DTH_2210900029_PhuCap"),
                        rs.getBigDecimal("DTH_2210900029_KhauTru"),
                        rs.getBigDecimal("DTH_2210900029_TongLuong"),
                        rs.getInt("DTH_2210900029_Thang"),
                        rs.getInt("DTH_2210900029_Nam"),
                        rs.getBigDecimal("DTH_2210900029_TongGioLam")
                );
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("luong", luong);
        request.getRequestDispatcher("luong_form.jsp").forward(request, response);
    }



    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int idNhanVien = Integer.parseInt(request.getParameter("idNhanVien"));
        BigDecimal luongCoBan = new BigDecimal(request.getParameter("luongCoBan"));
        BigDecimal thuong = new BigDecimal(request.getParameter("thuong"));
        BigDecimal phuCap = new BigDecimal(request.getParameter("phuCap"));
        BigDecimal khauTru = new BigDecimal(request.getParameter("khauTru"));
        BigDecimal tongGioLam = new BigDecimal(request.getParameter("tongGioLam"));
        int thang = Integer.parseInt(request.getParameter("thang"));
        int nam = Integer.parseInt(request.getParameter("nam"));

        BigDecimal tongLuong = (luongCoBan.add(thuong).add(phuCap)).subtract(khauTru);

        try (Connection conn = DBConnection.getConnection()) {
            String sql;
            if (id == 0) {
                sql = "INSERT INTO DTH_2210900029_Luong " +
                        "(DTH_2210900029_ID_NV, DTH_2210900029_LuongCoBan, DTH_2210900029_Thuong, " +
                        "DTH_2210900029_PhuCap, DTH_2210900029_KhauTru, DTH_2210900029_TongLuong, " +
                        "DTH_2210900029_Thang, DTH_2210900029_Nam, DTH_2210900029_TongGioLam) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            } else {
                sql = "UPDATE DTH_2210900029_Luong SET " +
                        "DTH_2210900029_ID_NV=?, DTH_2210900029_LuongCoBan=?, DTH_2210900029_Thuong=?, " +
                        "DTH_2210900029_PhuCap=?, DTH_2210900029_KhauTru=?, DTH_2210900029_TongLuong=?, " +
                        "DTH_2210900029_Thang=?, DTH_2210900029_Nam=?, DTH_2210900029_TongGioLam=? " +
                        "WHERE DTH_2210900029_ID_BangLuong=?";
            }

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, idNhanVien);
                stmt.setBigDecimal(2, luongCoBan);
                stmt.setBigDecimal(3, thuong);
                stmt.setBigDecimal(4, phuCap);
                stmt.setBigDecimal(5, khauTru);
                stmt.setBigDecimal(6, tongLuong);
                stmt.setInt(7, thang);
                stmt.setInt(8, nam);
                stmt.setBigDecimal(9, tongGioLam);
                if (id != 0) stmt.setInt(10, id);
                stmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("LuongServlet");
    }

}

