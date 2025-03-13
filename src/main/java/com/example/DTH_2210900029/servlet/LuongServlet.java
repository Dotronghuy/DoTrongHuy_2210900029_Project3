package com.example.DTH_2210900029.servlet;

import com.example.DTH_2210900029.connection.DBConnection;
import com.example.DTH_2210900029.entity.Luong;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/LuongServlet")
public class LuongServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String vaiTro = (String) request.getSession().getAttribute("vaiTro");

        if (vaiTro == null || (!"Admin".equals(vaiTro) )) {
            response.sendRedirect("403.jsp?error=1");
            return;
        }


        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "edit":
                showEditForm(request, response);
                break;
            default:
                listLuong(request, response);
                break;
        }
    }

    private void listLuong(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Luong> luongs = new ArrayList<>();
        String sql = "SELECT * FROM DTH_2210900029_Luong";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Luong luong = new Luong(
                        rs.getInt("DTH_2210900029_ID_BangLuong"),
                        rs.getInt("DTH_2210900029_ID_NV"),
                        rs.getBigDecimal("DTH_2210900029_LuongCoBan"),
                        rs.getBigDecimal("DTH_2210900029_PhuCap"),
                        rs.getBigDecimal("DTH_2210900029_KhauTru"),
                        rs.getInt("DTH_2210900029_Thang"),
                        rs.getInt("DTH_2210900029_Nam"),
                        rs.getBigDecimal("DTH_2210900029_TongGioLam")
                );
                luongs.add(luong);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Số bản ghi lấy được: " + luongs.size());
        for (Luong l : luongs) {
            System.out.println(l.getId() + " - " + l.getIdNhanVien() + " - " + l.getLuongCoBan());
        }

        request.setAttribute("luongs", luongs);
        request.getRequestDispatcher("luong.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Luong luong = null;

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM DTH_2210900029_Luong WHERE DTH_2210900029_ID_BangLuong=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                luong = new Luong(
                        rs.getInt("DTH_2210900029_ID_BangLuong"),
                        rs.getInt("DTH_2210900029_ID_NV"),
                        rs.getBigDecimal("DTH_2210900029_LuongCoBan"),
                        rs.getBigDecimal("DTH_2210900029_PhuCap"),
                        rs.getBigDecimal("DTH_2210900029_KhauTru"),
                        rs.getInt("DTH_2210900029_Thang"),
                        rs.getInt("DTH_2210900029_Nam"),
                        rs.getBigDecimal("DTH_2210900029_TongGioLam")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (luong != null) {
            request.setAttribute("luong", luong);
            request.getRequestDispatcher("luong.jsp").forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Không tìm thấy bản ghi lương");
        }
    }





    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = parseIntWithDefault(request.getParameter("id"));
            int idNhanVien = parseIntWithDefault(request.getParameter("idNhanVien"));
            BigDecimal luongCoBan = parseBigDecimalWithDefault(request.getParameter("luongCoBan"));
            BigDecimal phuCap = parseBigDecimalWithDefault(request.getParameter("phuCap"));
            BigDecimal khauTru = parseBigDecimalWithDefault(request.getParameter("khauTru"));
            BigDecimal tongGioLam = parseBigDecimalWithDefault(request.getParameter("tongGioLam"));
            int thang = parseIntWithDefault(request.getParameter("thang"));
            int nam = parseIntWithDefault(request.getParameter("nam"));

            System.out.println("ID: " + id);
            System.out.println("ID Nhân viên: " + idNhanVien);
            System.out.println("Lương cơ bản: " + luongCoBan);
            System.out.println("Phụ cấp: " + phuCap);
            System.out.println("Khấu trừ: " + khauTru);
            System.out.println("Tổng giờ làm: " + tongGioLam);
            System.out.println("Tháng: " + thang);
            System.out.println("Năm: " + nam);

            if (!isNhanVienExist(idNhanVien)) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID nhân viên không tồn tại!");
                return;
            }

            try (Connection conn = DBConnection.getConnection()) {
                String sql;
                if (id == 0) {
                    sql = "INSERT INTO DTH_2210900029_Luong " +
                            "(DTH_2210900029_ID_NV, DTH_2210900029_LuongCoBan, " +
                            "DTH_2210900029_PhuCap, DTH_2210900029_KhauTru, " +
                            "DTH_2210900029_Thang, DTH_2210900029_Nam, DTH_2210900029_TongGioLam) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?)";
                } else {
                    sql = "UPDATE DTH_2210900029_Luong SET " +
                            "DTH_2210900029_ID_NV=?, DTH_2210900029_LuongCoBan=?, " +
                            "DTH_2210900029_PhuCap=?, DTH_2210900029_KhauTru=?,  " +
                            "DTH_2210900029_Thang=?, DTH_2210900029_Nam=?, DTH_2210900029_TongGioLam=? " +
                            "WHERE DTH_2210900029_ID_BangLuong=?";
                }

                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setInt(1, idNhanVien);
                    stmt.setBigDecimal(2, luongCoBan);
                    stmt.setBigDecimal(3, phuCap);
                    stmt.setBigDecimal(4, khauTru);
                    stmt.setInt(5, thang);
                    stmt.setInt(6, nam);
                    stmt.setBigDecimal(7, tongGioLam);
                    if (id != 0) stmt.setInt(8, id);
                    stmt.executeUpdate();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Có lỗi xảy ra trong quá trình xử lý yêu cầu.");
        }

        response.sendRedirect("LuongServlet");
    }

    private int parseIntWithDefault(String value) {
        if (value == null || value.trim().isEmpty()) {
            return 0;
        }
        return Integer.parseInt(value);
    }

    private BigDecimal parseBigDecimalWithDefault(String value) {
        if (value == null || value.trim().isEmpty()) {
            return BigDecimal.ZERO;
        }
        return new BigDecimal(value);
    }


    private boolean isNhanVienExist(int idNhanVien) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT 1 FROM DTH_2210900029_NhanVien WHERE DTH_2210900029_ID_NV = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, idNhanVien);
                ResultSet rs = stmt.executeQuery();

                System.out.println("Kiểm tra ID nhân viên: " + idNhanVien);
                if (rs.next()) {
                    System.out.println("Nhân viên tồn tại.");
                    return true;
                } else {
                    System.out.println("Nhân viên không tồn tại.");
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }





}

