<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.DTH_2210900029.entity.NhanVien" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="./css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
    <title>Danh sách nhân viên</title>
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
                <h2>Quản lý nhân viên</h2>
                <button class="btn_add" onclick="window.location.href='NhanVienServlet?action=add'">
                    <i class="fa-solid fa-plus"></i> Thêm nhân viên
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
                        <th>ID</th>
                        <th>Họ Tên</th>
                        <th>Ngày Sinh</th>
                        <th>Giới Tính</th>
                        <th>SĐT</th>
                        <th>Email</th>
                        <th>Hành động</th>
                        <th>Chức vụ</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        List<NhanVien> nhanViens = (List<NhanVien>) request.getAttribute("nhanViens");
                        if (nhanViens != null && !nhanViens.isEmpty()) {
                            for (NhanVien nv : nhanViens) {
                    %>
                    <tr>
                        <td><%= nv.getId() %></td>
                        <td><%= nv.getHoTen() %></td>
                        <td><%= nv.getNgaySinh() %></td>
                        <td><%= nv.getGioiTinh() %></td>
                        <td><%= nv.getSdt() %></td>
                        <td><%= nv.getEmail() %></td>
                        <td><%= nv.getChucVu() %></td>
                        <td>
                            <a href="NhanVienServlet?action=edit&id=<%= nv.getId() %>" class="btn_edit">
                                <i class="fa-solid fa-pen"></i>
                            </a>
                            <a href="NhanVienServlet?action=delete&id=<%= nv.getId() %>" class="btn_delete"
                               onclick="return confirm('Bạn có chắc chắn muốn xóa nhân viên này không?');">
                                <i class="fa-solid fa-trash"></i>
                            </a>
                        </td>
                    </tr>
                    <%
                        }
                    } else {
                    %>
                    <tr>
                        <td colspan="11" style="text-align: center;">Không có nhân viên nào.</td>
                    </tr>
                    <%
                        }
                    %>
                    </tbody>
                </table>
            </div>
        </div>

    </div>
</div>
</body>
</html>
