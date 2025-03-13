<%@ page import="com.example.DTH_2210900029.entity.PhanCong" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
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
                <h2>Danh sách phân công công việc</h2>
                <button class="btn_add">
                    <i class="fa-solid fa-plus"></i> Thêm công việc
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
                        <th>ID phân công</th>
                        <th>ID nhân viên</th>
                        <th>Tên nhân viên</th>
                        <th>ID công việc</th>
                        <th>Tên công việc</th>
                        <th>Ngày bắt đầu</th>
                        <th>Ngày kết thúc</th>
                        <th>Hành động</th>
                    </tr>
                    </thead>
                    <tbody id="assignment_list">
                    <%
                        List<PhanCong> phanCongs = (List<PhanCong>) request.getAttribute("phanCongs");
                        if (phanCongs != null) {
                            for (PhanCong p : phanCongs) {
                    %>
                    <tr>
                        <td><%= p.getId() %></td>
                        <td><%= p.getIdNhanVien() %></td>
                        <td><%= p.getTenNhanVien() %></td>
                        <td><%= p.getIdCongViec() %></td>
                        <td><%= p.getTenCongViec() %></td>
                        <td><%= p.getNgayBatDau() %></td>
                        <td><%= p.getNgayKetThuc() %></td>
                        <td>

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

<script src="./js/phancong.js"></script>
<script src="./js/admin.js"></script>
</body>
</html>
