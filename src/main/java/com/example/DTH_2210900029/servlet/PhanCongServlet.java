package com.example.DTH_2210900029.servlet;

import com.example.DTH_2210900029.connection.DBConnection;
import com.example.DTH_2210900029.entity.PhanCong;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/PhanCongServlet")
public class PhanCongServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deletePhanCong(request, response);
                break;
            default:
                listPhanCong(request, response);
                break;
        }
    }

    private void listPhanCong(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<PhanCong> phanCongs = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT p.DTH_2210900029_ID_PhanCong, p.DTH_2210900029_ID_NV, p.DTH_2210900029_ID_CongViec, " +
                    "n.DTH_2210900029_Ten AS TenNhanVien, c.DTH_2210900029_TenCongViec AS TenCongViec, " +
                    "p.DTH_2210900029_NgayBatDau, p.DTH_2210900029_NgayKetThuc " +
                    "FROM DTH_2210900029_PhanCong p " +
                    "JOIN DTH_2210900029_NhanVien n ON p.DTH_2210900029_ID_NV = n.DTH_2210900029_ID_NV " +
                    "JOIN DTH_2210900029_CongViec c ON p.DTH_2210900029_ID_CongViec = c.DTH_2210900029_ID_CongViec";

            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                phanCongs.add(new PhanCong(
                        rs.getInt("DTH_2210900029_ID_PhanCong"),
                        rs.getInt("DTH_2210900029_ID_NV"),
                        rs.getInt("DTH_2210900029_ID_CongViec"),
                        rs.getString("TenNhanVien"),
                        rs.getString("TenCongViec"),
                        rs.getString("DTH_2210900029_NgayBatDau"),
                        rs.getString("DTH_2210900029_NgayKetThuc")
                ));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("phanCongs", phanCongs);
        request.getRequestDispatcher("phancong.jsp").forward(request, response);
    }


    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        PhanCong phanCong = null;

        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT * FROM DTH_2210900029_PhanCong WHERE ID=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                phanCong = new PhanCong(
                        rs.getInt("DTH_2210900029_ID_PhanCong"),
                        rs.getInt("DTH_2210900029_ID_NV"),
                        rs.getInt("DTH_2210900029_ID_CongViec"),
                        rs.getString("TenNhanVien"),
                        rs.getString("TenCongViec"),
                        rs.getString("DTH_2210900029_NgayBatDau"),
                        rs.getString("DTH_2210900029_NgayKetThuc")
                );
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("phanCong", phanCong);
        request.getRequestDispatcher("phancong_form.jsp").forward(request, response);
    }

    private void deletePhanCong(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "DELETE FROM DTH_2210900029_PhanCong WHERE DTH_2210900029_ID_PhanCong=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("PhanCongServlet");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int idNhanVien = Integer.parseInt(request.getParameter("idNhanVien"));
        int idCongViec = Integer.parseInt(request.getParameter("idCongViec"));
        String ngayPhanCong = request.getParameter("ngayPhanCong");

        try {
            Connection conn = DBConnection.getConnection();
            String sql;
            if (id == 0) {
                sql = "INSERT INTO DTH_2210900029_PhanCong (IDNhanVien, IDCongViec, NgayPhanCong) VALUES (?, ?, ?)";
            } else {
                sql = "UPDATE DTH_2210900029_PhanCong SET IDNhanVien=?, IDCongViec=?, NgayPhanCong=? WHERE ID=?";
            }
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idNhanVien);
            stmt.setInt(2, idCongViec);
            stmt.setString(3, ngayPhanCong);
            if (id != 0) stmt.setInt(4, id);
            stmt.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("PhanCongServlet");
    }
}
