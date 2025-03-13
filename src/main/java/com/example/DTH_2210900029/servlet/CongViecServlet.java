package com.example.DTH_2210900029.servlet;

import com.example.DTH_2210900029.connection.DBConnection;
import com.example.DTH_2210900029.entity.CongViec;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/CongViecServlet")
public class CongViecServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<CongViec> congViecs = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT DTH_2210900029_ID_CongViec, DTH_2210900029_TenCongViec, DTH_2210900029_MoTa, DTH_2210900029_NgayGiao, DTH_2210900029_HanHoanThanh, DTH_2210900029_TrangThai FROM DTH_2210900029_CongViec");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                congViecs.add(new CongViec(
                        rs.getInt("DTH_2210900029_ID_CongViec"),
                        rs.getString("DTH_2210900029_TenCongViec"),
                        rs.getString("DTH_2210900029_MoTa"),
                        rs.getDate("DTH_2210900029_NgayGiao"),
                        rs.getDate("DTH_2210900029_HanHoanThanh"),
                        rs.getString("DTH_2210900029_TrangThai")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("congViecs", congViecs);
        request.getRequestDispatcher("congviec.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            addCongViec(request, response);
        } else if ("delete".equals(action)) {
            deleteCongViec(request, response);
        } else if ("edit".equals(action)) {
            editCongViec(request, response);
        } else {
            response.sendRedirect("CongViecServlet");
        }
    }
    private void editCongViec(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idCongViec = Integer.parseInt(request.getParameter("id"));
        String tenCongViec = request.getParameter("tenCongViec");
        String moTa = request.getParameter("moTa");
        String ngayGiaoStr = request.getParameter("ngayGiao");
        String hanHoanThanhStr = request.getParameter("hanHoanThanh");
        String trangThai = request.getParameter("trangThai");

        Date ngayGiao = (ngayGiaoStr != null && !ngayGiaoStr.isEmpty()) ? Date.valueOf(ngayGiaoStr) : null;
        Date hanHoanThanh = (hanHoanThanhStr != null && !hanHoanThanhStr.isEmpty()) ? Date.valueOf(hanHoanThanhStr) : null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "UPDATE DTH_2210900029_CongViec " +
                             "SET DTH_2210900029_TenCongViec=?, DTH_2210900029_MoTa=?, DTH_2210900029_NgayGiao=?, " +
                             "DTH_2210900029_HanHoanThanh=?, DTH_2210900029_TrangThai=? WHERE DTH_2210900029_ID_CongViec=?")) {

            stmt.setString(1, tenCongViec);
            stmt.setString(2, moTa);
            stmt.setDate(3, ngayGiao);
            stmt.setDate(4, hanHoanThanh);
            stmt.setString(5, trangThai);
            stmt.setInt(6, idCongViec);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Cập nhật công việc thành công!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("CongViecServlet");
    }
    private void addCongViec(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String tenCongViec = request.getParameter("tenCongViec");
        String moTa = request.getParameter("moTa");
        String ngayGiaoStr = request.getParameter("ngayGiao");
        String hanHoanThanhStr = request.getParameter("hanHoanThanh");
        String trangThai = request.getParameter("trangThai");

        Date ngayGiao = (ngayGiaoStr != null && !ngayGiaoStr.isEmpty()) ? Date.valueOf(ngayGiaoStr) : null;
        Date hanHoanThanh = (hanHoanThanhStr != null && !hanHoanThanhStr.isEmpty()) ? Date.valueOf(hanHoanThanhStr) : null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO DTH_2210900029_CongViec (DTH_2210900029_TenCongViec, DTH_2210900029_MoTa, DTH_2210900029_NgayGiao, DTH_2210900029_HanHoanThanh, DTH_2210900029_TrangThai) VALUES (?, ?, ?, ?, ?)")) {

            stmt.setString(1, tenCongViec);
            stmt.setString(2, moTa);
            stmt.setDate(3, ngayGiao);
            stmt.setDate(4, hanHoanThanh);
            stmt.setString(5, trangThai);

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("CongViecServlet");
    }
    private void deleteCongViec(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idCongViec = Integer.parseInt(request.getParameter("id"));

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM DTH_2210900029_CongViec WHERE DTH_2210900029_ID_CongViec = ?")) {

            stmt.setInt(1, idCongViec);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("CongViecServlet");
    }
}
