<%@ page import="com.example.DTH_2210900029.entity.Luong" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.Locale" %>
<% NumberFormat currencyFormat = NumberFormat.getNumberInstance(Locale.US); %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
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
                    <tr class="luong-row">
                        <td><%= l.getId() %></td>
                        <td data-id="<%= l.getIdNhanVien()  %>"><%= l.getIdNhanVien() %></td>
                        <td><%= l.getThang() %></td>
                        <td><%= l.getNam() %></td>
                        <td class="tong-gio"><%= l.getTongGioLam() %></td>
                        <td class="luong-co-ban"><%= l.getLuongCoBan() %></td>
                        <td class="phu-cap"><%= l.getPhuCap() %></td>
                        <td class="khau-tru"><%= l.getKhauTru() %></td>
                        <td class="tong-luong"></td>
                        <td>
                            <a class="btn_edit"
                               data-id="<%= l.getId() %>"
                               data-id-nhan-vien="<%= l.getIdNhanVien() %>"
                               data-thang="<%= l.getThang() %>"
                               data-nam="<%= l.getNam() %>"
                               data-tong-gio="<%= l.getTongGioLam() %>"
                               data-luong-co-ban="<%= l.getLuongCoBan() %>"
                               data-phu-cap="<%= l.getPhuCap() %>"
                               data-khau-tru="<%= l.getKhauTru() %>"
                              >
                                <i class="fa-solid fa-pen"></i>
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
<script src="./js/luong.js"></script>
<script src="./js/admin.js"></script>
</body>
</html>
