package com.example.DTH_2210900029.servlet;

import com.example.DTH_2210900029.connection.DBConnection;
import com.example.DTH_2210900029.entity.HopDong;
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


@WebServlet("/HopDongServlet")
public class HopDongServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteHopDong(request, response);
                break;
            default:
                listHopDong(request, response);
                break;
        }
    }

    private void listHopDong(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<HopDong> hopDongs = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT * FROM DTH_2210900029_HopDong";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                hopDongs.add(new HopDong(
                        rs.getInt("DTH_2210900029_ID_HopDong"),
                        rs.getInt("DTH_2210900029_ID_NV"),
                        rs.getDate("DTH_2210900029_NgayBatDau"),
                        rs.getDate("DTH_2210900029_NgayKetThuc"),
                        rs.getString("DTH_2210900029_LoaiHopDong")
                ));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("hopDongs", hopDongs);
        request.getRequestDispatcher("hopDong.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        HopDong hopDong = null;

        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT * FROM DTH_2210900029_HopDong WHERE ID=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                hopDong = new HopDong(rs.getInt("ID"), rs.getInt("IDNhanVien"), rs.getDate("NgayBatDau"),
                        rs.getDate("NgayKetThuc"), rs.getString("LoaiHopDong"));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("hopDong", hopDong);
        request.getRequestDispatcher("hopDong_form.jsp").forward(request, response);
    }

    private void deleteHopDong(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "DELETE FROM DTH_2210900029_HopDong WHERE DTH_2210900029_ID_HopDong=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("HopDongServlet");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int idNhanVien = Integer.parseInt(request.getParameter("idNhanVien"));
        String ngayBatDauStr = request.getParameter("ngayBatDau");
        String ngayKetThucStr = request.getParameter("ngayKetThuc");
        String loaiHopDong = request.getParameter("loaiHopDong");

        try {
            Connection conn = DBConnection.getConnection();
            String sql;
            if (id == 0) {
                sql = "INSERT INTO DTH_2210900029_HopDong (IDNhanVien, NgayBatDau, NgayKetThuc, LoaiHopDong) VALUES (?, ?, ?, ?)";
            } else {
                sql = "UPDATE DTH_2210900029_HopDong SET IDNhanVien=?, NgayBatDau=?, NgayKetThuc=?, LoaiHopDong=? WHERE ID=?";
            }
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idNhanVien);
            stmt.setString(2, ngayBatDauStr);
            stmt.setString(3, ngayKetThucStr);
            stmt.setString(4, loaiHopDong);
            if (id != 0) stmt.setInt(5, id);
            stmt.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("HopDongServlet");
    }

    private class List<T> {
    }
}

