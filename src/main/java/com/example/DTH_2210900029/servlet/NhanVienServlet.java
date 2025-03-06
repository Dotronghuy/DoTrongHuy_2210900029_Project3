package com.example.DTH_2210900029.servlet;

import com.example.DTH_2210900029.connection.DBConnection;
import com.example.DTH_2210900029.entity.NhanVien;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


// Định nghĩa Servlet với URL mapping
@WebServlet(value = "/NhanVienServlet")
public class NhanVienServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Thông tin kết nối SQL Server
    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=DTH_2210900029_QLNV;encrypt=false";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "123456";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<NhanVien> nhanViens = new ArrayList<>();

        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT DTH_2210900029_ID_NV, DTH_2210900029_HoTen, DTH_2210900029_NgaySinh, " +
                    "DTH_2210900029_GioiTinh, DTH_2210900029_SDT, DTH_2210900029_Email, DTH_2210900029_ChucVu " +
                    "FROM DTH_2210900029_NhanVien";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                NhanVien nv = new NhanVien(
                        rs.getInt("DTH_2210900029_ID_NV"),
                        rs.getString("DTH_2210900029_HoTen"),
                        rs.getDate("DTH_2210900029_NgaySinh"),
                        rs.getString("DTH_2210900029_GioiTinh"),
                        rs.getString("DTH_2210900029_SDT"),
                        rs.getString("DTH_2210900029_Email"),
                        rs.getString("DTH_2210900029_ChucVu")
                );
                nhanViens.add(nv);
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Gửi danh sách nhân viên đến JSP
        request.setAttribute("nhanViens", nhanViens);
        request.getRequestDispatcher("/nhanvien.jsp").forward(request, response);
    }

    private void deleteNhanVien(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "DELETE FROM DTH_2210900029_NhanVien WHERE DTH_2210900029_ID_NV=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("NhanVienServlet");
    }
}

