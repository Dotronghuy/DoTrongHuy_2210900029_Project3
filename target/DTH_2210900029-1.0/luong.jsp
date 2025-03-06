<%@ page import="com.example.DTH_2210900029.entity.Luong" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.Locale" %>
<% NumberFormat currencyFormat = NumberFormat.getNumberInstance(Locale.US); %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="./css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
    <title>Quản lý Lương</title>
</head>
<body>
<div id="appspotify">
    <div id="col_left">
        <div id="header_left">
            <div class="header-top">
                <div class="ul-header">
                    <a href="#" class="li-header_item">
                        <i class="fa-solid fa-house"></i>
                        <h4 class="text_header">Trang chủ</h4>
                    </a>
                </div>
            </div>
            <div class="header-bottom">
                <div class="listmusic">
                    <div class="find_listmusic">
                        <div class="srcoll">
                            <ul class="ul_listmusic">
                                <li class="li_list"><a href="/NhanVienServlet">Nhân viên</a></li>
                                <li class="li_list"><a href="/CongViecServlet">Công việc</a></li>
                                <li class="li_list"><a href="/ChamCongServlet">Chấm công</a></li>
                                <li class="li_list"><a href="/LuongServlet">Lương</a></li>
                                <li class="li_list"><a href="/HopDongServlet">Hợp đồng</a></li>
                                <li class="li_list"><a href="/PhanCongServlet">Phân công</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="col_right">
        <div class="content_right">
            <div class="header_right">
                <h2>Bảng lương nhân viên</h2>
                <a href="luong_form.jsp" class="btn_add">
                    <i class="fa-solid fa-plus"></i> Thêm lương
                </a>
            </div>

            <div class="search_bar">
                <input type="text" id="search" placeholder="Tìm kiếm nhân viên..." autocomplete="off">
                <i class="fa-solid fa-magnifying-glass"></i>
            </div>

            <div class="table_container">
                <table>
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>ID Nhân Viên</th>
                        <th>Tháng</th>
                        <th>Năm</th>
                        <th>Tổng giờ làm</th>
                        <th>Lương cơ bản</th>
                        <th>Phụ cấp</th>
                        <th>Khấu trừ</th>
                        <th>Tổng lương</th>
                        <th>Hành động</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% List<Luong> luongs = (List<Luong>) request.getAttribute("luongs");
                        if (luongs != null && !luongs.isEmpty()) {
                            for (Luong l : luongs) { %>
                    <tr>
                        <td><%= l.getId() %></td>
                        <td><%= l.getIdNhanVien() %></td>
                        <td><%= l.getThang() %></td>
                        <td><%= l.getNam() %></td>
                        <td><%= l.getTongGioLam() %> giờ</td>
                        <td><%= currencyFormat.format(l.getLuongCoBan()) %> VND</td>
                        <td><%= currencyFormat.format(l.getPhuCap()) %> VND</td>
                        <td><%= currencyFormat.format(l.getKhauTru()) %> VND</td>
                        <td><%= currencyFormat.format(l.getTongLuong()) %> VND</td>
                        <td>
                            <a href="LuongServlet?action=edit&id=<%= l.getId() %>" class="btn_edit">
                                <i class="fa-solid fa-pen"></i>
                            </a>
                            <a href="LuongServlet?action=delete&id=<%= l.getId() %>" class="btn_delete"
                               onclick="return confirm('Bạn có chắc chắn muốn xóa?');">
                                <i class="fa-solid fa-trash"></i>
                            </a>
                        </td>
                    </tr>
                    <% } } else { %>
                    <tr>
                        <td colspan="10" style="text-align: center;">Không có dữ liệu lương.</td>
                    </tr>
                    <% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

</div>

</body>
</html>
