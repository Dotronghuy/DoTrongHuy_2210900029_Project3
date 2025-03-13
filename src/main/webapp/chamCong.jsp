<%@ page import="com.example.DTH_2210900029.entity.ChamCong" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="com.example.DTH_2210900029.entity.NhanVien" %>
<%@ page session="true" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>



<!DOCTYPE html>
<html>
<head>

    <link rel="stylesheet" href="./css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
    <title>Danh sách chấm công</title>
</head>
<body>
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
            <h2>Quản lý Chấm Công</h2>
            <input type="hidden" id="idNhanVien" value="${idNhanVien}">
            <button class="btn_add handle_admin" id="btnChamCong">
                <i class="fa-solid fa-plus"></i> Chấm Công
            </button>
        </div>

        <div class="search_bar">
            <input type="text" id="search" placeholder="Tìm kiếm chấm công..." autocomplete="off">
            <i class="fa-solid fa-magnifying-glass"></i>
        </div>

        <div class="table_container">
            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>ID Nhân Viên</th>
                    <th>Ngày Làm Việc</th>
                    <th>Giờ làm việc</th>
                    <th>Giờ về</th>
                    <th>Tổng Giờ Làm</th>
                    <th>Hành động</th>
                </tr>
                </thead>
                <tbody>
                <%
                    List<ChamCong> chamCongs = (List<ChamCong>) request.getAttribute("chamCongs");
                    if (chamCongs != null && !chamCongs.isEmpty()) {
                        for (ChamCong chamCong : chamCongs) {
                %>
                <tr>
                    <td><%= chamCong.getId() %></td>
                    <td data-id="<%= chamCong.getId() %>"><%= chamCong.getIdNhanVien() %></td>
                    <td><%= chamCong.getNgayLam() %></td>
                    <td><%= chamCong.getGioVao() %></td>
                    <td><%= chamCong.getGioRa() %></td>
                    <td><%= chamCong.getTongGio() %> giờ</td>
                    <td>
                        <a href="ChamCongServlet?action=edit&id=<%= chamCong.getId() %>" class="btn_edit" id="btn-edit-<%= chamCong.getId() %>">
                            <i class="fa-solid fa-pen"></i>
                        </a>
                        <a href="ChamCongServlet?action=delete&id=<%= chamCong.getId() %>" class="btn_delete" id="btn-delete-<%= chamCong.getId() %>"
                           onclick="return confirm('Bạn có chắc chắn muốn xóa?');">
                            <i class="fa-solid fa-trash"></i>
                        </a>
                    </td>
                </tr>
                <%
                    }
                } else {
                %>
                <tr>
                    <td colspan="5" style="text-align: center;">Không có dữ liệu chấm công.</td>
                </tr>
                <%
                    }
                %>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script src="./js/chamcong.js"></script>
<script src="./js/admin.js"></script>
</body>
</html>
