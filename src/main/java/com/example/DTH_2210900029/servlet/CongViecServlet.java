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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/CongViecServlet")
public class CongViecServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<CongViec> congViecs = new ArrayList<>();

        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT DTH_2210900029_ID_CongViec, DTH_2210900029_TenCongViec, DTH_2210900029_MoTa, DTH_2210900029_NgayGiao, DTH_2210900029_HanHoanThanh, DTH_2210900029_TrangThai FROM DTH_2210900029_CongViec";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

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

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("congViecs", congViecs);
        request.getRequestDispatcher("congviec.jsp").forward(request, response);
    }
}
