package com.example.DTH_2210900029.servlet;

import com.example.DTH_2210900029.connection.DBConnection;
import com.example.DTH_2210900029.entity.ChamCong;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ChamCongServlet")
public class ChamCongServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteChamCong(request, response);
                break;
            default:
                listChamCong(request, response);
                break;
        }
    }

    private void listChamCong(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<ChamCong> chamCongs = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM DTH_2210900029_ChamCong";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                chamCongs.add(new ChamCong(
                        rs.getInt("DTH_2210900029_ID_ChamCong"),
                        rs.getInt("DTH_2210900029_ID_NV"),
                        rs.getDate("DTH_2210900029_NgayLamViec"),
                        rs.getInt("DTH_2210900029_TongGio")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("chamCongs", chamCongs);
        request.getRequestDispatcher("chamCong.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        ChamCong chamCong = null;

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM DTH_2210900029_ChamCong WHERE DTH_2210900029_ID_ChamCong=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                chamCong = new ChamCong(
                        rs.getInt("DTH_2210900029_ID_ChamCong"),
                        rs.getInt("DTH_2210900029_ID_NV"),
                        rs.getDate("DTH_2210900029_NgayLamViec"),
                        rs.getInt("DTH_2210900029_TongGio")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("chamCong", chamCong);
        request.getRequestDispatcher("chamCong_form.jsp").forward(request, response);
    }

    private void deleteChamCong(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "DELETE FROM DTH_2210900029_ChamCong WHERE DTH_2210900029_ID_ChamCong=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("ChamCongServlet");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        int id = (idStr == null || idStr.isEmpty()) ? 0 : Integer.parseInt(idStr);
        int idNhanVien = Integer.parseInt(request.getParameter("idNhanVien"));
        int soGioLam = Integer.parseInt(request.getParameter("soGioLam"));
        String ngayLamStr = request.getParameter("ngayLam");

        try {
            // Chuyển đổi từ String sang java.sql.Date
            java.sql.Date ngayLam = java.sql.Date.valueOf(ngayLamStr);

            Connection conn = DBConnection.getConnection();
            String sql;
            if (id == 0) {
                sql = "INSERT INTO DTH_2210900029_ChamCong (DTH_2210900029_ID_NV, DTH_2210900029_NgayLamViec, DTH_2210900029_TongGio) VALUES (?, ?, ?)";
            } else {
                sql = "UPDATE DTH_2210900029_ChamCong SET DTH_2210900029_ID_NV=?, DTH_2210900029_NgayLamViec=?, DTH_2210900029_TongGio=? WHERE DTH_2210900029_ID_ChamCong=?";
            }
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idNhanVien);
            stmt.setDate(2, ngayLam);
            stmt.setInt(3, soGioLam);
            if (id != 0) stmt.setInt(4, id);
            stmt.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("ChamCongServlet");
    }
}
