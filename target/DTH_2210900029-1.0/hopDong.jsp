
<%@ page import="java.util.List" %>
<%@ page import="com.example.DTH_2210900029.entity.HopDong" %>
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
                <h2>Danh sách hợp đồng</h2>
                <a href="HopDongServlet?action=add" class="btn_add">
                    <i class="fa-solid fa-plus"></i> Thêm hợp đồng
                </a>
            </div>

            <div class="search_bar">
                <input type="text" id="search" placeholder="Tìm kiếm nhân viên..." autocomplete="off">
                <i class="fa-solid fa-magnifying-glass"></i>
            </div>

            <div class="table_container">
                <table border="1">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>ID Nhân Viên</th>
                        <th>Ngày bắt đầu</th>
                        <th>Ngày kết thúc</th>
                        <th>Loại hợp đồng</th>
                        <th>Hành Động</th>
                    </tr>
                    </thead>
                    <tbody id="contract_list">
                    <% List<HopDong> hopDongs = (List<HopDong>) request.getAttribute("hopDongs");
                        if (hopDongs != null) {
                            for (HopDong hd : hopDongs) { %>
                    <tr>
                        <td><%= hd.getId() %></td>
                        <td><%= hd.getIdNhanVien() %></td>
                        <td><%= hd.getNgayBatDau() %></td>
                        <td><%= hd.getNgayKetThuc() != null ? hd.getNgayKetThuc() : "-" %></td>
                        <td><%= hd.getLoaiHopDong() %></td>
                        <td>
                            <a href="HopDongServlet?action=edit&id=<%= hd.getId() %>" class="btn_edit" title="Chỉnh sửa">
                                <i class="fa-solid fa-pen"></i>
                            </a>
                            <a href="HopDongServlet?action=delete&id=<%= hd.getId() %>" class="btn_delete" title="Xóa"
                               onclick="return confirm('Bạn có chắc chắn muốn xóa hợp đồng này?');">
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
