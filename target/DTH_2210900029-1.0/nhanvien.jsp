<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.DTH_2210900029.entity.NhanVien" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="./css/style.css">
    <title>Danh sách nhân viên</title>
</head>
<body>
<div id="appspotify">
    <div id="col_left">
        <div id="header_left">
            <div class="header-top">
                <div class="ul-header">
                    <a  class="li-header_item" id="logoutLink" style="text-transform: none; color: #ffffff; cursor: pointer">
                        Đăng xuất
                    </a>
                </div>
            </div>
            <div class="header-bottom">
                <div class="listmusic">
                    <div class="find_listmusic">
                        <div class="srcoll">
                            <ul class="ul_listmusic" data-vai-tro="<%= request.getSession().getAttribute("vaiTro") %>">
                                <%
                                    String vaiTro = (String) request.getSession().getAttribute("vaiTro");
                                    if ("Admin".equals(vaiTro)) {
                                %>
                                <li class="li_list"><a href="/NhanVienServlet">Nhân viên</a></li>
                                <li class="li_list"><a href="/CongViecServlet">Công việc</a></li>
                                <li class="li_list"><a href="/ChamCongServlet">Chấm công</a></li>
                                <li class="li_list"><a href="/LuongServlet">Lương</a></li>
                                <li class="li_list"><a href="/HopDongServlet">Hợp đồng</a></li>
                                <li class="li_list"><a href="/PhanCongServlet">Phân công</a></li>
                                <%
                                } else if ("Nhân viên".equals(vaiTro)) {
                                %>
                                <li class="li_list"><a href="/CongViecServlet">Công việc</a></li>
                                <li class="li_list"><a href="/ChamCongServlet">Chấm công</a></li>
                                <li class="li_list"><a href="/PhanCongServlet">Phân công</a></li>
                                <%
                                    }
                                %>
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
                <button class="btn_add" >
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
                        <th>Chức vụ</th>
                        <th>Hành động</th>
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
                            <button class="btn_edit"
                                    data-id="<%= nv.getId() %>"
                                    data-hoTen="<%= nv.getHoTen() %>"
                                    data-ngaySinh="<%= nv.getNgaySinh() %>"
                                    data-gioiTinh="<%= nv.getGioiTinh() %>"
                                    data-sdt="<%= nv.getSdt() %>"
                                    data-email="<%= nv.getEmail() %>"
                                    data-chucVu="<%= nv.getChucVu() %>">
                                <i class="fa-solid fa-pen"></i>
                            </button>

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
                        <td colspan="8" style="text-align: center;">Không có nhân viên nào.</td>
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
<script src="./js/main.js" defer></script>
<script src="./js/admin.js"></script>
</body>
</html>
