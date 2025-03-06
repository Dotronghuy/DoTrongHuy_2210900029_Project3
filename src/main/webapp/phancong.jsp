<%@ page import="com.example.DTH_2210900029.entity.PhanCong" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="./css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
    <title>Quản lý Phân Công</title>
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
                <h2>Danh sách phân công công việc</h2>
                <button class="btn_add">
                    <i class="fa-solid fa-plus"></i> Thêm phân công
                </button>
            </div>

            <div class="search_bar">
                <input type="text" id="search" placeholder="Tìm kiếm nhân viên..." autocomplete="off">
                <i class="fa-solid fa-magnifying-glass"></i>
            </div>

            <div class="table_container">
                <table>
                    <thead>
                    <tr>
                        <th>Tên nhân viên</th>
                        <th>Công việc</th>
                        <th>Ngày bắt đầu</th>
                        <th>Ngày kết thúc</th>
                        <th>Hành động</th>
                    </tr>
                    </thead>
                    <tbody id="assignment_list">
                    <% List<PhanCong> phanCongs = (List<PhanCong>) request.getAttribute("phanCongs");
                        if (phanCongs != null) {
                            for (PhanCong p : phanCongs) { %>
                    <tr>
                        <td><%= p.getTenNhanVien() %></td>
                        <td><%= p.getTenCongViec() %></td>
                        <td><%= p.getNgayBatDau() %></td>
                        <td><%= p.getNgayKetThuc() %></td>
                        <td>
                            <a href="PhanCongServlet?action=edit&id=<%= p.getId() %>" class="btn_edit">
                                <i class="fa-solid fa-pen"></i>
                            </a>
                            <a href="PhanCongServlet?action=delete&id=<%= p.getId() %>" class="btn_delete"
                               onclick="return confirm('Bạn có chắc muốn xóa?');">
                                <i class="fa-solid fa-trash"></i>
                            </a>
                        </td>
                    </tr>
                    <% } } %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>


</body>
</html>
