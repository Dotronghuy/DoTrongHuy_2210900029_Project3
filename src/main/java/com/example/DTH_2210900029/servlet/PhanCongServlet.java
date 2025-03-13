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
            case "fetchEmployeeName":
                fetchEmployeeName(request, response);
                break;
            case "fetchJobName":
                fetchJobName(request, response);
                break;
            default:
                listPhanCong(request, response);
                break;
        }
    }
    private void fetchEmployeeName(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idNhanVien = request.getParameter("idNhanVien");
        String tenNhanVien = null;

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT DTH_2210900029_HoTen FROM DTH_2210900029_NhanVien WHERE DTH_2210900029_ID_NV = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, idNhanVien);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                tenNhanVien = rs.getString("DTH_2210900029_HoTen");
            }

            response.setContentType("application/json");
            response.getWriter().write("{\"tenNhanVien\": \"" + (tenNhanVien != null ? tenNhanVien : "") + "\"}");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fetchJobName(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idCongViec = request.getParameter("idCongViec");
        String tenCongViec = null;

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT DTH_2210900029_TenCongViec FROM DTH_2210900029_CongViec WHERE DTH_2210900029_ID_CongViec = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, idCongViec);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                tenCongViec = rs.getString("DTH_2210900029_TenCongViec");
            }

            response.setContentType("application/json");
            response.getWriter().write("{\"tenCongViec\": \"" + (tenCongViec != null ? tenCongViec : "") + "\"}");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void listPhanCong(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<PhanCong> phanCongs = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT p.DTH_2210900029_ID_PhanCong, p.DTH_2210900029_ID_NV, p.DTH_2210900029_ID_CongViec, " +
                    "p.DTH_2210900029_NgayBatDau, p.DTH_2210900029_NgayKetThuc, " +
                    "n.DTH_2210900029_HoTen AS TenNhanVien, " +
                    "c.DTH_2210900029_TenCongViec AS TenCongViec " +
                    "FROM DTH_2210900029_PhanCong p " +
                    "JOIN DTH_2210900029_NhanVien n ON p.DTH_2210900029_ID_NV = n.DTH_2210900029_ID_NV " +
                    "JOIN DTH_2210900029_CongViec c ON p.DTH_2210900029_ID_CongViec = c.DTH_2210900029_ID_CongViec";

            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                phanCongs.add(new PhanCong(
                        rs.getInt("DTH_2210900029_ID_PhanCong"),
                        rs.getInt("DTH_2210900029_ID_NV"),
                        rs.getString("TenNhanVien"),
                        rs.getInt("DTH_2210900029_ID_CongViec"),
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
        Connection conn = null;

        try {
            conn = DBConnection.getConnection();
            String sql = "SELECT * FROM DTH_2210900029_PhanCong WHERE DTH_2210900029_ID_PhanCong=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                phanCong = new PhanCong(
                        rs.getInt("DTH_2210900029_ID_PhanCong"),
                        rs.getInt("DTH_2210900029_ID_NV"),
                        rs.getString("TenNhanVien"),
                        rs.getInt("DTH_2210900029_ID_CongViec"),
                        rs.getString("TenCongViec"),
                        rs.getString("DTH_2210900029_NgayBatDau"),
                        rs.getString("DTH_2210900029_NgayKetThuc")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        request.setAttribute("phanCong", phanCong);
        request.getRequestDispatcher("phancong.jsp").forward(request, response);
    }

    private void deletePhanCong(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "DELETE FROM DTH_2210900029_PhanCong WHERE DTH_2210900029_ID_PhanCong=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        response.sendRedirect("PhanCongServlet");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idNhanVien = request.getParameter("idNhanVien");
        String idCongViec = request.getParameter("idCongViec");
        String ngayBatDau = request.getParameter("ngayBatDau");
        String ngayKetThuc = request.getParameter("ngayKetThuc");

        if (idNhanVien == null || idCongViec == null || ngayBatDau == null || ngayKetThuc == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Thông tin không đầy đủ.");
            return;
        }

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO DTH_2210900029_PhanCong (DTH_2210900029_ID_NV, DTH_2210900029_ID_CongViec, " +
                    "DTH_2210900029_NgayBatDau, DTH_2210900029_NgayKetThuc) VALUES (?, ?, ?, ?)";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, idNhanVien);
            stmt.setString(2, idCongViec);
            stmt.setString(3, ngayBatDau);
            stmt.setString(4, ngayKetThuc);

            stmt.executeUpdate();

            response.sendRedirect("PhanCongServlet");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Có lỗi xảy ra khi thêm phân công.");
        }
    }

}
