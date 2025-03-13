package com.example.DTH_2210900029.servlet;

import com.example.DTH_2210900029.connection.DBConnection;
import com.example.DTH_2210900029.entity.ChamCong;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ChamCongServlet")
public class ChamCongServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        String idNhanVienStr = request.getParameter("idNhanVien");
        if (idNhanVienStr != null && !idNhanVienStr.trim().isEmpty()) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            int idNhanVien = Integer.parseInt(idNhanVienStr);
            try (Connection conn = DBConnection.getConnection()) {
                String sql = "SELECT DTH_2210900029_GioVao, DTH_2210900029_GioRa " +
                        "FROM DTH_2210900029_ChamCong WHERE DTH_2210900029_ID_NV = ? " +
                        "AND DTH_2210900029_NgayLamViec = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, idNhanVien);
                stmt.setDate(2, new java.sql.Date(System.currentTimeMillis()));
                ResultSet rs = stmt.executeQuery();

                JSONObject jsonResponse = new JSONObject();
                if (rs.next()) {
                    jsonResponse.put("gioVao", rs.getString("DTH_2210900029_GioVao"));
                    jsonResponse.put("gioRa", rs.getString("DTH_2210900029_GioRa") != null ? rs.getString("DTH_2210900029_GioRa") : "Chưa chấm");
                } else {
                    jsonResponse.put("message", "Chưa có dữ liệu chấm công hôm nay");
                }
                response.getWriter().write(jsonResponse.toString());
            } catch (SQLException e) {
                e.printStackTrace();
                response.getWriter().write("{\"status\": \"error\", \"message\": \"Lỗi server\"}");
            }
            return;
        }

        if (session == null || session.getAttribute("idNhanVien") == null) {
            System.out.println("Session không tồn tại hoặc idNhanVien là null");
            response.sendRedirect("index.jsp");
            return;
        }

        int idNhanVien = (Integer) session.getAttribute("idNhanVien");
        request.setAttribute("idNhanVien", idNhanVien);

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
                        rs.getTime("DTH_2210900029_GioVao"),
                        rs.getTime("DTH_2210900029_GioRa"),
                        rs.getDouble("DTH_2210900029_TongGio")
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
                        rs.getTime("DTH_2210900029_GioVao"),
                        rs.getTime("DTH_2210900029_GioRa"),
                        rs.getDouble("DTH_2210900029_TongGio")
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String idNhanVienStr = request.getParameter("idNhanVien");
        String ngayLam = request.getParameter("ngayLam");
        String gioHienTai = request.getParameter("gioHienTai");

        if (idNhanVienStr == null || idNhanVienStr.trim().isEmpty()) {
            response.getWriter().write("{\"status\": \"error\", \"message\": \"Thiếu idNhanVien\"}");
            return;
        }

        int idNhanVien = Integer.parseInt(idNhanVienStr);
        try (Connection conn = DBConnection.getConnection()) {
            String checkSql = "SELECT DTH_2210900029_ID_ChamCong, DTH_2210900029_GioVao, DTH_2210900029_GioRa FROM DTH_2210900029_ChamCong WHERE DTH_2210900029_ID_NV = ? AND DTH_2210900029_NgayLamViec = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setInt(1, idNhanVien);
            checkStmt.setString(2, ngayLam);
            ResultSet rs = checkStmt.executeQuery();

            JSONObject jsonResponse = new JSONObject();
            if (rs.next()) {
                int idChamCong = rs.getInt("DTH_2210900029_ID_ChamCong");
                String existingGioVao = rs.getString("DTH_2210900029_GioVao");
                String existingGioRa = rs.getString("DTH_2210900029_GioRa");

                if (existingGioVao != null && existingGioRa == null) {
                    String updateSql = "UPDATE DTH_2210900029_ChamCong SET DTH_2210900029_GioRa = ?, DTH_2210900029_TongGio = DATEDIFF(SECOND, DTH_2210900029_GioVao, ?) / 3600 WHERE DTH_2210900029_ID_ChamCong = ?";
                    PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                    updateStmt.setString(1, gioHienTai);
                    updateStmt.setString(2, gioHienTai);
                    updateStmt.setInt(3, idChamCong);
                    updateStmt.executeUpdate();
                    jsonResponse.put("gioRa", gioHienTai);
                    jsonResponse.put("message", "Cập nhật giờ ra thành công");
                } else {
                    jsonResponse.put("message", "Bạn đã chấm công trước đó!");
                }
            } else {
                String insertSql = "INSERT INTO DTH_2210900029_ChamCong (DTH_2210900029_ID_NV, DTH_2210900029_NgayLamViec, DTH_2210900029_GioVao) VALUES (?, ?, ?)";
                PreparedStatement insertStmt = conn.prepareStatement(insertSql);
                insertStmt.setInt(1, idNhanVien);
                insertStmt.setString(2, ngayLam);
                insertStmt.setString(3, gioHienTai);
                insertStmt.executeUpdate();
                jsonResponse.put("gioVao", gioHienTai);
                jsonResponse.put("message", "Chấm công vào thành công");
            }
            response.getWriter().write(jsonResponse.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write("{\"status\": \"error\", \"message\": \"Lỗi server\"}");
        }
    }





}
