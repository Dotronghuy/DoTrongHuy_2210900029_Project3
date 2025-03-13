package com.example.DTH_2210900029.servlet;

import com.example.DTH_2210900029.connection.DBConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT * FROM DTH_2210900029_TaiKhoan WHERE DTH_2210900029_TenDangNhap = ? AND DTH_2210900029_MatKhau = ?")) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int idNhanVien = rs.getInt("DTH_2210900029_ID_NV");
                String vaiTro = rs.getString("DTH_2210900029_VaiTro");

                HttpSession session = request.getSession();
                System.out.println("ID Nhân viên từ DB: " + idNhanVien);
                session.setAttribute("idNhanVien", idNhanVien);
                session.setAttribute("username", username);
                session.setAttribute("vaiTro", vaiTro);

                if ("Admin".equals(vaiTro)) {
                    response.sendRedirect("NhanVienServlet");
                } else if ("Nhân viên".equals(vaiTro)) {
                    response.sendRedirect("ChamCongServlet");
                } else {
                    response.sendRedirect("403.jsp?error=3");
                }
            } else {
                response.sendRedirect("403.jsp?error=1");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("403.jsp?error=2");
        }
    }

}
