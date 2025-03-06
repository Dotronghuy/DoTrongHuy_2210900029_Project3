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
        // Lấy thông tin đăng nhập từ form
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT * FROM DTH_2210900029_TaiKhoan WHERE DTH_2210900029_TenDangNhap = ? AND DTH_2210900029_MatKhau = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Nếu tìm thấy user, tạo session và chuyển hướng
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                response.sendRedirect("CongViecServlet");
            } else {
                // Nếu đăng nhập sai, chuyển về login.jsp với thông báo lỗi
                response.sendRedirect("login.jsp?error=1");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("login.jsp?error=2"); // Lỗi hệ thống
        }
    }
}
