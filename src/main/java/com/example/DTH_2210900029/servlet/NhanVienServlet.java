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


@WebServlet(value = "/NhanVienServlet")
public class NhanVienServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=DTH_2210900029_QLNV;encrypt=false";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "123456";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String vaiTro = (String) request.getSession().getAttribute("vaiTro");

        if (vaiTro == null || (!"Admin".equals(vaiTro) )) {
            response.sendRedirect("403.jsp?error=1");
            return;
        }

        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            deleteNhanVien(request, response);
            return;
        } else if ("edit".equals(action)) {
            editNhanVien(request, response);
            return;
        }

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

        request.setAttribute("nhanViens", nhanViens);
        request.getRequestDispatcher("/nhanvien.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");

        if ("add".equals(action)) {
            addNhanVien(request, response);
        } else if ("update".equals(action)) {
            updateNhanVien(request, response);
        } else {
            response.sendRedirect("NhanVienServlet");
        }
    }

    private void addNhanVien(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            String hoTen = request.getParameter("hoTen");
            Date ngaySinh = Date.valueOf(request.getParameter("ngaySinh"));
            String gioiTinh = request.getParameter("gioiTinh");
            String sdt = request.getParameter("sdt");
            String email = request.getParameter("email");
            String chucVu = request.getParameter("chucVu");

            Connection conn = DBConnection.getConnection();
            String sql = "INSERT INTO DTH_2210900029_NhanVien (DTH_2210900029_HoTen, DTH_2210900029_NgaySinh, " +
                    "DTH_2210900029_GioiTinh, DTH_2210900029_SDT, DTH_2210900029_Email, DTH_2210900029_ChucVu) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, hoTen);
            stmt.setDate(2, ngaySinh);
            stmt.setString(3, gioiTinh);
            stmt.setString(4, sdt);
            stmt.setString(5, email);
            stmt.setString(6, chucVu);
            stmt.executeUpdate();

            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("NhanVienServlet");
    }

    private void editNhanVien(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT DTH_2210900029_ID_NV, DTH_2210900029_HoTen, DTH_2210900029_NgaySinh, " +
                    "DTH_2210900029_GioiTinh, DTH_2210900029_SDT, DTH_2210900029_Email, DTH_2210900029_ChucVu " +
                    "FROM DTH_2210900029_NhanVien WHERE DTH_2210900029_ID_NV=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                NhanVien nv = new NhanVien(
                        rs.getInt("DTH_2210900029_ID_NV"),
                        rs.getString("DTH_2210900029_HoTen"),
                        rs.getDate("DTH_2210900029_NgaySinh"),
                        rs.getString("DTH_2210900029_GioiTinh"),
                        rs.getString("DTH_2210900029_SDT"),
                        rs.getString("DTH_2210900029_Email"),
                        rs.getString("DTH_2210900029_ChucVu")
                );
                request.setAttribute("nhanVien", nv);
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("/nhanvien_form.jsp").forward(request, response);
    }

    private void updateNhanVien(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String hoTen = request.getParameter("hoTen");
            Date ngaySinh = Date.valueOf(request.getParameter("ngaySinh"));
            String gioiTinh = request.getParameter("gioiTinh");
            String sdt = request.getParameter("sdt");
            String email = request.getParameter("email");
            String chucVu = request.getParameter("chucVu");

            System.out.println("Dữ liệu cập nhật: ID=" + id + ", Họ Tên=" + hoTen);

            Connection conn = DBConnection.getConnection();
            String sql = "UPDATE DTH_2210900029_NhanVien SET DTH_2210900029_HoTen=?, DTH_2210900029_NgaySinh=?, " +
                    "DTH_2210900029_GioiTinh=?, DTH_2210900029_SDT=?, DTH_2210900029_Email=?, DTH_2210900029_ChucVu=? " +
                    "WHERE DTH_2210900029_ID_NV=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, hoTen);
            stmt.setDate(2, ngaySinh);
            stmt.setString(3, gioiTinh);
            stmt.setString(4, sdt);
            stmt.setString(5, email);
            stmt.setString(6, chucVu);
            stmt.setInt(7, id);

            int rowsUpdated = stmt.executeUpdate();
            System.out.println("Số dòng cập nhật: " + rowsUpdated);

            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("NhanVienServlet");
    }



    private void deleteNhanVien(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "DELETE FROM DTH_2210900029_NhanVien WHERE DTH_2210900029_ID_NV=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();

            stmt.close();
            conn.close();

            if (rowsDeleted > 0) {
                System.out.println("Xóa nhân viên thành công!");
            } else {
                System.out.println("Không tìm thấy nhân viên cần xóa!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("NhanVienServlet");
    }

}

