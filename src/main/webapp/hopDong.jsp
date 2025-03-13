
<%@ page import="java.util.List" %>
<%@ page import="com.example.DTH_2210900029.entity.HopDong" %>
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
                            <a class="btn_edit" title="Chỉnh sửa"
                               data-id="<%= hd.getId() %>"
                               data-id-nhan-vien="<%= hd.getIdNhanVien() %>"
                               data-ngay-bat-dau="<%= hd.getNgayBatDau() %>"
                               data-ngay-ket-thuc="<%= hd.getNgayKetThuc() %>">
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
<script src="./js/hopdong.js"></script>
<script src="./js/admin.js"></script>
</body>
</html>
